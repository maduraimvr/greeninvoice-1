package com.company.greeninvoice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
/**
 * Address is a bean class.
 * @author Venkatraman
 *
 */
@Data
public class Address  implements Serializable{

	private String addressLine1;
	
	private String addressLine2;
	
	private String country;
	
	private String city;
	
	private String phoneNumber;
	
}
