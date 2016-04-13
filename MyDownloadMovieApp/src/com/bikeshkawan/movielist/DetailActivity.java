package com.bikeshkawan.movielist;





import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;



public class DetailActivity extends ActionBarActivity {

//	Movie movie;
//	MovieDataSource datasource;
	private static final String TAG = "DetailActivity";
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(MainActivity.LOGTAG, "in DetailsActivity onCreate");
		// Set the activity content from a layout resource which will be
		// inflated, adding all top-level views to the activity.
		setContentView(R.layout.activity_movie_detail);
		// enable to show the user that selecting home will return one level up
				// rather than to the top level of the app
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	
		// do display app icon,back button, menu option at  action bar 
		ActionBar actionBar = getSupportActionBar();
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		
       // store the current configuration of the device
		int orientation = getResources().getConfiguration().orientation;
		// check orientation of the device 
		if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
			
			 // If the screen is now in landscape mode, it means
        	// that our MainActivity is being shown with both
        	// the titles and the text, so this activity is
        	// no longer needed. Bail out and let the MainActivity
        	// do all the work.
			// destroy this activity and go back to the "base" view if we are in landscape mode
			// this activity is only for portrait mode
			finish();
			return;
			
		}

		// check if savedInstance is null
		if(savedInstanceState == null){
       // if savedInstanceState is null
			// instantiate MovieDetailFragment
			MovieDetailFragment detailFragment = new MovieDetailFragment();
			// store the value of an item that previously added with putExtra() in bundle
			Bundle b = getIntent().getBundleExtra(MainActivity.MOVIE_BUNDLE);
			//Supply the bundle as  construction arguments for this fragment.
			detailFragment.setArguments(b);
			/**
			 * Call FragmentManager for interacting with fragments associated with this activity.
			 * Start a series of edit operations on the Fragments associated with this FragmentManager.
			 */
			getSupportFragmentManager().beginTransaction()
		
			// the view in which fragment to  insert it
			// fragment to be added
			.add(R.id.detailContainer, detailFragment)
			           
 // Commit the transaction
			.commit();
		}

		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.detail_activity, menu);
//		menu.findItem(R.id.delete);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.refr:
			// to refresh
			Intent i = new Intent(this, DetailActivity.class);
			// start Activity with intent
			startActivity(i);
			
//			datasource.removeMovie(movie);
//			setResult(-1);
//			finish();

			break;

		default:
			break;
		}

		return true;

	}
	
    

	//Called after onCreate(Bundle) — or after onRestart() when the activity had been stopped
    @Override
    public void onStart() {
		Log.v(TAG, "in DetailActivity onStart");
    	super.onStart();
    }
    
    //Called after onRestoreInstanceState(Bundle), onRestart(), or onPause(),
    //for  activity to start interacting with the user.
    @Override
    public void onResume() {
		Log.v(TAG, "in DetailActivity onResume");
    	super.onResume();
    }
    
    //Called as part of the activity lifecycle when an activity is going into the background, but has not (yet) been killed
    @Override
    public void onPause() {
		Log.v(TAG, "in DetailActivity onPause");
    	super.onPause();
    }
    
    //Called when you are no longer visible to the user
    @Override
    public void onStop() {
		Log.v(TAG, "in DetailActivity onStop");
    	super.onStop();
    }
    
    //This method is called before an activity may be killed so that when 
    //it comes back some time in the future it can restore its state.
    @Override
    public void onSaveInstanceState(Bundle outState) {
    	Log.v(TAG, "in DetailActivity onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    //Perform any final cleanup before an activity is destroyed
    @Override
    public void onDestroy() {
		Log.v(TAG, "in DetailActiviy onDestroy");
    	super.onDestroy();

    }
}
