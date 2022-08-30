package com.microdb.microdb.bean.input;

import java.util.Date;

public class User {
	
	private String id;
	private Date createdAt;
	private Date updatedAt;
	private String name;
  	private String email;
	private String documentNumber;
	private CellPhone cellPhoneId;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public CellPhone getCellPhoneId() {
		return cellPhoneId;
	}
	public void setCellPhoneId(CellPhone cellPhoneId) {
		this.cellPhoneId = cellPhoneId;
	}




	
}
