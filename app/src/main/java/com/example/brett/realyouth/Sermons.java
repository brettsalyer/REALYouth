package com.example.brett.realyouth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by brett on 11/16/17.
 */

class Sermons extends AsyncTask<String, String, Void> {
    MainActivity main = new MainActivity();
    InputStream inputStream;
    String result = "";



    protected void onPreExecute() {
        ProgressBar status = new ProgressBar(main.getApplicationContext());
        Toast.makeText(main.getApplicationContext(), ("Fetching Sermons..."), Toast.LENGTH_SHORT).show();
       // status.setOnCancelListener(new DialogInterface.OnCancelListener() {
        //    @Override
         //   public void onCancel(DialogInterface dialogInterface) {
          //      Sermons.this.cancel(true);
           // }
        //});

    }

    @Override

    protected Void doInBackground(String... strings) {
        String jsonURL = "http://tricountynaz.net/api/v1/youth_sermons/";
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(jsonURL);

        try {
            post.setEntity(new UrlEncodedFormEntity(param));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
        }
        catch(Exception e){
            Log.e("IOException", "Error has occured");
        }

        // Convert response to string using String Builder

        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder sBuilder = new StringBuilder();


        String line = null;
        try {
            while((line = bReader.readLine()) != null){
                sBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = sBuilder.toString();

        return null;
    }
}
