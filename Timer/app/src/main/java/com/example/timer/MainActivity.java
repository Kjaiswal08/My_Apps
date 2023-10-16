package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int nos=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This runs a particular task a given interval
        Handler handy=new Handler();
        Runnable runs=new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "This is toast"+nos, Toast.LENGTH_SHORT).show();
                handy.postDelayed(this,1000);
            }
        };
        handy.post(runs);
        //Same description
        new CountDownTimer(5000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("Kshitij","onTick running");
            }

            @Override
            public void onFinish() {
                Log.d("Kshitij","onfinish running");
            }
        }.start();
    }
}