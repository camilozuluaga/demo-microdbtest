package com.microdb.microdb.repository;

import org.springframework.data.repository.CrudRepository;

import com.microdb.microdb.bean.db.CellPhoneDb;

public interface CellPhoneRepository extends CrudRepository<CellPhoneDb, Integer>{
    
}
