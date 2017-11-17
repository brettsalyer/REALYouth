package com.example.brett.realyouth;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONObject;

import java.io.IOException;
import java.lang.invoke.MutableCallSite;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.json.*;

public class MainActivity extends AppCompatActivity {

        Streaming stream = new Streaming(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar status = (ProgressBar)findViewById(R.id.downloadingProgress);

        JSONObject obj = null;
        try {
            obj = new JSONObject("http://tricountynaz.net/api/v1/youth_sermons/");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String title;
        try {
            title = obj.getJSONObject("title").getString("title");
            Toast.makeText(getApplicationContext(), obj.getString("title"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        stream.onPlayClick();


    }

public Context getContext(){
        Context context = getApplicationContext();
        return context;
}







}
