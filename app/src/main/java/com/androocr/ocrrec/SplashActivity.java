package com.androocr.ocrrec;


import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        imageView=(ImageView) findViewById(R.id.imageView);
        Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_anim);
//        imageView.setVisibility(View.GONE);

        Thread timer=new Thread()
        {
            @Override
            public void run() {
                try                {
                    music=MediaPlayer.create(SplashActivity.this,R.raw.aaa11);
                    music.start();
                    sleep(5000);

                }
                catch(InterruptedException e)
                {

                }
                finally {
                    Intent i=new Intent(SplashActivity.this,home.class);
                    startActivity(i);
                    finish();
                }
            }
        };

        timer.start();


    }
    @Override
    protected void onPause() {
        super.onPause();
        music.release();
    }
}

