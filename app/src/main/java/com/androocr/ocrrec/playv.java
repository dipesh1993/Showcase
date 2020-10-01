package com.androocr.ocrrec;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class playv extends AppCompatActivity {

     VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playv);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        TextView tv = (TextView)findViewById(R.id.textofc);
        tv.setSelected(true);

        videoView =(VideoView)findViewById(R.id.videoView1);

        //Creating MediaController
        final MediaController mediaController= new MediaController(getApplicationContext());
        mediaController.setAnchorView(videoView);

        //specify the location of media file
        // Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.aaa);

        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);

        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.aaa));
        videoView.requestFocus();

      /*  Animation zoomAnimation = AnimationUtils.loadAnimation(playv.this, R.anim.fade_in);
        videoView.startAnimation(zoomAnimation); */

        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //videoView.start();
                videoView.stopPlayback();
                finish();
                moveTaskToBack(true);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        moveTaskToBack(true);
    }
}
