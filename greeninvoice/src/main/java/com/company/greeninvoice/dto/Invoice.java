package com.company.greeninvoice.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.company.greeninvoice.entity.ItemDetail;

import lombok.Data;
import lombok.Setter;

/**
 * Invoice class is a data transfer object class.
 * @author Venkatraman
 *
 */
@Data
@Entity
@Setter
@Table(name="invoiceDto")
public class Invoice implements Serializable{
	
	private Timestamp createdTime;
	
	@Id
	private String invoiceNumber;
	
	private Date invoiceDate;
	
	private Customer customerDetails;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ItemDetail> itemdetailsList;
	
	private float subTotalAmount;
	
	private float taxPercentage;
	
	private float taxAmount;
	
	private float shippingCharges;
	
	private float totalAmount;
	
	private String paymentMethod;
	
	private String invoiceStatus;	
	
}
