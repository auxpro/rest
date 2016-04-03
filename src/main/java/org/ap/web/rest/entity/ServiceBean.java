package org.ap.web.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.ap.web.internal.EUserType;

@XmlRootElement
public class ServiceBean extends UserBean {
	
	/* ATTRIBUTES */
	
	private String society;
	private String phone;
	private AddressBean address;

	/* CONSTRUCTORS */
	
	public ServiceBean() {
		setType(EUserType.SERVICE.getId());
	}
	
	/* METHODS */
	
	public String getSociety() { return society; }
	public void setSociety(String society) { this.society = society; }
	
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	
	public AddressBean getAddress() { return address; }
	public void setAddress(AddressBean address) { this.address = address; }
}
