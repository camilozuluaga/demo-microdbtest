package com.microdb.microdb.bean.input;

import java.util.Date;

import lombok.Data;
@Data

public class User {
	
	private String id;
	private Date createdAt;
	private Date updatedAt;
	private String name;
  	private String email;
	private String documentNumber;
	private CellPhone cellPhoneId;




	
}
