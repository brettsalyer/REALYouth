package com.example.brett.realyouth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.NameValuePair;

import java.io.InputStream;
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
        String url = "http://tricountynaz.net/api/v1/youth_sermons/";
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        return null;
    }
}
