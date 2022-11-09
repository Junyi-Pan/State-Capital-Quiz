package edu.uga.cs.statecapitalquiz;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private StatesData statesData = null;

    private Button startQuiz;
    private Button viewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startQuiz = findViewById(R.id.button);
        viewResults = findViewById(R.id.button2);

        startQuiz.setOnClickListener((view) -> {
            Intent intent = new Intent(this, QuizActivity.class);
            startActivity(intent);
        });

        viewResults.setOnClickListener((view) -> {
            Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);
        });

    }
/*
    // This is an AsyncTask class (it extends AsyncTask) to perform DB writing of a job lead, asynchronously.
    public class StateDBWriter extends AsyncTask<State, State> {

        // This method will run as a background process to write into db.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onClick listener of the Save button.
        @Override
        protected State doInBackground( State... states ) {
            statesData.storeState(states[0]);
            return states[0];
        }
        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.  Note that doInBackground returns a JobLead object.
        // That object will be passed as argument to onPostExecute.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( State state ) {
            // Show a quick confirmation message
            Log.d(TAG, "State saved: " + state );
        }
    }

*/
}