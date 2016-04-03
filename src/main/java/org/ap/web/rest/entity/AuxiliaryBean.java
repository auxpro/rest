package org.ap.web.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.ap.web.internal.EUserType;

@XmlRootElement
public class AuxiliaryBean extends UserBean {
	
	/* ATTRIBUTES */
	
	private String firstName;
	private String lastName;
	private String phone;
	private AddressBean address;

	/* CONSTRUCTORS */
	
	public AuxiliaryBean() {
		setType(EUserType.AUXILIARY.getId());
	}
	
	/* METHODS */
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstname) { this.firstName = firstname; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	
	public AddressBean getAddress() { return address; }
	public void setAdress(AddressBean adress) { this.address = adress; }
}
