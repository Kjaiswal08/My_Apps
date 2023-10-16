package com.example.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    AudioManager audiomanger;
    public void play(View view)
    {player.start();}
    public void pause(View view)
    {player.pause();}
    public void stop(View view)
    {player.stop();}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player=MediaPlayer.create(this,R.raw.music);
        audiomanger=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol=audiomanger.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVol=audiomanger.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar vol=findViewById(R.id.seekVol1);
        vol.setMax(maxVol);
        vol.setProgress(currVol);
        vol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audiomanger.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}});
        SeekBar prog=findViewById(R.id.seekProg);
        prog.setMax(player.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                prog.setProgress(player.getCurrentPosition());
            }
        },0,100000);
        prog.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player.seekTo(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}