package com.example.jaanderson.countingsecondsthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SecondsCounter extends AppCompatActivity {
    //we want tshi to be an instacnce variable because the handler is going to need it
    private int count;

    //we can also have a reference to the TextView.
    private TextView secondsDisplay;

    //we need to start the thread
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconds_counter);

        //make a reference to secondsDisplay
        secondsDisplay = (TextView) findViewById(R.id.secondsTV);

        count = 0;

        thread = new Thread( new CountNumbers());
        thread.start();


    }

    //We need to have a class that is going to be our thread and is going to do the counter.
    private class CountNumbers implements Runnable {
        private final int DELAY = 1000;

        //needs a run
        public void run() {
            //we want to pause for a second and augment the counter. We wanna keep doing that
            //unitl we are told to stop
            //this runs the counter.
            try{
                while(true) {
                    Thread.sleep(1000);
                    count++;

                    //
                    threadHandler.sendEmptyMessage(0);
                }
            }
            //interrupted means the stop button has called us
            catch (InterruptedException ie) {
                return;
            }
        }
    }

    //set up a handlers
    private Handler threadHandler = new Handler() {
        //needs to override handleMessage
        public void handleMessage(Message msg) {
            //we can use the secondsDisplay here to be the current value of count
            secondsDisplay.setText(String.valueOf(count));
        }
    };

    public void signalStop( View v ) {

        //this thll cause an interrupted exception.
        thread.interrupt();

    }
}
