/**
 * 
 */
package com.bikeshkawan.movielist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bikeshkawan.movielist.model.Movie;

/**
 * @author bikeshkawan
 *
 */
public class MovieDetailFragment extends Fragment {


	// store instance of Movie
	Movie movie;
	
	   // String LOGTAG constant  use to output to the LogCat Console
	public static String TAG ="MovieDetailFragment";
	
	/**
	 *   No argument Constructor as sometimes it is required at runtime
	 */
	public MovieDetailFragment() {
		// TODO Auto-generated constructor stub
	}
	
	//Called when a fragment is first attached to its activity. onCreate(Bundle) will be called after this.
	@Override
	public void onAttach(Activity activity) {
		// logCat output
		Log.v(MainActivity.LOGTAG, "in MovieDetailFragment onAttach; activity is: " +
    			activity);
    	super.onAttach(activity);
		
	}

	/**
	 * Called to do initial creation of a fragment. 
	 * This is called after onAttach(Activity) and before onCreateView(LayoutInflater, ViewGroup, Bundle).
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		Log.v(MainActivity.LOGTAG, "in DetailsFragment onCreate. Bundle contains:");
    	if(savedInstanceState != null) {
            for(String key : savedInstanceState.keySet()) {
                Log.v(MainActivity.LOGTAG, "    " + key);
            }
    	}
    	else {
            Log.v(MainActivity.LOGTAG, "    savedInstanceState is null");
    	}
		super.onCreate(savedInstanceState);
		
		//store the arguments supplied to setArguments(Bundle) in a bundle, if any.
		Bundle b = getArguments();
		// check if Move data  bundle is not empty and contains  key of the movie object
		if(b !=null && b.containsKey(Movie.KEY)){
			
            //instantiate the Movie by passing the bundle as arguments
			movie = new Movie(b);
		}
	}

	

	//Called to have the fragment instantiate its user interface view.
	@Override
	public View onCreateView(LayoutInflater inflater, // object to inflate the xml layout file
			ViewGroup container,// view Group that contain the fragment ie is root element of the containing activity
			Bundle savedInstanceState) {// to restore the appearance of a fragment

		Log.v(MainActivity.LOGTAG,
				"in MovieDetailFragment onCreateView. container is " + container);
		//load the layout
		View v =inflater.inflate(R.layout.movie_detail_fragment,// passing resource id of custom xml layout file to inflate
				container,// view group the fragment is attached
				false);// not re-attaching to root element again
		
		// check if movie object contains its properties
		if(movie !=null){
			//if movie object contains its properties
			/**
			 *  invoke getter method of the movie object to get the properties and store then as a string
			 *  Dispaly the properties of movie object in appropriate format
			 */
			String movieHeader = "<b>" + "Movie Details for : " + "</b> " +"<i>" + movie.getKey()+ "<i>"; 
			String movieKey = "<b>" + "Movie Name : " + "</b> " + movie.getKey(); 
			String movieTitle = "<b>" + "Full Title : " + "</b> " + movie.getTitle() ; 
			String movieType = "<b>" + " Type : " + "</b> " + movie.getType() ; 
			String movieStory = "<b>" + "Story Outline : " + "</b> " + movie.getStory()+"."; 
			String movieRating = "<b>" + "Rating : " + "</b> " + movie.getRating(); 
			String movieLanguage = "<b>" + "Language : " + "</b> " + movie.getLanguage() ; 
			String movieRunTime = "<b>" + "Run Time : " + "</b> " + movie.getRunTime() + " minutes."; 

			/**Get the reference to UI widgets for TextView 
			Sets the string value of the TextView and display  in appropriate format
			*/
			TextView tvMovieHeader = (TextView) v.findViewById(R.id.tvMovieDetails);
			tvMovieHeader.setText(Html.fromHtml(movieHeader));
			
			TextView tvMovieKey = (TextView) v.findViewById(R.id.tvMovieKey);
			tvMovieKey.setText(Html.fromHtml(movieKey));

			TextView tvMovieTitle = (TextView) v.findViewById(R.id.tvMovieTitle);
			tvMovieTitle.setText(Html.fromHtml(movieTitle));

			TextView tvMovieType = (TextView) v.findViewById(R.id.tvMovieType);
			tvMovieType.setText(Html.fromHtml(movieType));

			TextView tvMovieStory = (TextView) v.findViewById(R.id.tvMovieStory);
			tvMovieStory.setText(Html.fromHtml(movieStory));

			TextView tvMovieRating = (TextView) v.findViewById(R.id.tvMovieRating);
			tvMovieRating.setText(Html.fromHtml(movieRating));

			TextView tvMovieLanguage = (TextView) v.findViewById(R.id.tvMovieLanguage);
			tvMovieLanguage.setText(Html.fromHtml(movieLanguage));

			TextView tvMovieRunTime = (TextView) v.findViewById(R.id.tvMovieRunTime);
			tvMovieRunTime.setText(Html.fromHtml(movieRunTime));

			
		}
		return v;
	}
	

	/**
	 * Called when a fragment is first attached to its activity.
	 * pass reference to current activity
	 */

    @Override
    public void onActivityCreated(Bundle savedState) {
    	Log.v(MainActivity.LOGTAG,
    			"in MovieDetailFragment onActivityCreated. savedState contains:");
    	if(savedState != null) {
            for(String key : savedState.keySet()) {
                Log.v(MainActivity.LOGTAG, "    " + key);
            }
    	}
    	else {
            Log.v(MainActivity.LOGTAG, "    savedState is null");
    	}
        super.onActivityCreated(savedState);
    }

	// Called when the Fragment is visible to the user.

    @Override
    public void onStart() {
    	Log.v(MainActivity.LOGTAG, "in MovieDetailFragment onStart");
    	super.onStart();
    }
    
	//Called when the Fragment is no longer resumed.

  @Override
  public void onResume() {
	  super.onResume();
	  MainActivity.actList=false;
	  
  }
  
	//Called when the Fragment is no longer resumed.

  @Override
  public void onPause() {
  	Log.v(MainActivity.LOGTAG, "in MovieDetailFragment onPause");
  	super.onPause();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
  	Log.v(MainActivity.LOGTAG, "in MovieDetailFragment onSaveInstanceState");
      super.onSaveInstanceState(outState);
  }

	// Called when the Fragment is no longer started.

  @Override
  public void onStop() {
  	Log.v(MainActivity.LOGTAG, "in MovieDetailFragment onStop");
  	super.onStop();
  }

  /**
	 * Called when the view previously created by 
	 * onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment.
	 */
  @Override
  public void onDestroyView() {
  	Log.v(MainActivity.LOGTAG, "in MovieDetailsFragment onDestroyView, view = " +
  			getView());
  	super.onDestroyView();
  }

//Called when the fragment is no longer in use. This is called after
	// onStop() and before onDetach().
  @Override
  public void onDestroy() {
  	Log.v(MainActivity.LOGTAG, "in MovieDetailFragment onDestroy");
  	super.onDestroy();
  }

	//Called when the fragment is no longer attached to its activity.

  @Override
  public void onDetach() {
  	Log.v(MainActivity.LOGTAG, "in MovieDetailFragment onDetach");
  	super.onDetach();
  }
}
