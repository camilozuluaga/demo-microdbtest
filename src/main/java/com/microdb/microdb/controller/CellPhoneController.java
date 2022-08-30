package com.microdb.microdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microdb.microdb.bean.CellPhone;
import com.microdb.microdb.bean.Respuesta;
import com.microdb.microdb.repository.CellPhoneRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/phone") // This means URL's start with /demo (after Application path)
public class CellPhoneController {

    @Autowired
    CellPhoneRepository cellPhoneRepository;

    @ExceptionHandler(DataIntegrityViolationException.class)
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public @ResponseBody CellPhone createCellPhone(@RequestBody CellPhone cellPhone) {
		return cellPhoneRepository.save(cellPhone);
	}

}
