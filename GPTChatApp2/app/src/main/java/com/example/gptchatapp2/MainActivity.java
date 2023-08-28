package com.example.gptchatapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String input;

    private ArrayList<String> respString;
    private ListView respList;
    private ArrayAdapter<String> arrayAdapter;
    public void setListview()
    {   Log.d("checksmsg","Entering input in listview");
        respString.add(input);}
    public void callGPTclass()
    {ChatGPTExample br=new ChatGPTExample(input);
        br.execute("https://api.openai.com/v1/engines/davinci-codex/completions");
        Log.d("checksmsg","Calling GPT class");
        String resp=br.result;
        Log.d("checksmsg","Got response: "+resp);
        respString.add(resp);
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton send=findViewById(R.id.imageButton2);
        respString=new ArrayList<>();
        respList=findViewById(R.id.responseList);
        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,respString);
        respList.setAdapter(arrayAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input =((EditText)findViewById(R.id.Input)).getText().toString();
                setListview();
                callGPTclass();
            }
        });
        /*
        */
    }
}