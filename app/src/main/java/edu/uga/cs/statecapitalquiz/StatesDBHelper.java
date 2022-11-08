package edu.uga.cs.statecapitalquiz;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StatesDBHelper extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "StatesDBHelper";

    private static final String DB_NAME = "states.db";
    private static final int DB_VERSION = 1;

    // Define all names (strings) for table and column names.
    // This will be useful if we want to change these names later.
    public static final String TABLE_STATES = "states";
    public static final String STATES_COLUMN_ID = "_id";
    public static final String STATES_COLUMN_NAME = "name";
    public static final String STATES_COLUMN_CAPITAL = "capital";
    public static final String STATES_COLUMN_CITY1 = "city1";
    public static final String STATES_COLUMN_CITY2 = "city2";
    public static final String STATES_COLUMN_STATEHOOD = "statehood";
    public static final String STATES_COLUMN_CAPITALSINCE = "capitalsince";
    public static final String STATES_COLUMN_CAPITALRANK = "capitalrank";

    // This is a reference to the only instance for the helper.
    private static StatesDBHelper helperInstance;

    // A Create table SQL statement to create a table for job leads.
    // Note that _id is an auto increment primary key, i.e. the database will
    // automatically generate unique id values as keys.
    private static final String CREATE_STATES =
            "create table " + TABLE_STATES + " ("
                    + STATES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + STATES_COLUMN_NAME + " TEXT, "
                    + STATES_COLUMN_CAPITAL + " TEXT, "
                    + STATES_COLUMN_CITY1 + " TEXT, "
                    + STATES_COLUMN_CITY2 + " TEXT, "
                    + STATES_COLUMN_STATEHOOD + " TEXT, "
                    + STATES_COLUMN_CAPITALSINCE + " TEXT, "
                    + STATES_COLUMN_CAPITALRANK + " TEXT"
                    + ")";

    // Note that the constructor is private!
    // So, it can be called only from
    // this class, in the getInstance method.
    private StatesDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Access method to the single instance of the class.
    // It is synchronized, so that only one thread can executes this method, at a time.
    public static synchronized StatesDBHelper getInstance(Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new StatesDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    // We must override onCreate method, which will be used to create the database if
    // it does not exist yet.
    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_STATES );
        Log.d( DEBUG_TAG, "Table " + TABLE_STATES + " created" );
    }

    // We should override onUpgrade method, which will be used to upgrade the database if
    // its version (DB_VERSION) has changed.  This will be done automatically by Android
    // if the version will be bumped up, as we modify the database schema.
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_STATES );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_STATES + " upgraded" );
    }
}
