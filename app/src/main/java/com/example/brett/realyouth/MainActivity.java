package com.example.brett.realyouth;

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
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


    public class MainActivity extends AppCompatActivity {
        private ImageButton playButton;
        Streaming strm = new Streaming();
        //private SeekBar musicSeek = strm.getMusicSeek();
        //private TextView currentTime = strm.getCurrentTime();
        //private TextView totalTime = strm.getTotalTime();
        //private MediaPlayer mp = strm.getMp();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strm.getMusicSeek().findViewById(R.id.music_seek);
        strm.getCurrentTime().findViewById(R.id.currentTime);
        strm.getTotalTime().findViewById(R.id.totalTime);
        strm.getMp().setAudioStreamType(AudioManager.STREAM_MUSIC);
        //musicSeek = (SeekBar) findViewById(R.id.music_seek);
        //currentTime = (TextView)findViewById(R.id.currentTime);
        //totalTime = (TextView) findViewById(R.id.totalTime);
       //mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        strm.updateSeekBar();
        onPlayClick();
        onDrag();

    }

    //What happens when the user interacts with the button
    public void onPlayClick (){
        playButton = (ImageButton) findViewById(R.id.playButton);
        try {
            strm.getMp().setDataSource("http://tricountynaz.net/media/audio/2017-11-08-The%20Compassion%20of%20the%20Christ.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            strm.getMp().prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        playButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        playButton.setImageResource(R.drawable.ic_pause_name);


                        if (strm.getMp().isPlaying()) {
                            strm.getMp().pause();
                            playButton.setImageResource(R.drawable.ic_play_name);


                        } else {
                            //mediaPlayer.start();
                            strm.getMp().start();
                        }
                    }
                });
    }
    //Handles when the user interacts with the seekbar
    private void onDrag(){
    strm.getMusicSeek().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    //seeks to the current position of the slider when the user (b) interacts.
                    strm.getMp().seekTo(i*1000);
                    strm.getMusicSeek().setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });}






}
