package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);
        Intent intent=getIntent();
        String name=intent.getStringExtra("Rname");
        String number=intent.getStringExtra("Rphone");
        TextView nameView=findViewById(R.id.nameView);
        TextView nosView=findViewById(R.id.nosView);
        nameView.setText(name);
        nosView.setText(number);

    }
}