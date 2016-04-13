package com.bikeshkawan.movielist.validation;

import java.util.regex.Pattern;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

public class Validation {
	//REGULAR EXPRESSION

	private static final String KEY_REGEX = "^[0-9a-zA-Z]+$";
	private static final String LANGUAGE_REGEX = "^[a-zA-Z,.:; ]+$";
	private static final String RUNTIME_REGEX =  "^0*[1-9]\\d*$";
	//ERROR MESSAGE

	private static final String KEY_ERR_MSG= "is invalid!!(only Alpha-Numeric are allowed)";
	private static final String LANG_ERR_MSG= "is invalid!!(only Alphabetic are allowed)";
	private static final String RUNTIME_ERR_MSG= "is invalid!!(only Numeric Greater than 0 are allowed)";

	private static final String KEY_MAXLENGTH_ERR_MSG  = "should be equal or less than 10 character";
	private static final String MAXLENGTH_ERR_MSG  = "should be equal or less than 25 character";

	private static final String MINLENGTH  = "should be equal or greater than 3 character";



	private static final boolean REQUIRED = true;
	private static final String REQUIRED_MSG = "**Required field";


	public static boolean isEditTextKey(EditText editText){
		return isValidKey(editText, KEY_REGEX, KEY_ERR_MSG, REQUIRED);
	}
	public static boolean isEditTextTitleStory(EditText editText){
		return isValidTitleStory(editText,  REQUIRED);
	}



	public static boolean isEditTextLanguage(EditText editText){
		return isValidLanguage(editText, LANGUAGE_REGEX, LANG_ERR_MSG, REQUIRED);
	}

	public static boolean isEditTextRunTime(EditText editText){
		return isValidRunTime(editText, RUNTIME_REGEX, RUNTIME_ERR_MSG, REQUIRED);
	}









	// return true if the input field is valid based on parameter passed
	public  static boolean isValidKey(EditText editText, String keyRegex,
			String errMsg, boolean required) {
		String text = editText.getText().toString();

		//clearing the error if it was previously set by other values
		editText.setError(null);
		if (text.length()==0) {
			if(required){
				customeErrorStyle(editText, REQUIRED_MSG);
				//editText.setError(REQUIRED_MSG);
				return false;
			}
		}
		// filled field
		// pattern doesn’t match so returning false
		if (!Pattern.matches(keyRegex, text)) {
			editText.setError(errMsg);
			return false;
		}
		isMiniLength(editText);

		
		if( text.length() >=11){

			customeErrorStyle(editText, KEY_MAXLENGTH_ERR_MSG);
			//editText.setError(KEY_MAXLENGTH_ERR_MSG);
			return false;
		}
		return true;

	}


	private static boolean isValidTitleStory(EditText editText,
			boolean required) {
		String text = editText.getText().toString().trim();
		editText.setError(null);
		// TODO Auto-generated method stub


		//clearing the error if it was previously set by other values


		//
		if (text.length()==0) {
			if(required){
				customeErrorStyle(editText, REQUIRED_MSG);
				//editText.setError(REQUIRED_MSG);
				return false;
			}
		}
		isMiniLength(editText);
		isMaxLength(editText);
		return true;
	}



	private static boolean isValidLanguage(EditText editText,
			String languageRegex, String errMsg, boolean required) {
		String text = editText.getText().toString().trim();
		editText.setError(null);
		//clearing the error if it was previously set by other values

		if (text.length()==0) {
			if(required){
				customeErrorStyle(editText, REQUIRED_MSG);
				//editText.setError(REQUIRED_MSG);
				return false;
			}
		}
		// filled field
		// pattern doesn’t match so returning false
		if (!Pattern.matches(languageRegex, text)) {
			editText.setError(errMsg);
			return false;
		}
		isMaxLength(editText);
		isMiniLength(editText);

		
		return true;

	}

	private static boolean isValidRunTime(EditText editText, String runTimeRegex,
			String keyMsg, boolean required) {
		String text = editText.getText().toString().trim();
		editText.setError(null);


		if ((text.length()==0 )) {
			if(required){
				customeErrorStyle(editText, REQUIRED_MSG);
				//editText.setError(REQUIRED_MSG);
				return false;
			}
		}

		if (!Pattern.matches(runTimeRegex, text)) {
			customeErrorStyle(editText, RUNTIME_ERR_MSG);
			//editText.setError(RUNTIME_ERR_MSG);
			return false;
		}

		return true;
	}



//check mimimum lenght for key, title, srory, and lang
	private static boolean  isMiniLength(EditText editText){

		String text = editText.getText().toString();
		if(text.length() <=2 ){

			customeErrorStyle(editText, MINLENGTH);
			//editText.setError(MINLENGTH);
			return false;
		}
		return false;

	}
	
	
	//Check MaxMimum length for Title , story , and language
	private static boolean  isMaxLength(EditText editText){

		String text = editText.getText().toString();
		if( text.length() >=26){
			customeErrorStyle(editText, MAXLENGTH_ERR_MSG);
			//editText.setError(MAXLENGTH_ERR_MSG);
			return false;
		}


		return false;

	}
	private static  void customeErrorStyle(EditText editText, String string){
		int ecolor = 0xffff0000 ; //red color

		ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
		SpannableStringBuilder ssbuilder = new SpannableStringBuilder(string);
		ssbuilder.setSpan(fgcspan, 0, string.length(), 0);
		editText.setError(ssbuilder);


	}
}
