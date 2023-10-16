package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public  static final String msg="Order";
    public void placeorder(View view)
    {
        Intent intent=new Intent(this,orderActivity.class);
        EditText ed1=findViewById(R.id.TestView1);
        EditText ed2=findViewById(R.id.TestView2);
        EditText ed3=findViewById(R.id.TestView3);
        String order=ed1.getText().toString()+" , "+ed2.getText().toString()+" & "+ed3.getText().toString();
        intent.putExtra(msg,order);//msg declared at global acts as a key and order acts as value when we send it to thenextscreen
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}