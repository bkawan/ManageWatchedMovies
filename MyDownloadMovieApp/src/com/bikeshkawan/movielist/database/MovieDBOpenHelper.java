package com.bikeshkawan.movielist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 */
public class MovieDBOpenHelper extends SQLiteOpenHelper {

    // String LOGTAG constant  use to output to the LogCat Console
	private static final String LOGTAG = "MovieDBOpenHelper";

    // String constant for for name of database
	private static final String DATABASE_NAME = "MyMovieList.db";
    // integer Constant for Database version
	private static final int DATABASE_VERSION = 1;

    // String constant for table name
	public static final String TABLE_MOVIES = "movies";
    // String constant for primary key column which is autoincrement and data type is integer
	public static final String COLUMN_ID = "id";
    //String constant for column key and data type is text not null and must be unique
	public static final String COLUMN_KEY = "key";
    //String constant for column  title and data type is text
	public static final String COLUMN_TITLE = "title";
    //String constant for column type and data type is text
	public static final String COLUMN_TYPE = "type";
    //String constant for column story and data type is text
	public static final String COLUMN_STORY = "story";
    //String constant for column rating and data type is text
	public static final String COLUMN_RATING = "rating";
    //String constant for column language and data type is text
	public static final String COLUMN_LANGUAGE = "language";
    //String constant for column runTime and data type is integer
	public static final String COLUMN_RUNTIME = "runTime";


    /**
     * String constant  for SQL Statement to create table 
     */
	private static final String TABLE_CREATE = 
			"create table " + TABLE_MOVIES
			+ " (" + COLUMN_ID + " integer primary key autoincrement, "
			+  COLUMN_KEY + " text not null unique, "
			+ COLUMN_TITLE + " text , "
			+ COLUMN_TYPE + " text , "
			+ COLUMN_STORY + " text , "
			+ COLUMN_RATING + " text , "
			+ COLUMN_LANGUAGE + " text , "
			+ COLUMN_RUNTIME + " int );";


    /**
     * explicit constructor method simplified version
     * @param context to connect to current Activity
     */
	public MovieDBOpenHelper(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

    /**
     *inherit method of superclass SQliteOpenHelper
     * @param db to manage SQLite database
     * called automatically by android SDK  when creating database table
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//execute SQL command  to create table
		db.execSQL(TABLE_CREATE);
		// LogCat output
		Log.i(LOGTAG, DATABASE_NAME+"Table has been created");
	}

    /**
     * inherit method of superclass SQliteOpenHelper
     * called automatically by android SDK  when creating database table
     * @param db  to manage SQLite database
     * @param oldVersion previous version of table
     * @param newVersion new version of table
     */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// execute SQL command fro dropping existing table verision and new table version
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
		// to re create the new table version again in this class not in other class
		onCreate(db);
	}

}
