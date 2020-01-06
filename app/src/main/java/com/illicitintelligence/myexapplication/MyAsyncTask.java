package com.illicitintelligence.myexapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Integer, Integer, Float> {

    private Context applicationContext;

    public MyAsyncTask(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected Float doInBackground(final Integer... seconds) {


        new Thread(){
            @Override
            public synchronized void start() {
                super.start();

                for(int i = 0; i < seconds[0]; i ++){

                    try {
                        Thread.sleep(1000);

                        publishProgress((i+1));


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }.start();
        return 0.0f;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //
        Intent progressIntent = new Intent("progress.intent");
        progressIntent.putExtra("my_int", values[0]);
        applicationContext.sendBroadcast(progressIntent);

    }
}
