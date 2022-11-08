package edu.uga.cs.statecapitalquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StatesData {
    public static final String DEBUG_TAG = "StatesData";

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase db;
    private SQLiteOpenHelper statesDbHelper;
    private static final String[] allColumns = {
            StatesDBHelper.STATES_COLUMN_ID,
            StatesDBHelper.STATES_COLUMN_NAME,
            StatesDBHelper.STATES_COLUMN_CAPITAL,
            StatesDBHelper.STATES_COLUMN_CITY1,
            StatesDBHelper.STATES_COLUMN_CITY2,
            StatesDBHelper.STATES_COLUMN_STATEHOOD,
            StatesDBHelper.STATES_COLUMN_CAPITALSINCE,
            StatesDBHelper.STATES_COLUMN_CAPITALRANK
    };

    public StatesData(Context context ) {
        this.statesDbHelper = StatesDBHelper.getInstance( context );
    }

    // Open the database
    public void open() {
        db = statesDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "StatesData: db open" );
    }

    // Close the database
    public void close() {
        if( statesDbHelper != null ) {
            statesDbHelper.close();
            Log.d(DEBUG_TAG, "StatesData: db closed");
        }
    }

    public boolean isDBOpen()
    {
        return db.isOpen();
    }

    // Retrieve all states and return them as a List.
    // This is how we restore persistent objects stored as rows in the states table in the database.
    // For each retrieved row, we create a new State (Java POJO object) instance and add it to the list.
    public List<State> retrieveAllStateCapital() {
        ArrayList<State> states = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( StatesDBHelper.TABLE_STATES, allColumns,
                    null, null, null, null, null );

            // collect all states into a List
            if( cursor != null && cursor.getCount() > 0 ) {

                while( cursor.moveToNext() ) {

                    if( cursor.getColumnCount() >= 5) {

                        // get all attribute values of this state
                        columnIndex = cursor.getColumnIndex( StatesDBHelper.STATES_COLUMN_ID );
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex( StatesDBHelper.STATES_COLUMN_NAME );
                        String name = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StatesDBHelper.STATES_COLUMN_CAPITAL );
                        String capital = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StatesDBHelper.STATES_COLUMN_CITY1 );
                        String city1 = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StatesDBHelper.STATES_COLUMN_CITY2 );
                        String city2 = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StatesDBHelper.STATES_COLUMN_STATEHOOD );
                        String stateHood = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StatesDBHelper.STATES_COLUMN_CAPITALSINCE );
                        String capitalSince = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StatesDBHelper.STATES_COLUMN_CAPITALRANK );
                        String capitalRank = cursor.getString( columnIndex );

                        // create a new State object and set its state to the retrieved values
                        State state = new State( name, capital, city1, city2, stateHood, capitalSince, capitalRank );
                        state.setId(id); // set the id (the primary key) of this object
                        // add it to the list
                        states.add( state );
                        Log.d(DEBUG_TAG, "Retrieved State: " + state);
                    }
                }
            }
            if( cursor != null )
                Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
            else
                Log.d( DEBUG_TAG, "Number of records from DB: 0" );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // return a list of retrieved states
        return states;
    }

    // Store a new state in the database.
    public State storeState( State state ) {
        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the State argument.
        // This is how we are providing persistence to a State (Java object) instance
        // by storing it as a new row in the database table representing states.
        ContentValues values = new ContentValues();
        values.put(StatesDBHelper.STATES_COLUMN_NAME, state.getStateName());
        values.put(StatesDBHelper.STATES_COLUMN_CAPITAL, state.getCapital());
        values.put(StatesDBHelper.STATES_COLUMN_CITY1, state.getCity1());
        values.put(StatesDBHelper.STATES_COLUMN_CITY2, state.getCity2());
        values.put(StatesDBHelper.STATES_COLUMN_STATEHOOD, state.getStateHood());
        values.put(StatesDBHelper.STATES_COLUMN_CAPITALSINCE, state.getCapitalSince());
        values.put(StatesDBHelper.STATES_COLUMN_CAPITALRANK, state.getCapitalRank());

        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        long id = db.insert( StatesDBHelper.TABLE_STATES, null, values );

        // store the id (the primary key) in the State instance, as it is now persistent
        state.setId( id );

        Log.d( DEBUG_TAG, "Stored new state with id: " + String.valueOf( state.getId() ) );

        return state;
    }
}
