package com.company.greeninvoice.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.company.greeninvoice.entity.Address;

import lombok.Data;
import lombok.Setter;
/**
 * customerDto class is a data transfer object class.
 * @author Venkatraman
 *
 */
@Data
@Setter
public class Customer implements Serializable{

	private String customerName;
	
	private boolean male;
	
	private Address customerAddress;
	
}
