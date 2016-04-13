package com.bikeshkawan.movielist;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bikeshkawan.movielist.model.Movie;

/**
 * 
 * @author bikeshkawan
 *
 * MovieListArrayAdapter class  take an array list of Movie objects and then display each movie using a custom layout.
 */
public class MovieListArrayAdapter extends ArrayAdapter<Movie> {
	

	//variable to store instance of Context
	private Context context;
	// variable to store list of movies
	private List<Movie> movies;

	/**
	 * 
	 * @param context to connect to current activity
	 * @param resource
	 * @param movies
	 */
	public MovieListArrayAdapter(Context context, int resource, List<Movie> movies) {
		super(context, resource, movies);
		this.context = context;
		this.movies = movies;
	}
	
	
	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Movie movie = movies.get(position);
		
		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.movie_list, null);
		
		
		
		TextView tv = (TextView) view.findViewById(R.id.tvRowKey);
		tv.setText((position + 1) + ".   " + movie.getKey());

		
		return view;
		
	}

	@Override
	public int getCount() {
		return movies.size();
	}

	@Override
	public Movie getItem(int position) {
		return movies.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	// this doesn't work instead i have used start mainactivity using intent to refresh the list
	@Override
	public void add(Movie movie) {
		movies.add(movie);
		notifyDataSetChanged();
		super.add(movie);
	}

	@Override
	public void remove(Movie movie) {
		movies.remove(movie);
		notifyDataSetChanged();
		super.remove(movie);
	}
}
