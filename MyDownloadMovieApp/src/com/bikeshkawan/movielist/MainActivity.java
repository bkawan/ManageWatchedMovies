package com.bikeshkawan.movielist;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bikeshkawan.movielist.database.MovieDataSource;
import com.bikeshkawan.movielist.model.Movie;

public class MainActivity extends ActionBarActivity implements
		MovieListFragment.Callbacks {

	// String constant For the Movie_Bundle Reference
	public static final String MOVIE_BUNDLE = "MOVIE_BUNDLE";

	// String LOGTAG constant use to output to the LogCat Console

	public static final String LOGTAG = "MainActivity";

// store request code 
	private static final int REQUEST_CODE = 100;

	// store id for Quit Menu
	private static final int DIALOG_QUIT = 0;

	// boolean false for device in landscape mode
	private boolean isTwoPane = false;
	// variable to store instance of MovieDataSource
	MovieDataSource datasource;
	//variable to store  instance Movie
	Movie listMovie;
	 public static boolean actList = false;
	    public static boolean first = true;

	/**
	 * This is called when activity first start
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set the activity content from a layout resource which will be
				// inflated, adding all top-level views to the activity.
		setContentView(R.layout.activity_main);
		///////////
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		/////////////////////////////
		// check if the activity_main layout has id called detailContainer

		if (findViewById(R.id.detailContainer) != null) {
			// if resource id detalContainer exist the device is in landscape
			// mode
			isTwoPane = true;
		}


	}

	/**
	 * 
	 * @return a Resources instance for  application's package ie landscape
	 *         orientiation
	 */
	public boolean isMultiPane() {
		return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
	}

	//Initialize the contents of the Activity's custom options menu.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// get the resource id for the menu and inflate
		getMenuInflater().inflate(R.menu.main, menu);

		return true; // return menu
	}

	
	//This method is called whenever an item in  options menu is selected.
	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// get the resource id of the menu
		switch (item.getItemId()) {
        // if add Movie is clicked
		case R.id.action_add_new_movie:

			//Create an intent in current Activity as contest  AddNewMovieActivity  as The component class that is to be used 
			Intent i = new Intent(this, AddNewMovieActivity.class);
			// start Activity with intent
			startActivity(i);
			break;
			// if user clicked Refresh option menu
		case R.id.refresh:
			// store the android build version
			int currentapiVersion = android.os.Build.VERSION.SDK_INT;
			// check the android build version of current device 
			if (currentapiVersion > android.os.Build.VERSION_CODES.GINGERBREAD_MR1){
				// if the current device is greater than api level 11 that is GingerVread_MR1
				// Cause this Activity to be recreated with a new instance
				recreate();
			} else{
				// api level below 11 doesn't support recreate
				// if the current device is less  than api level 11 that is GingerVread_MR1
				//Create an intent in current Activity as contest  MainActivity  as The component class that is to be used 
				Intent ii = new Intent(this, MainActivity.class);
				// start activity with intent
				startActivity(ii);
			}
		

			break;

			// if quit menu is clicked
		case R.id.quit_app:
			// show Quit Dialog 
			showDialog(DIALOG_QUIT);
			break;

		default:
			break;
		}
		return false;
	}

	@Override
	public void onItemSelected(Movie movie) {
		// Store movie data in a bundle
		Bundle b = movie.toBundle();

		movie = listMovie;
		// perform onItemSeleted within if else condition
		// check if the device is in horizontal mode
		if (isTwoPane) {
			// if device is in horizontal mode
			// instantiate MovieDetailFragment

			MovieDetailFragment frag = new MovieDetailFragment();
			// Supply the bundle as construction arguments for this fragment.
			frag.setArguments(b);
			/**
			 * Call FragmentManager for interacting with fragments associated
			 * with this activity. Start a series of edit operations on the
			 * Fragments associated with this FragmentManager.
			 */
			getSupportFragmentManager().beginTransaction()
			// the view to be replaced
			// fragment to be replaced
			  
					.replace(R.id.detailContainer, frag, "DETAIL")
					// commit Transaction
					.commit();
		} else {
			//Create an intent in current Activity as contest  DetailActivity  as The component class that is to be used 

			Intent i = new Intent(this, DetailActivity.class);
			// Add extended data to the intent with the name of extra data and
			// the Movie Bundle
			i.putExtra(MOVIE_BUNDLE, b);
			// Launch an activity for which we would like a result when it
			// finished.
			// pass the intent to start 
			//If >= 0, this code will be returned in onActivityResult() when the activity exits.
			startActivityForResult(i, REQUEST_CODE);

		}
	}

	/**
	 * Called when a key down event has occurred.
	 * @param keyCode
	 *            The value in event.getKeyCode().
	 * @param event
	 *            Description of the key event.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// keyCode is equal to Back key
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//show alert dialog
			showDialog(DIALOG_QUIT);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * This method is invoked when user click the quit menu 
	 */
	protected final Dialog onCreateDialog(final int id) {
		// to store the instance of Dialog
		Dialog dialog = null;
		// get string id
		switch (id) {
		// if id is DIALOG_QUIT
		case DIALOG_QUIT:
			// Instantiate an AlertDialog.Builder with its constructor
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			// Chain together various setter methods to set the dialog
			// characteristics
			// disable Cancelable
			builder.setCancelable(false)
			// Set Title for Dialog
					.setTitle("Quit")
					// set Message for Dialog
					.setMessage("Do You Want Quit MovieList APP")
					// Set a listener to be invoked when the Yes button of the
					// dialog
					// is pressed.
					.setPositiveButton("YES",
							new DialogInterface.OnClickListener() {
								// This method will be invoked when NO button in
								// the dialog
								// is clicked.
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									// close the app and release memory
									android.os.Process
											.killProcess(android.os.Process
													.myPid());
									System.exit(1);

								}
							});
			// Set a listener to be invoked when the Yes button of the dialog
			// is pressed.
			builder.setNegativeButton("NO",
					new DialogInterface.OnClickListener() {
						// This method will be invoked when NO button in the
						// dialog
						// is clicked.
						public void onClick(DialogInterface dialog, int id) {
							// cancel dialog
							dialog.cancel();
							// show message
							Toast.makeText(getApplicationContext(),
									"Great!! Welcome Back", Toast.LENGTH_LONG)
									.show();
						}
					});
			//Creates a AlertDialog with the arguments supplied to this builder
			AlertDialog alert = builder.create();
			// assign created alert dialog to dialog 
			dialog = alert;

			break;

		default:
			break;
		}
		return dialog;
	}

	//Called after onCreate(Bundle) — or after onRestart() when the activity had been stopped
    @Override
    public void onStart() {
		Log.v(LOGTAG, "in MainActivity onStart");
    	super.onStart();
    }
    
    //Called after onRestoreInstanceState(Bundle), onRestart(), or onPause(),
    //for  activity to start interacting with the user.
    @Override
    public void onResume() {
		Log.v(LOGTAG, "in MainActivity onResume");
    	super.onResume();
    }
    
    //Called as part of the activity lifecycle when an activity is going into the background, but has not (yet) been killed
    @Override
    public void onPause() {
		Log.v(LOGTAG, "in MainActivity onPause");
    	super.onPause();
    }
    
    //Called when you are no longer visible to the user
    @Override
    public void onStop() {
		Log.v(LOGTAG, "in MainActivity onStop");
    	super.onStop();
    }
    
    //This method is called before an activity may be killed so that when 
    //it comes back some time in the future it can restore its state.
    @Override
    public void onSaveInstanceState(Bundle outState) {
    	Log.v(LOGTAG, "in MainActivity onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    //Perform any final cleanup before an activity is destroyed
    @Override
    public void onDestroy() {
		Log.v(LOGTAG, "in MainActivity onDestroy");
    	super.onDestroy();

    }

}
