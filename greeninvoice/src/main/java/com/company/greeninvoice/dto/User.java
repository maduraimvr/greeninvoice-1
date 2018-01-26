package com.company.greeninvoice.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * User class is a data transfer object class.
 * 
 * @author Venkatraman
 *
 */
@Data
@Entity
@Table(name="userdto")
public class User {
	
	@Id
	private String userName;
    
	private String firstName;
	
	private String lastName;

	private String email;

	private String password;

	private boolean isBlocked;
	
	private String profileImage;
	

}

