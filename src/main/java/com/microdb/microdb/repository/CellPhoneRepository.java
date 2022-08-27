package com.microdb.microdb.repository;

import org.springframework.data.repository.CrudRepository;

import com.microdb.microdb.bean.CellPhone;

public interface CellPhoneRepository extends CrudRepository<CellPhone, Integer>{
    
}
