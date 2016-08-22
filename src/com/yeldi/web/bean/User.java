package com.yeldi.web.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "user")
public class User implements Serializable {
	
	private int id;
    private String userName;
    private String password1;
    private String email;
    private String phone;
    private String city;
    private String employeetype;
    private String status;
    
    @XmlElement
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the id
	 */
    @XmlElement
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password1=" + password1 + ", email=" + email
				+ ", phone=" + phone + ", city=" + city + ", employeetype=" + employeetype + "]";
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	 @XmlElement
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password1
	 */
	 @XmlElement
	public String getPassword1() {
		return password1;
	}
	/**
	 * @param password1 the password1 to set
	 */
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	/**
	 * @return the email
	 */
	 @XmlElement
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	 @XmlElement
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the city
	 */
	 @XmlElement
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	 @XmlElement
	public String getEmployeetype() {
		return employeetype;
	}
	public void setEmployeetype(String employeetype) {
		this.employeetype = employeetype;
	}
	
	

}
