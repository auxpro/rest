package org.ap.web.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressBean {
	
	/* ATTRIBUTES */
	
	private String address;
	private int postalCode;
	private String city;

	/* CONSTRUCTORS */
	
	public AddressBean() {}
	
	/* METHODS */
	
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	
	public int getPostalCode() { return postalCode; }
	public void setPostalCode(int postalCode) { this.postalCode = postalCode; }
	
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
}
