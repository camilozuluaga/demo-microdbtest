package com.microdb.microdb.bean.input;

import java.util.Date;

import lombok.Data;

@Data
public class CellPhone {

    private String id;
    private Date createdAt;
    private Date updatedAt;
    private String brand;
    private String model;
    private String number;
	


}
