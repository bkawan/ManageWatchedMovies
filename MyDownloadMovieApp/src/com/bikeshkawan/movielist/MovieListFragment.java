package com.bikeshkawan.movielist;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.bikeshkawan.movielist.database.MovieDataSource;
import com.bikeshkawan.movielist.model.Movie;

public class MovieListFragment extends ListFragment {

	@SuppressWarnings("unused")
	private MainActivity mainActivity = null;

	// variable to store the list of movies
	List<Movie> movies;
	// variable to store the instance of MovieListArrayAdapter
	MovieListArrayAdapter aa;
	// variable to store the call back interface
	private Callbacks activity;

	// variable to store the position of the view from the list
	public static int movieReq;
	// variable to to the instane of MovieDataSource
	private MovieDataSource datasource;

	// String LOGTAG constant use to output to the LogCat Console
	private static final String LOGTAG = " MovieListFragment";

	/**
	 * No argument Constructor as sometimes it is required at runtime
	 * 
	 */
	public MovieListFragment() {
	}

	/**
	 * Called when a fragment is first attached to its activity.
	 * pass reference to current activity
	 */

	@Override
	public void onAttach(Activity activity) {

		Log.v(MainActivity.LOGTAG,
				"in MovieListFragment onAttach; activity is: " + activity);

		super.onAttach(activity);

		this.activity = (Callbacks) activity;
	}

	@Override
	/**
	 * this is called when fragment starts
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			for (String key : savedInstanceState.keySet()) {
				Log.v(MainActivity.LOGTAG, "    " + key);
			}
		} else {
			Log.v(MainActivity.LOGTAG, "    savedInstanceState is null");
		}
		// open database in try catch block
		try {
			// instantiate MovieDataSource by passing Current activity as a context
			datasource = new MovieDataSource(getActivity());
			// open database
			datasource.open();

		} catch (Exception e) {
			// LogCat output if database can't be open
			Log.e(LOGTAG, "Error openning database");
		}

	}
	
//Called to have the fragment instantiate its user interface view.
	@Override
	public View onCreateView(LayoutInflater inflater, // object to inflate the xml layout file
			ViewGroup container,// view Group that contain the fragment ie is root element of the containing activity
			Bundle savedInstanceState) // to restore the appearance of a fragment
	{
		Log.v(MainActivity.LOGTAG,
				"in MovieListFragment onCreateView. container is " + container);
		View rootView = inflater.inflate(R.layout.movie_list_fragment, // passing resource id of custom xml layout file to inflate
				container,// view group the fragment is attached
				false);// not re-attaching to root element again
		return rootView;// view group inside movie_list_fragment
	}

	// this interface is used when a user click the movie item from the listview
	public interface Callbacks {
		public void onItemSelected(Movie movie);
	}

	// Called when the Fragment is visible to the user.
	@Override
	public void onStart() {
		super.onStart();
		try {
			// this is life cycle for getting the list of movies
			new GetMovies().execute();
		} catch (Exception e) {
			Log.e(LOGTAG, "Error resuming get movies unexcuted");
		}
	}

	// Called when the fragment is visible to the user and actively running.
	@Override
	public void onResume() {
		super.onResume();
		Log.v(MainActivity.LOGTAG, "in MovieListFragment onResume");

		MainActivity.actList = true;

	}

	//Called when the Fragment is no longer resumed.
	@Override
	public void onPause() {
		Log.v(MainActivity.LOGTAG, "in MovieListFragment onPause");
		super.onPause();
	}

	/**
	 * Called to ask the fragment to save its current dynamic state, 
	 * so it can later be reconstructed in a new instance of its process is restarted.
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.v(MainActivity.LOGTAG, "in MovieListFragment onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	/**
	 * This method will be called when an item in the list is selected.
	 * 
	 * @param l
	 *            The ListView where the click happened
	 * @param v
	 *            The view that was clicked within the ListView
	 * @param position
	 *            The position of the view in the list
	 * @param id
	 *            The row id of the item that was clicked
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		// local variable of data type Movie to store the element at the
		// specified location in listView
		Movie movie = movies.get(position);

		// call the onItemSelected method of call back interface and pass movie
		// object
		activity.onItemSelected(movie);
	}

	// Called when the Fragment is no longer started.
	@Override
	public void onStop() {
		Log.v(MainActivity.LOGTAG, "in MovieListFragment onStop");

		super.onStop();

	}

	
	/**
	 * Called when the view previously created by 
	 * onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment.
	 */
	@Override
	public void onDestroyView() {
		Log.v(MainActivity.LOGTAG, "in MovieListFragment onDestroyView");
		super.onDestroyView();
	}

	// Called when the fragment is no longer in use. This is called after
	// onStop() and before onDetach().
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(MainActivity.LOGTAG, "in MovieListFragment onDestroy");

		// close the database when destroying fragment in try catch block
		try {
			// call close method of datasource object
			datasource.close();
		} catch (Exception e) {
			// LogCat output if error in closing database
			Log.e(LOGTAG, "Problem closing database");

		}
	}

	
	//Called when the fragment is no longer attached to its activity.
	@Override
	public void onDetach() {
		Log.v(MainActivity.LOGTAG, "in MovieTitleFragment onDetach");
		super.onDetach();
		mainActivity = null;
	}

	// this method is called when user click the movie item for long time
	private void longClick() {
		// enable Enables long click events for current list view
		this.getListView().setLongClickable(true);
		// Register a callback for current list view to be invoked when an item
		// in this AdapterView has been clicked and held
		// pass the OnItemLongClickListener as The callback that will run
		this.getListView().setOnItemLongClickListener(
				new OnItemLongClickListener() {
					/**
					 * 
					 * @param adpter The AbsListView where the click happened
					 * @param v The view within the AbsListView that was     clicked
					 * @param position The position of the view in the movie list
					 * @param id The row id of the movie item that was clicked
					 *            
					 */
					public boolean onItemLongClick(AdapterView<?> adpter,
							View v, int position, long id) {

						movieReq = position; // store the position of the view in global variable
						selectionMenu(position);// call selectionMenu method by pass the position of the the view from the movie list
						return true; // true if the callback consumed the long click, false otherwise
					}
				});
	}

	/**
	 * 
	 * @param position
	 *            The position of the view in the movie list when user click the
	 *            list for longer time
	 */
	public void selectionMenu(final int position) {
		// store String object in array of CharSequence
		final CharSequence[] items = { "Open Details", "Delete" };

		/**
		 * instantiate AlertDialog.Builder by passing current activity as
		 * context to the Constructor for this builder and the AlertDialog it
		 * creates.
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Set Title for the Alert Dialog box
		builder.setTitle("Make your selection");
		/**
		 * @param items
		 *            CharSequence to be displayed in the dialog as the content,
		 * @paarm listener instantiate the Interface used to allow the creator
		 *        of a dialog to run some code when an item on the dialog is
		 *        clicked
		 */
		builder.setItems(items, new DialogInterface.OnClickListener() {
			/**
			 * This method will be invoked when a button in the dialog is
			 * clicked.
			 * 
			 * @param dialog
			 *            The dialog that received the click.
			 * @param item
			 *            the position of the item clicked
			 */
			public void onClick(DialogInterface dialog, int item) {
				// Do something with the selection
				// if the position of item in CharSequence Array is 0 it is Open
				// Deails
				if (item == 0) {
					// if Open Details is clicked
					// get the element at the specified location in Movie List.
					// and store as movie object
					Movie movie = movies.get(position);
					// pass the movie object as argument in onItemSelected
					// method of call back interface
					activity.onItemSelected(movie);

					// if the position of the item in CharSequence Array is 1 it
					// is Delete
				} else if (item == 1) {
					/**
					 * If delete is clicked pass position of the element from
					 * the specified location in Movie List to removeConfirm
					 * method
					 */
					removeConfirm(position);// Show Remove dialog box
				}
			}
		});
		// store AlertDialog created with the arguments supplied to this build
		AlertDialog alert = builder.create();
		// Start the dialog and display it on screen.
		alert.show();

	}

	/**
	 * This method is called when delete button is clicked
	 * 
	 * @param position
	 *            the element from the specified location in Movie List
	 */
	public void removeConfirm(final int position) {
		/**
		 * instantiate AlertDialog.Builder by passing current activity as
		 * context to the Constructor for this builder and the AlertDialog it
		 * creates.
		 */
		AlertDialog.Builder buildertwo = new AlertDialog.Builder(getActivity());
		// set Title to be displayed for the AlertDialog box
		buildertwo.setTitle("Delete Confirmation");
		// set Message to be displayed for the AlertDialog Box
		buildertwo.setMessage("Are you sure you want to delete this movie");
		/**
		 * Set a listener to be invoked when the positive button call yes of the
		 * dialog is pressed.
		 * 
		 * @param YES
		 *            The text to display in the positive button
		 * @param listener
		 *            The DialogInterface.OnClickListener to use.
		 */
		buildertwo.setPositiveButton("YES",
				new DialogInterface.OnClickListener() {

					/**
					 * This method will be invoked when a button in the dialog
					 * is clicked.
					 *
					 * @param dialog
					 *            The dialog that received the click.
					 * @param id
					 *            The button that was clicked
					 */
					@Override
					public void onClick(DialogInterface dialog, int id) {
						// Delete movie process in Try Catch block
						try {
							Log.d(LOGTAG, "The Movie Successfully Removed");

							// get the element at the specified location in
							// Movie List. and store as movie object
							Movie movie = movies.get(position);
							// call the removeMovie Method and pass the move
							// object as argument and delete the movie
							datasource.removeMovie(movie);
							// After movie is delete call findAll Method for
							// datasource object and find all the movies from
							// database
							// store all found movies as a list global variable
							movies = datasource.findAll();

							aa = new MovieListArrayAdapter(getActivity(),// pass the current Activity as a context
									R.layout.movie_list,// pass the Resource id for xml layout for  movie list
									movies);// pass the list of movie object

							// Bind our custom array adapter to the listview in
							// ListFragment
							setListAdapter(aa);

							/**
							 * Notifies the attached observers that the
							 * underlying data has been changed and any View
							 * reflecting the data set should refresh itself.
							 */
							aa.notifyDataSetChanged();

							// set the position of the list view to 0
							movieReq = 0;
							// display the message to the current activity for
							// successful delete using Toast
							Toast.makeText(
									getActivity().getApplicationContext(),
									"The Movie Successfully Removed",
									Toast.LENGTH_LONG).show();
						} catch (Exception e) {
							// LogCat Output for err if movie can't be delete
							Log.e(LOGTAG, "Error deleting the movie");
						}

					}
					/**
					 * Set a listener to be invoked when the positive button
					 * call yes of the dialog is pressed.
					 * 
					 * @param No
					 *            The text to display in the positive button
					 * @param listener
					 *            The DialogInterface.OnClickListener to use.
					 */
				}).setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					/**
					 * This method will be invoked when a button in the dialog
					 * is clicked.
					 *
					 * @param dialog
					 *            The dialog that received the click.
					 * @param id
					 *            The button that was clicked
					 */
					public void onClick(DialogInterface dialog, int id) {
						// cancel the dialog
						dialog.dismiss();
					}
				});
		// store AlertDialog created with the arguments supplied to this build
		AlertDialog alert = buildertwo.create();
		// Start the dialog and display it on screen.
		alert.show();
	}

	/**
	 * 
	 * @author bikeshkawan
	 *
	 */
	private class GetMovies extends AsyncTask<Void, Void, Void> {
		// to store the instance of Progressdialog that shows progress indicator
		// with text message
		private ProgressDialog pDialog;

		@Override
		// Runs on the UI thread before doInBackground.
		protected void onPreExecute() {
			super.onPreExecute();
			// show please wait dialog in try catch block
			try {
				// instantiate ProgressDialog by passing current Activity as
				// context
				pDialog = new ProgressDialog(getActivity());
				// setMessate for ProgressDialog
				pDialog.setMessage("Please wait...");
				// disable Cancelable for the ProgressDialog
			
				pDialog.setCancelable(false);
				// Show Progress dialog
				pDialog.show();

			} catch (Exception e) {
			}
		}

		//perform a computation on a background thread
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating life cycle handler for getting list of movies within try catch block
			
			try {

				// find all the movies from database by calling findAll method
				// and store as list
				movies = datasource.findAll();

			} catch (Exception e) {
				// LogCat output for error if datasource object can't find all
				// the movies
				Log.e(MovieDataSource.LOGTAG, "Error getting movies");
			}

			return null;// return nothing
		}

		/**
		 * Runs on the UI thread after doInBackground. The specified result is
		 * the value returned by doInBackground. This method won't be invoked if
		 * the task was cancelled.but we have already disable cancelable
		 */
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			try {
				// close please wait dialog
				pDialog.dismiss();
				// instantiate the Our custom MovieListArrayAdapter
				aa = new MovieListArrayAdapter(getActivity(),// pass the current
																// Activity as
																// context
						R.layout.movie_list,// pass the Resource id for xml
											// layout for movie list
						movies);// pass the list of movie object

				// Bind our custom array adapter to the listview in ListFragment
				setListAdapter(aa);

				/**
				 * Notifies the attached observers that the underlying data has
				 * been changed and any View reflecting the data set should
				 * refresh itself.
				 */
				aa.notifyDataSetChanged();

				longClick();// call longClick Method

				/**
				 * Check the orientation of the device if the device is in
				 * landscape mode and size of movie list is greater than 0 show
				 * the first movie details in detailFragment
				 */
				if (MainActivity.first
						&& getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
						&& datasource.findAll().size() > 0) {
					Movie movie = movies.get(movieReq);
					activity.onItemSelected(movie);
					MainActivity.first = false;
				}
			} catch (Exception e) {

			}
		}

	}

	
	//Called by the system when the device configuration changes while your component is running.
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

	}

}
