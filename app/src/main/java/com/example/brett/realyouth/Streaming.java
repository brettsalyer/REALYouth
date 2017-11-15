package com.example.brett.realyouth;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by brett on 11/14/17.
 */

public class Streaming {

    private SeekBar musicSeek;
    private TextView currentTime;
    private TextView totalTime;
    private Handler seekHandler = new Handler();
    private MediaPlayer mp = new MediaPlayer();;
    Utilities util = new Utilities();

    //Method to run the runnable to update the seekbar
    public void updateSeekBar(){
        seekHandler.postDelayed(mUpdateTimeTask, 100);
    }

    //Runnable to update the seekbar with the current position.
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            int totalDuration = mp.getDuration();
            int currentPosition = (mp.getCurrentPosition());


            //Displaying Total Duration time
            totalTime.setText(util.milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            currentTime.setText(util.milliSecondsToTimer(currentPosition));
            //Set the bars total duration, based on the song duration (converted to seconds)
            musicSeek.setMax(totalDuration/1000);
            // Updating progress bar
            musicSeek.setProgress(mp.getCurrentPosition()/1000);

            // Running this thread after 100 milliseconds
            seekHandler.postDelayed(this, 100);
        }
    };
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
