package org.ap.web.internal;

import javax.ws.rs.core.Response.Status;

public class APException extends Exception {

	/* STATIC */

	private static final long serialVersionUID = 3941716161128190382L;

	public static final APException NOT_IMPLEMENTED = new APException("NOT_IMPLEMENTED", Status.NOT_IMPLEMENTED);

	public static final APException INVALID_USER = new APException("INVALID_USER", Status.UNAUTHORIZED);
	public static final APException USER_NOT_FOUND = new APException("USER_NOT_FOUND", Status.NOT_FOUND);
	public static final APException USER_NOT_DELETED = new APException("USER_NOT_DELETED", Status.INTERNAL_SERVER_ERROR);
	public static final APException USER_NOT_ADDED = new APException("USER_NOT_ADDED", Status.INTERNAL_SERVER_ERROR);

	public static final APException USER_ID_INUSE = new APException("USER_ID_INUSE", Status.PRECONDITION_FAILED);
	public static final APException USER_NAME_INUSE = new APException("USER_NAME_INUSE", Status.PRECONDITION_FAILED);
	public static final APException USER_EMAIL_INUSE = new APException("USER_EMAIL_INUSE", Status.PRECONDITION_FAILED);

	/* ATTRIBUTES */

	private String _code;
	private Status _status;

	/* CONSTRUCTOR */

	public APException(String code, Status status) {
		this(code, code, status);
	}
	public APException(String msg, String code, Status status) {
		super(msg);
		_code = code;
		_status = status;
	}

	/* METHODS */

	public String getCode() {
		return _code;
	}
	public Status getStatus() {
		return _status;
	}
}
