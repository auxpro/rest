package org.ap.web.rest.security;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import org.ap.web.rest.entity.UserBean;

public class UserSecurityContext implements SecurityContext, Principal {
	
	/* ATTRIBUTES */
	
	private UserBean _user;
	
	/* CONSTRUCTOR */
	
	public UserSecurityContext(UserBean user) {
		_user = user;
	}
	
	/* METHODS */
	
	// SecurityContext implementation //
	
	@Override
	public String getAuthenticationScheme() {
		return "apSec";
	}
	@Override
	public Principal getUserPrincipal() {
		return this;
	}
	@Override
	public boolean isSecure() {
		return true;
	}
	@Override
	public boolean isUserInRole(String role) {
		if (_user.getRoles() == null)
			return false;
		for (String userrole : _user.getRoles()) {
			if (userrole.equalsIgnoreCase(role)) {
				return true;
			}
		}
		return false;
	}
	
	// Principal Implementation //
	
	@Override
	public String getName() {
		return _user.getName();
	}
}
