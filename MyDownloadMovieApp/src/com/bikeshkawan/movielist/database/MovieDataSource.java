/**
 * 
 */
package com.bikeshkawan.movielist.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bikeshkawan.movielist.model.Movie;

/**
 * @author bikeshkawan
 *  This class manage SQLite and called by rest of the application 
 */

public class MovieDataSource {

    // String LOGTAG constant  use to output to the LogCat Console
	public static final String LOGTAG = "MovieDataSource";

	// Reference to my Database Helper class
	private MovieDBOpenHelper dbHelper;
	// Reference to Databae object  Actual Datbase Class
	private SQLiteDatabase database;
// Array of String  constant for  to identify all columns to be retrieved values from the table
	private static final String[] allColumns = { MovieDBOpenHelper.COLUMN_ID,
			MovieDBOpenHelper.COLUMN_KEY,
			MovieDBOpenHelper.COLUMN_TITLE,
			MovieDBOpenHelper.COLUMN_TYPE, 
			MovieDBOpenHelper.COLUMN_STORY,
			MovieDBOpenHelper.COLUMN_RATING, 
			MovieDBOpenHelper.COLUMN_LANGUAGE,
			MovieDBOpenHelper.COLUMN_RUNTIME };

	/**
	 * 
	 * Constructor with single argument
	 * @param context connect to current activity
	 */
	public MovieDataSource(Context context) {
		// Instantiate MovieDBopenHelper class 
		dbHelper = new MovieDBOpenHelper(context);

	}
/**
 * 
 * @throws SQLException indicate error with SQL parsing if any
 */
	public void open() throws SQLException {
		//logCat output
		Log.i(LOGTAG, "Databae Opened");
		// open the database connection 
		database = dbHelper.getWritableDatabase();
	}
/**
 * 
 */
	public void close() {
		//logCat output
		Log.i(LOGTAG, "Databae Closed");
		// close the database connection
		database.close();

	}

	/**
	 * method to Insert Movie Type of Data in 
	 * @param movie argument with Data type of Movie
	 * @return instance of my Movie Class
	 */
	public Movie insertMovie(Movie movie) {

		/**Instead of executing explicit SQL Statement, we can instantiate  ContentValues Class
		 * 
		 * movieValues will store set of values we want to insert into database.
		**/
		ContentValues movieValues = new ContentValues();
		/**
		 * Adds a value to the set.
		 * Parameters:(key, value)
		 * key the name of the value to put ie  name of the column 
		 * for column we can call constant that is store in MovieDBOpenHelper Class
		 * 
		 * value the data for the value to put ie the value we want to insert,
		 * for value the movie argument we passed into this method will call getters method of Movie class
		 * 
		 */
		movieValues.put(MovieDBOpenHelper.COLUMN_KEY, movie.getKey());
		movieValues.put(MovieDBOpenHelper.COLUMN_TITLE, movie.getTitle());
		movieValues.put(MovieDBOpenHelper.COLUMN_TYPE, movie.getType());
		movieValues.put(MovieDBOpenHelper.COLUMN_STORY, movie.getStory());
		movieValues.put(MovieDBOpenHelper.COLUMN_RATING, movie.getRating());
		movieValues.put(MovieDBOpenHelper.COLUMN_LANGUAGE, movie.getLanguage());
		movieValues.put(MovieDBOpenHelper.COLUMN_RUNTIME, movie.getRunTime());

		/**
		 * now ready to insert row values ie movieValues into database by calling insert method and 
		 * is going to return long value  for  ID column which is stored in variable insertID
		 */
		long insertID = database.insert(MovieDBOpenHelper.TABLE_MOVIES,//the table to insert the row into
				null,//null value for nullColumnHack so that if empty values is passed name of column is passed
				movieValues // column values to be inserted which is stored in ContentValues object 
				);

		// return row id stored in insertId and assigning the value to our movie object calling setId method
		movie.setId(insertID);
		// return movie object
		return movie;
	}

	

	/**
	 * 
	 * @param  cursor 
	 * @return movies list of all movie
	 */
	private List<Movie> cursorToList(Cursor cursor) {
		// storing list the movies by instantiating ArrayList Constructor Method of Movie Type data
		List<Movie> movies = new ArrayList<Movie>();
		/**
		 * Looping through the cursor with one row at a time 
		 * a cursor starts before the first row so we will run within if statement
		 */
		// check if the number of rows in the cursor. is greater than zero by using cursor.getCount method
		if (cursor.getCount() > 0) {
			// if condition is true loop through the rows with condition
			//Move the cursor to the next row.
			//This method will return false if the cursor is already past the last entry in the result set.
			while (cursor.moveToNext()) {
				// if successfully move to next row, instantiate  Movie object
				Movie movie = new Movie();
				/**
				 * grabbing values from the cursor and assigning values to movie object by passing through setter method
				 *  for setId  cursor   Returns the value of the requested column  as a long and set to setId
				 *  for all other  cursor Returns the value of the requested column as a String and set to all setters
				 * 
				 */
				movie.setId(cursor.getLong(cursor 
						.getColumnIndex(MovieDBOpenHelper.COLUMN_ID))); 
				movie.setKey(cursor.getString(cursor
						.getColumnIndex(MovieDBOpenHelper.COLUMN_KEY)));
				movie.setTitle(cursor.getString(cursor
						.getColumnIndex(MovieDBOpenHelper.COLUMN_TITLE)));
				movie.setType(cursor.getString(cursor
						.getColumnIndex(MovieDBOpenHelper.COLUMN_TYPE)));
				movie.setStory(cursor.getString(cursor
						.getColumnIndex(MovieDBOpenHelper.COLUMN_STORY)));
				movie.setRating(cursor.getString(cursor
						.getColumnIndex(MovieDBOpenHelper.COLUMN_RATING)));
				movie.setLanguage(cursor.getString(cursor
						.getColumnIndex(MovieDBOpenHelper.COLUMN_LANGUAGE)));
				movie.setRunTime(cursor.getString(cursor
						.getColumnIndex(MovieDBOpenHelper.COLUMN_RUNTIME)));

				
				/**
				 * after looping through one rows at at time to all the rows then rows data is added to Movie object call movie
				 * then Movie object ie movie is add to list of movies using add method.
				 */
				
				movies.add(movie); 
			}
		}
		return movies;
	}
	
	/**
	 * 
	 * @return a list of Movie Data type 
	 */
	public List<Movie> findAll() {

		// cursor A Cursor object  which   stores the reference to the data that is returned from the Query
		Cursor cursor = database.query(
				MovieDBOpenHelper.TABLE_MOVIES, // Table to query
				allColumns,//Array list  of columns to return
				null,// null for selection parameter  of the column for  where clause and  return all rows for the given table.
				null, // null for selectionArgs parameter the values for the  where clause
				null, // don't group the rows for the  groupBy parameter 
				null,//don't filter by  row groups for the  having parameter 
				null // don't order the rows for orderedBy parameter
				);

		// logCat output to return list of movie object
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
		//
		List<Movie> movies = cursorToList(cursor);
		return movies;

	}

	public boolean removeMovie(Movie movie) {
		String where = MovieDBOpenHelper.COLUMN_ID + "=" + movie.getId();
		int result = database.delete(MovieDBOpenHelper.TABLE_MOVIES, where,
				null);

		return (result == 1);

	}

	public List<Movie> getMovie(String movie) {

		Cursor cursor = database.query(MovieDBOpenHelper.TABLE_MOVIES,
				allColumns, 
				"key like " + "'" + movie + "'",// where clause to check movie key
				null, // null for selectionArgs parameter the values for the  where clause
				null, // don't group the rows for the  groupBy parameter 
				null,//don't filter by  row groups for the  having parameter 
				null // don't order the rows for orderedBy parameter
				);
		List<Movie> movieget = cursorToList(cursor);
		return movieget;
	}

}
