package com.example.brett.realyouth;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.invoke.MutableCallSite;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class MainActivity extends AppCompatActivity {

        Streaming stream = new Streaming(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stream.updateSeekBar();
        stream.onPlayClick();
        stream.onDrag();

    }








}
