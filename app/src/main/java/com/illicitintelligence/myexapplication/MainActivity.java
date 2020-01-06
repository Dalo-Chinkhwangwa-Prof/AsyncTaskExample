package com.illicitintelligence.myexapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {


    ProgressBar progressBar;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int x = intent.getIntExtra("my_int", 0);
            progressDialog.setTitle("Hello Loading.. " + x);
            progressBar.setProgress(x*10);
            if(x == 10)
                progressDialog.dismiss();

        }
    };

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Hello Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        MyAsyncTask myAsyncTask = new MyAsyncTask(this.getApplicationContext());

        myAsyncTask.execute(10);

        registerReceiver(receiver, new IntentFilter("progress.intent"));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
