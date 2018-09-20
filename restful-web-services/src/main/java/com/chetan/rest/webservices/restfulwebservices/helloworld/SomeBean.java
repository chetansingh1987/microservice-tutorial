package com.chetan.rest.webservices.restfulwebservices.helloworld;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonFilter("SomeBeanFilter")
public class SomeBean {
	String field1;
	String field2;
	String field3;
	public SomeBean(String value1, String value2, String value3) {
		super();
		this.field1 = value1;
		this.field2 = value2;
		this.field3 = value3;
	}
	public String getField1() {
		return field1;
	}
	public String getField2() {
		return field2;
	}
	public String getField3() {
		return field3;
	}
	
	

}
