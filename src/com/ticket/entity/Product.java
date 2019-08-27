package com.ticket.entity;

public class Product {
	private int productId;
	private String productName;
	private String defaultAssignee;
	private int parent;
	/* setter and getter methods for parent*/
	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}
	/* setter and getter methods for defaultAssignee*/
	public String getDefaultAssignee() {
		return defaultAssignee;
	}

	public void setDefaultAssignee(String defaultAssignee) {
		this.defaultAssignee = defaultAssignee;
	}
	/* setter and getter methods for productId*/
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	/* setter and getter methods for productname*/
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", defaultAssignee="
				+ defaultAssignee + ", parent=" + parent + "]";
	}

}
