package com.warouss.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	
	private Integer id;
	
	private String name;
	
	private String complain;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getComplain() {
		return complain;
	}

	@XmlAttribute
	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public void setComplain(String complain) {
		this.complain = complain;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", complain="
				+ complain + "]";
	}
	
	
	
	
}
