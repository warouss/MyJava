package com.warouss.google.commons.cache;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Product {
	private Long id;
	private String sku;
	private Double price;
	public Product(Long id, String sku, Double price) {
		super();
		this.id = id;
		this.sku = sku;
		this.price = price;
	}
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("sku", sku)
				.add("price", price)
				.omitNullValues()
				.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id, sku, price);
	}
}
