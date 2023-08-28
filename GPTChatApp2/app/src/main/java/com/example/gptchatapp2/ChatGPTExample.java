package com.example.gptchatapp2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ChatGPTExample extends AsyncTask<String,Void,String> {
    String prompt;
    String result;
    public ChatGPTExample(String prompt)
    {this.prompt=prompt;}
    public String generate(){
        Log.d("Returnmsg","1");
        String apiKey = "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"; // Replace with your actual API key
        String apiUrl = "https://api.openai.com/v1/engines/text-davinci-001/completions";

        Log.d("Returnmsg","2");
        try {

            Log.d("Returnmsg","3");
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            Log.d("Returnmsg","4");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);


            String requestBody = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 50}";
            Log.d("Returnmsg","5");
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(requestBody);
                Log.d("Returnmsg","6");
                outputStream.flush();
                Log.d("Returnmsg","6.1");
            }
            catch (Exception e)
            {
                Log.d("Returnmsg","6.2 " + e);
            }
            Log.d("Returnmsg","7");

            int responseCode = connection.getResponseCode();

            Log.d("Returnmsg","RespCode"+responseCode);
            Log.d("Returnmsg","7.1");
            int next=HttpURLConnection.HTTP_OK;
            Log.d("Returnmsg","Httpconn"+next);
            if (responseCode == next) {
                Log.d("Returnmsg","7.2");
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    Log.d("Returnmsg","7.3");
                    String line;
                    Log.d("Returnmsg","8");
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    String responseBody = response.toString();
                    Log.d("Returnmsg","9");
                    // Parse the JSON response using org.json library
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    String generatedText = jsonResponse.getJSONArray("choices")
                            .getJSONObject(0)
                            .getString("text");
                    Log.d("Returnmsg","10");
                    return("ChatGPT Response: " + generatedText);
                }
            } else {
                Log.d("Returnmsg","10.1");
                Log.d("Returnmsg"," "+"Error: " + responseCode + " - " + connection.getResponseMessage());
                return("Error: " + responseCode + " - " + connection.getResponseMessage());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.d("Returnmsg","11");
        }

        Log.d("Returnmsg","12");
        Log.d("Returnmsg","Error");
        return "Error";
    }

    @Override
    protected void onPreExecute() {
        Log.d("Returnmsg","in preexecute");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d("Returnmsg","in inexecute");
        return generate();
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Returnmsg","in postexecute");
        result=s;
        super.onPostExecute(s);
    }
}
