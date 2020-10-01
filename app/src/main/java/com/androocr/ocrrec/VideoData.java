package com.androocr.ocrrec;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class VideoData extends AppCompatActivity {
    private static final String TAG = "VideoData";
    TextView title1,description;
    YouTubePlayerView youTubePlayer;
    ImageView imageView;
    Button btnplay;
    String title,desc,img,img1;
    YouTubePlayer.OnInitializedListener mOnInitialisedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_data);
        final YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtubeplay);
        frag.initialize(YoutubeConfig.getApiKey(), mOnInitialisedListener);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title1 = findViewById(R.id.textView5);
        description = findViewById(R.id.textView6);
//        youTubePlayer = findViewById(R.id.youtubeplay);
        btnplay=findViewById(R.id.btnplay);
        imageView=findViewById(R.id.imageView);
        Log.d(TAG,"oncreate:starting.");

        title = getIntent().getExtras().getString("name");
        desc = getIntent().getExtras().getString("desc");
        img = getIntent().getExtras().getString("info");
        img1 = getIntent().getExtras().getString("info1");
        TextView tv = (TextView)findViewById(R.id.textofc);
        tv.setSelected(true);
        title1.setText(title);
        description.setText(desc);
        Picasso.with(VideoData.this).load(img1).into(imageView);

        mOnInitialisedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG,"onClick:Done initializing.");
                youTubePlayer.loadVideo(img);
                imageView.setVisibility(View.GONE);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG,"onClick: Failed initializing.");
            }
        };
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:initializing.");
                frag.initialize(YoutubeConfig.getApiKey(),mOnInitialisedListener);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
