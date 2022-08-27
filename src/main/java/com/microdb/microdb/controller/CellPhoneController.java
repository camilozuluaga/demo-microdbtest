package com.microdb.microdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microdb.microdb.bean.CellPhone;
import com.microdb.microdb.bean.Respuesta;
import com.microdb.microdb.repository.CellPhoneRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/phone") // This means URL's start with /demo (after Application path)
public class CellPhoneController {

    @Autowired
    CellPhoneRepository cellPhoneRepository;

    @ExceptionHandler(DataIntegrityViolationException.class)
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Respuesta createCellPhone(@RequestBody CellPhone cellPhone) {
        Respuesta respuesta = new Respuesta();
        try {
            cellPhoneRepository.save(cellPhone);
            respuesta.setRespuesta("Celular Guardado");
        } catch (DataIntegrityViolationException e) {
            respuesta.setRespuesta("Celular no fue guardado");
            respuesta.setError(NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        }
        return respuesta;
    }

}
