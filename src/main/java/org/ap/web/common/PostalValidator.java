package org.ap.web.common;

import java.util.regex.Pattern;

public class PostalValidator implements IValidator {

	/* STATIC */

	private static final PostalValidator _INSTANCE = new PostalValidator();

	public static PostalValidator getInstance() {
		return _INSTANCE;
	}
	
	/* ATTRIBUTES */
	
	private Pattern _pattern;

	/* CONSTRUCTOR */

	private PostalValidator() {
		_pattern = Pattern.compile("\\d{5}");
	}

	/* METHODS */

	public boolean isValid(String phone) {
		String formatted = phone.replace(" " , "").replace("." , "").replace("-" , "");
		return _pattern.matcher(formatted).matches();
	}
}
