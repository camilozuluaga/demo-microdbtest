package com.microdb.microdb.bean.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="cellPhone")
@Data
public class CellPhoneDb implements Serializable{
    
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;
  
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
  
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

	@NotBlank(message = "Marca es obligatorio")
    private String brand;
	
	@NotBlank(message = "Modelo es Obligatorio")
    private String model;
    
    @Size(min = 6, max = 12)
	@Pattern(regexp = "[0-9]+", message = "Solo debe de contener números")
    @Column(length = 12, nullable = false, unique = true)
    private String number;

    @OneToOne(mappedBy = "cellPhoneId")
    private UserDb user;

}
