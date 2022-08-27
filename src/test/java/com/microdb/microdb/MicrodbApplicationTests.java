package com.microdb.microdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.microdb.microdb.bean.Respuesta;
import com.microdb.microdb.controller.UserController;

@SpringBootTest
class MicrodbApplicationTests {
	
    @Autowired
    private UserController mainController;

    @Test
    void testAddNewUser() {
        Respuesta res = mainController.addNewUser("Juan Camilo Zuluaga Serna", "juan.zuluaga@firefly-e.com", "1085365789");
        assertEquals(null, res.getError());
    }

    @Test
    void testGetAllUsers() {

    }
}
