package com.yeldi.web;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "customer")
public class Customer {
	
	private int custNo;
    private String custName;
    private String custCountry;
    private String custFeedback;
    
    @XmlElement
    public String getCustFeedback() {
		return custFeedback;
	}
	public void setCustFeedback(String custFeedback) {
		this.custFeedback = custFeedback;
	}
	@XmlElement
	public int getCustNo() {
        return custNo;
    }
    public void setCustNo(int custNo) {
        this.custNo = custNo;
    }
    @Override
	public String toString() {
		return "Customer [custNo=" + custNo + ", custName=" + custName + ", custCountry=" + custCountry
				+ ", custFeedback=" + custFeedback + "]";
	}
    
    @XmlElement
	public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    @XmlElement
    public String getCustCountry() {
        return custCountry;
    }
    public void setCustCountry(String custCountry) {
        this.custCountry = custCountry;
    }    

}
