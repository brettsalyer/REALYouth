package com.example.brett.realyouth;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Created by brett on 11/14/17.
 */

public class Streaming extends AppCompatActivity {

    private SeekBar musicSeek;
    private TextView currentTime;
    private TextView totalTime;
    private ImageButton play_pause;
    private Handler seekHandler = new Handler();
    private MediaPlayer mp = new MediaPlayer();
    Context context;
    Utilities util = new Utilities();

    //Default Constructor
    public Streaming(){}
    //Contructor
    public Streaming(Context context){
        this.context = context;
    }
    //Method to run the runnable to update the seekbar
    public void updateSeekBar(){
        seekHandler.postDelayed(mUpdateTimeTask, 100);
    }

    public void prepareStreaming() throws IOException {
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setDataSource("http://tricountynaz.net/media/audio/2017-11-08-The%20Compassion%20of%20the%20Christ.mp3");
        mp.prepare();
    }

    public void startStreaming(){
        mp.start();
    }

    public void pauseStreaming(){
        mp.pause();
    }

    //Runnable to update the seekbar with the current position.
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            int totalDuration = mp.getDuration();
            int currentPosition = (mp.getCurrentPosition());


            //Displaying Total Duration time
            totalTime = (TextView)((Activity)context).findViewById(R.id.totalTime);
            totalTime.setText(util.milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            currentTime = (TextView)((Activity)context).findViewById(R.id.currentTime);
            currentTime.setText(util.milliSecondsToTimer(currentPosition));
            //Set the bars total duration, based on the song duration (converted to seconds)
            musicSeek = (SeekBar)((Activity)context).findViewById(R.id.music_seek);
            musicSeek.setMax(totalDuration/1000);
            // Updating progress bar
            musicSeek.setProgress(mp.getCurrentPosition()/1000);

            // Running this thread after 100 milliseconds
            seekHandler.postDelayed(this, 100);
        }
    };

    //What happens when the user interacts with the button
    public void onPlayClick (){
        play_pause = (ImageButton)((Activity)context).findViewById(R.id.playButton);
        play_pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                play_pause.setImageResource(R.drawable.ic_pause_name);

                try {
                    prepareStreaming();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (mp.isPlaying()) {
                    pauseStreaming();
                    play_pause.setImageResource(R.drawable.ic_play_name);


                } else {
                    //mediaPlayer.start();
                    startStreaming();
                }
            }
        });
    }

    //Handles when the user interacts with the seekbar
    public void onDrag(){
        musicSeek = (SeekBar)((Activity)context).findViewById(R.id.music_seek);
        musicSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    //seeks to the current position of the slider when the user (b) interacts.
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
    public TextView getCurrentTime() {
        return this.currentTime;
    }

    public TextView getTotalTime(){
        return this.totalTime;
    }

    public SeekBar getMusicSeek(){
        return this.musicSeek;
    }
    public MediaPlayer getMp(){
        return this.mp;
    }
    public void setMusicSeek(SeekBar seek){
        this.musicSeek = seek;
    }
    public void setCurrentTime(TextView time){
        this.currentTime = time;
    }
    public void setTotalTime(TextView time){
        this.totalTime = time;
    }
    public void setMp(MediaPlayer player){
        this.mp = player;
    }
}
