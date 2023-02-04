package com.example.storeapps.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.storeapps.Model.User;
import com.example.storeapps.Model.UserAccountSetting;
import com.example.storeapps.R;
import com.example.storeapps.databinding.RegisterActivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    RegisterActivityBinding binding;

    FirebaseAuth mAuth;
    FirebaseDatabase database;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(Register.this, R.layout.register_activity );

        initFirebase();

        EditText edEmail = binding.email;
        EditText edUsername = binding.username;
        EditText edPassword = binding.password;
        EditText edAddress = binding.address;

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.VISIBLE);
                if (!edEmail.getText().toString().equals("") && !edUsername.getText().toString().equals("")
                    && !edPassword.getText().toString().equals("")
                )
                {
                    String email = edEmail.getText().toString();
                    String username = edUsername.getText().toString();
                    String address = edAddress.getText().toString();
                    String password = edPassword.getText().toString();

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        binding.progressBar.setVisibility(View.GONE);
                                        binding.progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Register.this, "Succesfull created users, Send Email Verification", Toast.LENGTH_SHORT).show();
                                        task.getResult().getUser().sendEmailVerification()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        // Todo lets create some model first
                                                        User user = new User(email, username, address, password);
                                                        UserAccountSetting userAccountSetting = new UserAccountSetting(
                                                                username, username, "", "", "", "", address, task.getResult().getUser().getUid()
                                                        );

                                                        database.getReference().child(getString(R.string.db_store_app))
                                                                .child(getString(R.string.db_user))
                                                                .child(task.getResult().getUser().getUid())
                                                                .setValue(user);

                                                        database.getReference().child(getString(R.string.db_store_app))
                                                                .child(getString(R.string.db_user_account_setting))
                                                                .child(task.getResult().getUser().getUid())
                                                                .setValue(userAccountSetting);

                                                        Intent intent = new Intent(Register.this, Login.class);
                                                        startActivity(intent);

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(Register.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                    else
                                    {
                                        Toast.makeText(Register.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        binding.progressBar.setVisibility(View.GONE);
                                        binding.pleaseWait.setVisibility(View.GONE);
                                    }
                                }
                            });

                }
                else
                {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(Register.this, "Please Input All The Credentials", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void initFirebase()
    {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

}
