package com.example.sharedpreferencestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView show=findViewById(R.id.fixedtext);
        Button button=findViewById(R.id.button);
        EditText write=findViewById(R.id.enterText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=write.getText().toString();
                SharedPreferences shrd=getSharedPreferences("demo",MODE_PRIVATE);
                SharedPreferences.Editor editor=shrd.edit();
                editor.putString("Str:",msg);
                editor.putString("Str1","This is 2nd part");
                editor.apply();
                show.setText(msg);
            }
        });
        //Get value of shared preference back
        SharedPreferences getshrd=getSharedPreferences("demo",MODE_PRIVATE);
        String val=getshrd.getString("Str","Trying to get value back..");
        String val1=getshrd.getString("Str1","Trying to get value back..");
        show.setText(val+val1);
    }
}