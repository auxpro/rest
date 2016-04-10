package org.ap.web.common;

public class EmailValidator implements IValidator {

	/* STATIC */

	private static final EmailValidator _INSTANCE = new EmailValidator();

	public static EmailValidator getInstance() {
		return _INSTANCE;
	}

	/* CONSTRUCTOR */

	private EmailValidator() {	}

	/* METHODS */

	public boolean isValid(String email) {
		return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email);
	}
}