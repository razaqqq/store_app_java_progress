package com.example.storeapps.Home;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.Util.MediaObjectVideoContainer;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionUtil;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import java.util.ArrayList;

public class VideoPlayerReciclerView extends RecyclerView {

    private static final String TAG = "VideoPlayerReciclerView";

    //ui
    private TextView username;
    private TextView description;
    private TextView subDescription;
    private Integer videoSrc;
    private ImageView videoView;
    private ImageView profileImageBottom;
    private TextView shareCount;
    private TextView chatCount;
    private TextView likeCount;
    private ImageView profileImage;
    private View viewHolderParent;
    private StyledPlayerView videoSurfaceView;
    private ExoPlayer videoPlayer;

    // vars
    ArrayList<MediaObjectVideoContainer> mediaObjectVideoContainers = new ArrayList<>();
    int videoSurvceDefaultHeight = 0;
    int screenDevaultHeight = 0;
    int playPosition = -1;
    boolean isVideoViewAdded;
    private Context context;

    public VideoPlayerReciclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public VideoPlayerReciclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        this.context = context.getApplicationContext();
        Display display = ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        videoSurvceDefaultHeight = point.x;
        screenDevaultHeight = point.y;

        videoSurfaceView = new StyledPlayerView(this.context);
        videoSurfaceView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);

//        DefaultBandwidthMeter.Builder bandwidthMeter = new DefaultBandwidthMeter.Builder(this.context);
//        TrackSelection videoTrackSelectionFactory =
//                new AdaptiveTrackSelection.Factory(bandwidthMeter);
//        TrackSelector trackSelector =
//                new DefaultTrackSelector(videoTrackSelectionFactory);
    }

    public VideoPlayerReciclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
