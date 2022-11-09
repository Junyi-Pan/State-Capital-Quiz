package edu.uga.cs.statecapitalquiz;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;

/**
 * This class is facilitates storing and restoring questions and quizzes stored.
 */
public class StatesData implements Serializable {

    public static final String DEBUG_TAG = "AppData";

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase db;
    private SQLiteOpenHelper myDBHelper;
    private static final String[] allColumns = {
            StatesDBHelper.QUESTIONS_COLUMN_ID,
            StatesDBHelper.QUESTIONS_COLUMN_STATE,
            StatesDBHelper.QUESTIONS_COLUMN_CAPITAL,
            StatesDBHelper.QUESTIONS_COLUMN_CITY1,
            StatesDBHelper.QUESTIONS_COLUMN_CITY2,
            StatesDBHelper.QUIZZES_COLUMN_ID,
            StatesDBHelper.QUIZZES_COLUMN_DATE,
            StatesDBHelper.QUIZZES_COLUMN_Q1,
            StatesDBHelper.QUIZZES_COLUMN_Q2,
            StatesDBHelper.QUIZZES_COLUMN_Q3,
            StatesDBHelper.QUIZZES_COLUMN_Q4,
            StatesDBHelper.QUIZZES_COLUMN_Q5,
            StatesDBHelper.QUIZZES_COLUMN_Q6,
            StatesDBHelper.QUIZZES_COLUMN_SCORE
    };

    public StatesData( Context context ) {
        this.myDBHelper = StatesDBHelper.getInstance( context );
    }

    // Open the database
    public void open() {
        db = myDBHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "AppData: db open" );
    }

    // Close the database
    public void close() {
        if( myDBHelper != null ) {
            myDBHelper.close();
            Log.d(DEBUG_TAG, "JobLeadsData: db closed");
        }
    }

    // Store a new question in the DB
    public Question storeQuestion( Question question ){
        ContentValues values = new ContentValues();
        values.put( StatesDBHelper.QUESTIONS_COLUMN_STATE, question.getState());
        values.put( StatesDBHelper.QUESTIONS_COLUMN_CAPITAL, question.getCapital() );
        values.put( StatesDBHelper.QUESTIONS_COLUMN_CITY1, question.getCity1() );
        values.put( StatesDBHelper.QUESTIONS_COLUMN_CITY2, question.getCity2() );



        long id = db.insert( StatesDBHelper.TABLE_QUESTIONS, null, values );

        // store the id (the primary key) in the JobLead instance, as it is now persistent
        question.setId( id );

        Log.d( DEBUG_TAG, "Stored new question with id: " + String.valueOf( question.getId() ) );

        return question;
    }

    public void deleteQuestions(){
        db.delete(StatesDBHelper.TABLE_QUESTIONS,null,null);
        db.execSQL( "DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + StatesDBHelper.TABLE_QUESTIONS + "'" );
    }
}
