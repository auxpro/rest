package org.ap.web.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerBean {
	
	/* ATTRIBUTES */
	
	private String firstName;
	private String lastName;
	private AddressBean adress;

	/* CONSTRUCTORS */
	
	public CustomerBean() {}
	
	/* METHODS */
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstname) { this.firstName = firstname; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public AddressBean getAdress() { return adress; }
	public void setAdress(AddressBean adress) { this.adress = adress; }
}
