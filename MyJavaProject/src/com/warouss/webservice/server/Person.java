package com.warouss.webservice.server;

public class Person {
	private Long id;
	private String name;
	private Boolean male;
	private Integer age;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Boolean getMale() {
		return male;
	}
	public Integer getAge() {
		return age;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMale(Boolean male) {
		this.male = male;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person{"+id+", "+name+", "+age+", "+male+"]";
	}
	
	

}
