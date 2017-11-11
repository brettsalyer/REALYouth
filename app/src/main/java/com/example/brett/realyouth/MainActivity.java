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

        private MediaPlayer mediaPlayer;
        private ImageButton playButton;
        private SeekBar musicSeek;
        private TextView currentTime;
        private TextView totalTime;
        private Handler seekHandler = new Handler();
        MediaPlayer mp = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicSeek = (SeekBar) findViewById(R.id.music_seek);
        currentTime = (TextView)findViewById(R.id.currentTime);
        totalTime = (TextView) findViewById(R.id.totalTime);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        updateSeekBar();
        onPlayClick();
        onDrag();

    }

    //What happens when the user interacts with the button
    public void onPlayClick (){
        playButton = (ImageButton) findViewById(R.id.playButton);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.acdc);
        try {
            mp.setDataSource("http://tricountynaz.net/media/audio/2017-11-08-The%20Compassion%20of%20the%20Christ.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        playButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        playButton.setImageResource(R.drawable.ic_pause_name);


                        if (mp.isPlaying()) {
                            mp.pause();
                            playButton.setImageResource(R.drawable.ic_play_name);


                        } else {
                            //mediaPlayer.start();
                            mp.start();
                        }
                    }
                });
    }
    //Handles when the user interacts with the seekbar
    private void onDrag(){
    musicSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    //seeks to the current position of the slider when the user (b) interacts.
                    //mediaPlayer.seekTo(i*1000);
                    //For LIVE
                    mp.seekTo(i*1000);
                    musicSeek.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });}
    //Method to run the runnable to update the seekbar
    public void updateSeekBar(){

        seekHandler.postDelayed(mUpdateTimeTask, 100);
    }
        //Method to convert milliseonds to seconds for the time display
        private String milliSecondsToTimer(long milliseconds) {
            String finalTimerString = "";
            String secondsString = "";

            // Convert total duration into time
            int hours = (int) (milliseconds / (1000 * 60 * 60));
            int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
            int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
            // Add hours if there
            if (hours > 0) {
                finalTimerString = hours + ":";
            }

            // Prepending 0 to seconds if it is one digit
            if (seconds < 10) {
                secondsString = "0" + seconds;
            } else {
                secondsString = "" + seconds;
            }

            finalTimerString = finalTimerString + minutes + ":" + secondsString;

            // return timer string
            return finalTimerString;
        }

        //Runnable to update the seekbar with the current position.
        private Runnable mUpdateTimeTask = new Runnable() {
            public void run() {
                int totalDuration = mp.getDuration();
                int currentPosition = (mp.getCurrentPosition());


                // Displaying Total Duration time
                totalTime.setText(milliSecondsToTimer(totalDuration));
                // Displaying time completed playing
                currentTime.setText(milliSecondsToTimer(currentPosition));
                //Set the bars total duration, based on the song duration (converted to seconds)
                musicSeek.setMax(totalDuration/1000);
                // Updating progress bar
                musicSeek.setProgress(mp.getCurrentPosition()/1000);

                // Running this thread after 100 milliseconds
                seekHandler.postDelayed(this, 100);
            }
        };


}
