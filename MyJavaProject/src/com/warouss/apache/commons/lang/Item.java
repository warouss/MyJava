package com.warouss.apache.commons.lang;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Item implements Comparable<Item> {
	private Long id;
	private String name;
	private Double price;
	
	public Item(Long id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(id).append(name).append(price).toString();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).toHashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		Item p = (Item) o;
		return new EqualsBuilder().append(this.id, p.getId()).isEquals();
	}

	@Override
	public int compareTo(Item o) {
		return new CompareToBuilder().append(id, o.getId()).toComparison();
	}

	
	
	
	
	

}
