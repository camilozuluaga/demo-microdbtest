package com.microdb.microdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microdb.microdb.bean.db.CellPhoneDb;
import com.microdb.microdb.bean.input.CellPhone;
import com.microdb.microdb.repository.CellPhoneRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/phone") // This means URL's start with /demo (after Application path)
public class CellPhoneController {

    @Autowired
    CellPhoneRepository cellPhoneRepository;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public @ResponseBody CellPhoneDb createCellPhone(@RequestBody CellPhone cellPhone) {
		CellPhoneDb cellPhoneDb = new CellPhoneDb();
		cellPhoneDb.setModel(cellPhone.getModel());
		cellPhoneDb.setBrand(cellPhone.getBrand());
		cellPhoneDb.setNumber(cellPhone.getNumber());
		return cellPhoneRepository.save(cellPhoneDb);
	}

}
