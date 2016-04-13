/**
 * 
 */
package com.bikeshkawan.movielist;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bikeshkawan.movielist.database.MovieDataSource;
import com.bikeshkawan.movielist.model.Movie;
import com.bikeshkawan.movielist.validation.Validation;

/**
 * @author bikeshkawan
 *
 */
public class AddNewMovieActivity extends ActionBarActivity implements
		OnClickListener {

	protected static final String LOGTAG = "AddNewMovieActivity";
	// variables to store instance of EditText
	private EditText editTextKey;
	private EditText editTextMovieTitle;
	private EditText editTextMovieStory;
	private EditText editTextMovieLanguage;
	private EditText editTextMovieRunTime;

	// variables to store instance of Spinner
	private Spinner spinnerMovieType;
	private Spinner spinnerMovieRating;

	// variables to store instance of Button
	private Button buttonReset;
	private Button buttonCancel;
	private Button buttonSave;

	// variable to store instance of MovieDataSource
	MovieDataSource dataSource;

	// called when activity is started
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set the activity content from a layout resource which will be
		// inflated, adding all top-level views to the activity.
		setContentView(R.layout.activity_add_new);

		
		// enable to show the user that selecting home will return one level up
		// rather than to the top level of the app
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	///////////
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		/////////////////////////////
		// Instantiate the MovieDataSource passing current activity as context
		dataSource = new MovieDataSource(this);
		// call open method to open Database
		dataSource.open();

		// register all the view when activity start
		registerViews();
	}

	private void registerViews() {
		// ///////////////////////////////////////////////
		// ##### Validation for movie key
		// Get references to EditText widget for movie key by Resource id
		editTextKey = (EditText) findViewById(R.id.editTextKey);

		// Adds a TextWatcher to the list of those whose methods are called
		// whenever editTextKey text changes.
		editTextKey.addTextChangedListener(new TextWatcher() {

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start are about to be replaced by new
			 * text with length after.
			 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start have just replaced old text that
			 * had length before.
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			/**
			 * This method is called to notify you that, somewhere within s, the
			 * text has been changed.
			 */
			@Override
			public void afterTextChanged(Editable s) {
				// validate Movie Key
				Validation.isEditTextKey(editTextKey);

			}
		});

		// ///////////////////////////////////////
		// ##### Movie Title
		// Get references to EditText widget for movie Title by Resource id

		editTextMovieTitle = (EditText) findViewById(R.id.editTextMovieTitle);

		// Adds a TextWatcher to the list of those whose methods are called
		// whenever editTextMovieTitle text changes.
		editTextMovieTitle.addTextChangedListener(new TextWatcher() {

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start are about to be replaced by new
			 * text with length after.
			 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start have just replaced old text that
			 * had length before.
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			/**
			 * This method is called to notify you that, somewhere within s, the
			 * text has been changed.
			 */

			@Override
			public void afterTextChanged(Editable s) {
				// Validate Movie Length
				Validation.isEditTextTitleStory(editTextMovieTitle);

			}
		});
		// ///////////////////////////////////////
		// ##### Story
		// Get references to EditText widget for movie story outline by Resource
		// id

		editTextMovieStory = (EditText) findViewById(R.id.editTextMovieStory);

		// Adds a TextWatcher to the list of those whose methods are called
		// whenever editTextMovieStory text changes.
		editTextMovieStory.addTextChangedListener(new TextWatcher() {

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start are about to be replaced by new
			 * text with length after.
			 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start have just replaced old text that
			 * had length before.
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			/**
			 * This method is called to notify you that, somewhere within s, the
			 * text has been changed.
			 */
			@Override
			public void afterTextChanged(Editable s) {
				// validate movie story outline
				Validation.isEditTextTitleStory(editTextMovieStory);

			}
		});
		// ///////////////////////////////////////
		// ##### Language

		// Get references to EditText widget for movie language by Resource id
		editTextMovieLanguage = (EditText) findViewById(R.id.editTextMovieLanguage);

		// Adds a TextWatcher to the list of those whose methods are called
		// whenever editTextMovieLanguage text changes.
		editTextMovieLanguage.addTextChangedListener(new TextWatcher() {

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start are about to be replaced by new
			 * text with length after.
			 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start have just replaced old text that
			 * had length before.
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			/**
			 * This method is called to notify you that, somewhere within s, the
			 * text has been changed.
			 */

			@Override
			public void afterTextChanged(Editable s) {
				Validation.isEditTextLanguage(editTextMovieLanguage);

			}
		});
		// ///////////////////////////////////////
		// ##### Language
		// //Get references to EditText widget for movie runing time by Resource
		// id
		editTextMovieRunTime = (EditText) findViewById(R.id.editTextMovieRunTime);

		// Adds a TextWatcher to the list of those whose methods are called
		// whenever editTextMovieRunTime text changes.
		editTextMovieRunTime.addTextChangedListener(new TextWatcher() {

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start are about to be replaced by new
			 * text with length after.
			 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			/**
			 * This method is called to notify you that, within s, the count
			 * characters beginning at start have just replaced old text that
			 * had length before.
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			/**
			 * This method is called to notify you that, somewhere within s, the
			 * text has been changed.
			 */

			@Override
			public void afterTextChanged(Editable s) {
				// Validate Movie Running Time
				Validation.isEditTextRunTime(editTextMovieRunTime);

			}
		});

		// Get references to UI widgets for movie by Resource id
		spinnerMovieType = (Spinner) findViewById(R.id.spinnerMovieType);
		editTextMovieTitle = (EditText) findViewById(R.id.editTextMovieTitle);
		editTextMovieStory = (EditText) findViewById(R.id.editTextMovieStory);
		spinnerMovieRating = (Spinner) findViewById(R.id.spinnerMovieRating);
		editTextMovieLanguage = (EditText) findViewById(R.id.editTextMovieLanguage);
		editTextMovieRunTime = (EditText) findViewById(R.id.editTextMovieRunTime);
		buttonSave = (Button) findViewById(R.id.buttonSave);
		buttonReset = (Button) findViewById(R.id.buttonReset);
		buttonCancel = (Button) findViewById(R.id.buttonCancel);

		// Register a callback to be invoked when this Button view is clicked
		buttonSave.setOnClickListener(this);
		buttonReset.setOnClickListener(this);
		buttonCancel.setOnClickListener(this);

	}

	/**
	 * Called when a view has been clicked.
	 * 
	 * @param v
	 *            The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		// get id to identify the views
		switch (v.getId()) {
		// Save button
		case R.id.buttonSave:
			// perform Validation check for all the EditText views
			if (checkValidation()) {
				// if validation success
				// call save movie life cycle
				new saveMovie().execute();

				// if validation failed
			} else {

				// Display the error message using tast
				Toast.makeText(getApplicationContext(),
						"Form Contain one or many error please check details",
						Toast.LENGTH_LONG).show();
			}
			break;

		// Reset Button is clicked
		case R.id.buttonReset:
			// call resetAllFields Method to reset all the EditText
			resetAllFields();
			// display message to user
			Toast.makeText(getApplicationContext(),
					"Your have reset the text field", Toast.LENGTH_LONG).show();
			break;

		// Cancel Button is clicked
		case R.id.buttonCancel:
			// called cancelDialog method
			cancelDialog();
			break;

		default:
			break;
		}

	}

	/**
	 * Reset all the EditText to empty if Reset Button is pressed
	 */
	private void resetAllFields() {
		editTextKey.setText("");
		editTextMovieTitle.setText("");
		editTextMovieStory.setText("");
		editTextMovieLanguage.setText("");
		editTextMovieRunTime.setText("");

	}

	// save movie life cycle
	/**
	 * 
	 * @author bikeshkawan This class is used to save movie using if user click
	 *         save button u
	 */
	private class saveMovie extends AsyncTask<Void, Void, Void> {

		// String LOGTAG constant use to output to the LogCat Console

		private static final String LOGTAG = "AddNewMovieActivity";

		// to store the instance of ProgressDialog that shows progress indicator
		// with text message
		private ProgressDialog pDialog;

		// store instance of Movie
		Movie movie;
		// variable to store string
		String newKey;

		/**
		 * Runs on the UI thread before doInBackground.
		 */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			// instantiate ProgressDialog by passing current Activity as context

			pDialog = new ProgressDialog(AddNewMovieActivity.this);
			// setMessage for ProgressDialog

			pDialog.setMessage("Saving...");
			// disable Cancelable for the ProgressDialog

			pDialog.setCancelable(false);

			// Show Saving dialog on user screen

			pDialog.show();
		}

		// perform a computation on a background thread
		@Override
		protected Void doInBackground(Void... arg0) {

			try {
				/**
				 * getting values from the EditText and spinner and convert them
				 * into String and store in String type variables for all the
				 * TextViews
				 */
				newKey = editTextKey.getText().toString();
				String newTitle = editTextMovieTitle.getText().toString();
				String newType = spinnerMovieType.getSelectedItem().toString();
				String newStory = editTextMovieStory.getText().toString();
				String newRating = spinnerMovieRating.getSelectedItem()
						.toString();
				String newLanguage = editTextMovieLanguage.getText().toString();
				String newRunTime = editTextMovieRunTime.getText().toString();

				// Instantiate Movie Class with no arguments method
				movie = new Movie();

				/**
				 * calling setter methods of movie object to set values by
				 * passing which we get from TextViews and stored in variables
				 * above
				 */

				movie.setKey(newKey);// set movie key
				movie.setTitle(newTitle);// set movie title
				movie.setType(newType);// set type of movie
				movie.setStory(newStory);// set movie story
				movie.setRating(newRating);// set rating for the movie
				movie.setLanguage(newLanguage);// set language types of the
												// movie
				movie.setRunTime(newRunTime);// set running time of the movies

			} catch (Exception e) {

				// LotCat Output to display error message
				Log.e(LOGTAG, "Error saving movie");
			}

			return null;
		}

		/**
		 * Runs on the UI thread after doInBackground. The specified result is
		 * the value returned by doInBackground. This method won't be invoked if
		 * the task was cancelled.
		 */
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			// dismiss Saving dialog
			pDialog.dismiss();
			/**
			 * Insert the movie object into database with if else , we created
			 * in in doing background method above
			 */
			// check if the movie with newKey is already exists in database
			if (dataSource.getMovie(newKey).isEmpty()) {
				// if does not exist insert into database table
				dataSource.insertMovie(movie);
				// after successfully inserted into database Confirmation dialog
				// is shown by calling saveDialog method
				saveDialog();
				// display the text for successfully inserted movie object
				Toast.makeText(
						getApplicationContext(),
						"Congrats!!New movie details  has been   sucessfully submitted",
						Toast.LENGTH_LONG).show();
			} else {
				/**
				 * If the movie object with newKey already exists in the
				 * database a Dialogbox is shown by calling alreadyDialog method
				 */

				alreadyDialog();
			}

		}
	}

	protected boolean checkValidation() {
		boolean ret = true;

		// if validation is not success set false
		if (!Validation.isEditTextKey(editTextKey))
			ret = false;
		if (!Validation.isEditTextTitleStory(editTextMovieTitle))
			ret = false;
		if (!Validation.isEditTextTitleStory(editTextMovieStory))
			ret = false;
		if (!Validation.isEditTextLanguage(editTextMovieLanguage))
			ret = false;
		if (!Validation.isEditTextRunTime(editTextMovieRunTime))
			ret = false;

		return ret;
	}

	// Initialize the contents of the Activity's custom options menu.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// inflate custom menu
		getMenuInflater().inflate(R.menu.add_new_movie_activity, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// This method is called when an item in your options menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Respond to the action bar's Up/Home button
		if (item.getItemId() == android.R.id.home) {
			// Up/Home button is clicked call CancelDialog method
			cancelDialog();

		}
		// Respond to the action bar's quit button
		if (item.getItemId() == R.id.quit_add) {
			// if action bar's quit button is clicked call cancelDialog method
			cancelDialog();

		}
		return true;

	}

	/**
	 * Called when a key down event has occurred.
	 * 
	 * @param keyCode
	 *            The value in event.getKeyCode().
	 * @param event
	 *            Description of the key event.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// keyCode is equal to Back key
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// back Key is pressed call cancelDialog method
			cancelDialog();
			// If user handled the event return true
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	// This method is called when user click the save button
	private void saveDialog() {

		/**
		 * instantiate AlertDialog.Builder by passing current activity as
		 * context to the Constructor for this builder and the AlertDialog it
		 * creates.
		 */
		AlertDialog.Builder builderSave = new AlertDialog.Builder(this);
		builderSave
		// set Title for the Alert DialogBox
				.setTitle("Saved Successfull")
				// setMessage to Alert Dialog Box
				.setMessage("Do you Want to Add another New Movie Detail ???")
				// disable cancelable
				.setCancelable(true)
				// Set a listener to be invoked when the No button of the dialog
				// is pressed.
				.setPositiveButton("NO", new DialogInterface.OnClickListener() {
					// This method will be invoked when NO button in the dialog
					// is clicked.
					public void onClick(DialogInterface dialog, int id) {
						// Destroy activity
						finish();
					}
				})
				// Set a listener to be invoked when the YES button of the
				// dialog is pressed.
				.setNegativeButton("YES",
						new DialogInterface.OnClickListener() {
							// This method will be invoked when NO button in the
							// dialog is clicked.
							public void onClick(DialogInterface dialog, int id) {
								// cancel the dialog
								dialog.cancel();
								// Cause this Activity to be recreated with a
								// new instance.
								//recreate();
								
								// api level below 11 doesn't support recreate
								// if the current device is less  than api level 11 that is GingerVread_MR1
								//Create an intent in current Activity as contest  MainActivity  as The component class that is to be used 
								Intent ii = new Intent(AddNewMovieActivity.this, AddNewMovieActivity.class);

								startActivity(ii);

							}
						});
		/**
		 * Creates a AlertDialog with the arguments supplied to this builder and
		 * Dialog.show()'s the dialog in the screen
		 */
		builderSave.show();
	}

	// This method is invoked when user clicked the save button and movie key
	// already exist in the database
	private void alreadyDialog() {
		/**
		 * instantiate AlertDialog.Builder by passing current activity as
		 * context to the Constructor for this builder and the AlertDialog it
		 * creates.
		 */
		AlertDialog.Builder builderalready = new AlertDialog.Builder(this);
		builderalready
		// Set Title for the Alert dialog Box
				.setTitle("Already Exists")
				// set Message for the alert Dialog
				.setMessage(
						" NewMovie Details Is Already Exists!! Do you Want to Modify It ???")
				// disable cancaleable for Dialog
				.setCancelable(false)
				// Set a listener to be invoked when the Yes button of the
				// dialog is pressed.
				.setNegativeButton("YES",
						new DialogInterface.OnClickListener() {
							// This method will be invoked when YES button in
							// the dialog is clicked.
							public void onClick(DialogInterface dialog, int id) {
								// LogCat output
								Log.v(LOGTAG, "  Modified Existing Movie");
								// dismiss the dialog
								dialog.dismiss();
							}
						})
				// Set a listener to be invoked when the Yes button of the
				// dialog is pressed.
				.setPositiveButton("NO", new DialogInterface.OnClickListener() {
					// This method will be invoked when NO button in the dialog
					// is clicked.
					public void onClick(DialogInterface dialog, int id) {
						Log.v(LOGTAG, "  Destroy Activity");

						// cancel the dialog
						dialog.cancel();
						// Finish Destroy Current Activity
						AddNewMovieActivity.this.finish();
					}
				});
		/**
		 * Creates a AlertDialog with the arguments supplied to this builder and
		 * Dialog.show()'s the dialog in the screen
		 */
		builderalready.show();
	}

	// This method is invoked when user click the cancel button
	private void cancelDialog() {
		/**
		 * instantiate AlertDialog.Builder by passing current activity as
		 * context to the Constructor for this builder and the AlertDialog it
		 * creates.
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// disable cancelable
		builder.setCancelable(false)
		// set Title for the dialog
				.setTitle("Cancel")
				// set Message for the Dialog
				.setMessage("Do You Want To Cancel Adding New Movie?")
				/** Set a listener to be invoked when the Yes button of the
				*dialog is pressed.
				*/
				.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
							/** This method will be invoked when YES button in
							 *the dialog is clicked.
							 */
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// Display the message
								Toast.makeText(
										getApplicationContext(),
										"Sorry!! Add Unsuccessfull!!You have cancel adding new movie detail",
										Toast.LENGTH_LONG).show();
								// Finish Destroy Current Activity
								AddNewMovieActivity.this.finish();

							}
						});

		/**
		 * Set a listener to be invoked when the NO button of the dialog is
		 * pressed.
		 */
		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			// This method will be invoked when NO button in the dialog is
			// clicked.

			public void onClick(DialogInterface dialog, int id) {
				// cancel the dialog
				dialog.cancel();
				// display The message
				Toast.makeText(getApplicationContext(),
						"Great!! Please Continue Adding Movie Details",
						Toast.LENGTH_LONG).show();
			}
		});
		/**
		 * Creates a AlertDialog with the arguments supplied to this builder and
		 * Dialog.show()'s the dialog in the screen
		 */
		builder.show();
	}

	// Called after onCreate(Bundle) — or after onRestart() when the activity
	// had been stopped
	@Override
	public void onStart() {
		Log.v(LOGTAG, "in AddNewMovieActivity onStart");
		super.onStart();
	}

	// Called after onRestoreInstanceState(Bundle), onRestart(), or onPause(),
	// for activity to start interacting with the user.
	@Override
	public void onResume() {
		Log.v(LOGTAG, "in AddNewMovieActivity onResume");
		super.onResume();
	}

	// Called as part of the activity lifecycle when an activity is going into
	// the background, but has not (yet) been killed

	@Override
	public void onPause() {
		Log.v(LOGTAG, "in AddNewMovieActivity onPause");
		super.onPause();
	}

	// Called when Activity no longer visible to the user
	@Override
	public void onStop() {
		Log.v(LOGTAG, "in AddNewMovieActivity  onStop");
		super.onStop();
	}

	// This method is called before an activity may be killed so that when
	// it comes back some time in the future it can restore its state.
	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.v(LOGTAG, "in AddNewMovieActivity onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	// Perform any final cleanup before an activity is destroyed
	@Override
	public void onDestroy() {
		Log.v(LOGTAG, "in AddNewMovieActivity onDestroy");
		super.onDestroy();

	}
}
