package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/** @noinspection ALL*/
public class MainActivity extends AppCompatActivity {
    public class BG extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("HarrryBg","onPreExecute");
        }
        @Override
        protected String doInBackground(String... urls) {
            Log.d("HarrryBg","doInbackground");
            String result="";
            URL url;
            HttpURLConnection conn;
            try {
                url=new URL(urls[0]);
                conn= (HttpsURLConnection)url.openConnection();
                Log.d("startedreading","1");
                InputStream in= conn.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                Log.d("startedreading","2");
                int data=reader.read();
                while(data!=-1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
            } catch (Exception e) {
                Log.d("startederroe",e.toString());
                e.printStackTrace();
                return "Something Went wrong";
            }
            return result;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("HarrryBg","onPostExecute");
            Log.d("HarrryBg",s);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BG ob=new BG();
        ob.execute("http://www.codewithharry.com/");
    }
}