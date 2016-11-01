package com.example.jaanderson.countingsecondsthread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CountingAsynchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting_asynch);


        CountingTask tsk = new CountingTask();
        tsk.execute();

    }

    //setup AsynchTask here                     <Params, Progress, Return>
    private class CountingTask extends AsyncTask < Void, Integer, Integer> {
        protected Integer doInBackground(Void ... unused) {
            int i = 0;

            while(i<100) {
                //ignores interrupted exceptions.
                SystemClock.sleep( 250 );
                i++;

                if( i % 5 == 0) {
                    publishProgress(i);
                }
            }
            return i;
        }

        //takes the i from the previous class
        protected void onProgressUpdate( Integer ... progress ) {
            TextView tv = (TextView) findViewById(R.id.counter);

            //we want to say how much we are complete
            tv.setText(  progress[0]   + "% complete");

            ProgressBar pb = (ProgressBar) findViewById(R.id.progress_bar);
            pb.setProgress(progress[0]);
        }


        protected void onPostExecute( Integer result ) {

            //do in background returns i so this method uses it.
            TextView tv = (TextView) findViewById(R.id.counter);
            tv.setText("Complete. Result is " + result.toString() );
        }
    }



}
