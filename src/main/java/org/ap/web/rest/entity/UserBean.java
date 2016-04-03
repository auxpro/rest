package org.ap.web.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.ap.web.rest.security.annotation.PrivateInformation;
import org.ap.web.rest.security.annotation.SecretInformation;

@XmlRootElement
public class UserBean {
	
	/* ATTRIBUTES */
	
	private String name;
	private String password;
	private String email;
	private boolean active;
	private String type;

	/* CONSTRUCTORS */
	
	public UserBean() {}
	
	/* METHODS */
	
	@PrivateInformation
	public String getName() { return name; }
	public void setName(String username) { this.name = username; }
	
	@SecretInformation
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	@PrivateInformation
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	@PrivateInformation
	public boolean getActive() { return active; }
	public void setActive(boolean active) { this.active = active; }
	
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
}
