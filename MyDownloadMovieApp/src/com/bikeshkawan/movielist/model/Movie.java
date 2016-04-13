/**
 * 
 */
package com.bikeshkawan.movielist.model;



import android.os.Bundle;


/**
 * @author bikeshkawan
 *
 */
public class Movie  {

	// String constants for field references
	public static final String ID="id";
	public static final String KEY = "key";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String STORY = "story";
	public static final String RATING = "rating";
	public static final String LANGUAGE = "language";
	public static final String RUNTIME = "runTime";

	


 // private fields of Movie class
	private long id;
	private String key;
	private String title;
	private String type;
	private String story;
	private String rating;
	private String language;
	private  String    runTime;
	
	/**
	 * @param id  id of movie
	 * @param key short title for movie
	 * @param title full title for movie 
	 * @param type type of movie 
	 * @param story story outline for movie 
	 * @param rating rating for movie 
	 * @param language languages of movie 
	 * @param runTime movie running time 
	 */
	public Movie(String key, String title, String type,
			String story, String rating, String language, String runTime) {
		super();
		
		this.key = key;
		this.title = title;
		this.type = type;
		this.story = story;
		this.rating = rating;
		this.language = language;
		this.runTime = runTime;
	}

	// Getters and Setters
	/**
	 * @return the id of movie 
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id of movie  to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the key  the short title of the movie 
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set  
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the story
	 */
	public String getStory() {
		return story;
	}

	/**
	 * @param story the story to set
	 */
	public void setStory(String story) {
		this.story = story;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the runTime
	 */
	public String getRunTime() {
		return runTime;
	}

	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}



	// no argument constructor 
	public Movie() {
	}
	
		
	/**
	 * Create from a bundle
	 * @param b A mapping from String values to various Parcelable types.
	 */
    public Movie(Bundle b) {
        if (b != null) {
            this.key = b.getString(KEY);
            this.title = b.getString(TITLE);
            this.type = b.getString(TYPE);
            this.story = b.getString(STORY);
            this.rating = b.getString(RATING);
            this.language = b.getString(LANGUAGE);
            this.runTime = b.getString(RUNTIME);
            
        }
    }

    
    /**
     * Package data for transfer between activities
     * @return b package data containing properties of Movie object
     */
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(KEY, this.key);
        b.putString(TITLE, this.title);
        b.putString(TYPE, this.type);
        b.putString(STORY, this.story);
        b.putString(RATING, this.rating);
        b.putString(LANGUAGE, this.language);
        b.putString(RUNTIME, this.runTime);
      
        return b;
    }

	// to provide an appropriate representation which displays the associated Movie key  
    @Override
    public String toString() {
        return key;
    }

 

}
