package com.microdb.microdb.bean.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
@Data
public class UserDb implements Serializable{

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

  @NotBlank(message = "Nombre es obligatorio")
  private String name;

  @Email(message = "Email is not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
  private String email;

  @Size(min = 6, max = 12)
  @Pattern(regexp = "[0-9]+", message = "Solo debe de contener números")
  @Column(length = 12, nullable = false, unique = true)
  private String documentNumber;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "cellPhoneId", referencedColumnName = "id")
  private CellPhoneDb cellPhoneId;
  
}
