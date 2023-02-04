package com.example.storeapps.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.storeapps.MainActivity;
import com.example.storeapps.R;

import com.example.storeapps.databinding.LoginActivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;

public class Login extends AppCompatActivity {

    LoginActivityBinding binding;

    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);


        initFirebase();

        EditText edEmail = binding.email;
        EditText edPassword = binding.password;
        ProgressBar progressBar = binding.progressBar;
        TextView pleaseWait = binding.pleaseWait;

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = mAuth.getCurrentUser();
                if (currentUser != null)
                {

                }
                else
                {

                }
            }
        };


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();

                if (!edEmail.getText().toString().equals("") && !edPassword.getText().toString().equals(""))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    pleaseWait.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        progressBar.setVisibility(View.GONE);
                                        pleaseWait.setVisibility(View.GONE);
                                        if (task.getResult().getUser().isEmailVerified())
                                        {
                                            startActivity(new Intent(Login.this, MainActivity.class));
                                            finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(Login.this, "Your Email Is Not Verivied", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(Login.this, "Send Email Verivication", Toast.LENGTH_SHORT).show();

                                            task.getResult().getUser().sendEmailVerification()
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(Login.this, "Success Sending Verification Email", Toast.LENGTH_SHORT).show();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(Login.this, "Failed sen the verivication", Toast.LENGTH_SHORT).show();
                                                            Toast.makeText(Login.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                        }
                                    }
                                    else
                                    {
                                        progressBar.setVisibility(View.GONE);
                                        pleaseWait.setVisibility(View.GONE);
                                        Toast.makeText(Login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }
                else
                {
                    Toast.makeText(Login.this, "Please Input Your Credential", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.registerNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void initFirebase()
    {
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

}
