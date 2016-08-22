package com.yeldi.web.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Error {
	
	private String notAvailble;
	 @XmlElement
	public String getNotAvailble() {
		return notAvailble;
	}

	public void setNotAvailble(String notAvailble) {
		this.notAvailble = notAvailble;
	}

}
