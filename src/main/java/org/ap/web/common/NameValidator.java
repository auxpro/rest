package org.ap.web.common;

import java.util.regex.Pattern;

public class NameValidator implements IValidator {

	/* STATIC */

	private static final NameValidator _INSTANCE = new NameValidator();

	public static NameValidator getInstance() {
		return _INSTANCE;
	}
	
	/* ATTRIBUTES */
	
	private Pattern _pattern;

	/* CONSTRUCTOR */

	private NameValidator() {
		_pattern = Pattern.compile("\\d{10}");
	}

	/* METHODS */

	public boolean isValid(String phone) {
		String formatted = phone.replace(" " , "").replace("." , "").replace("-" , "");
		return _pattern.matcher(formatted).matches();
	}
}
