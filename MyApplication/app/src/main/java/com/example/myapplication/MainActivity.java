package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView show=findViewById(R.id.player);
        show.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.smallvid);
        MediaController controller=new MediaController(this);
        controller.setAnchorView(show);//Connects controller to video
        show.setMediaController(controller);//Connects video to controller
        show.start();
    }
}