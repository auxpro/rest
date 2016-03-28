package org.ap.web.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceBean extends UserBean {
	
	/* ATTRIBUTES */
	
	private String name;
	private String phone;
	private AddressBean adress;

	/* CONSTRUCTORS */
	
	public ServiceBean() {}
	
	/* METHODS */
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	
	public AddressBean getAdress() { return adress; }
	public void setAdress(AddressBean adress) { this.adress = adress; }
}
