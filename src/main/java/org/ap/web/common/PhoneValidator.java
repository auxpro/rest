package org.ap.web.common;

import java.util.regex.Pattern;

public class PhoneValidator implements IValidator {

	/* STATIC */

	private static final PhoneValidator _INSTANCE = new PhoneValidator();

	public static PhoneValidator getInstance() {
		return _INSTANCE;
	}
	
	/* ATTRIBUTES */
	
	private Pattern _pattern;

	/* CONSTRUCTOR */

	private PhoneValidator() {
		_pattern = Pattern.compile("\\d{10}");
	}

	/* METHODS */

	public boolean isValid(String phone) {
		String formatted = phone.replace(" " , "").replace("." , "").replace("-" , "");
		return _pattern.matcher(formatted).matches();
	}
}
