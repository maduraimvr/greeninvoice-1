package com.company.greeninvoice.service;

import java.text.ParseException;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * InvoiceService interface handles all methods for invoice.
 * 
 * @author Venkatraman
 *
 */
@Service
public interface InvoiceService {

	/**
	 * generateInvoiceNumber method to initially generate invoice number sequentially.
	 * @return invoiceNumber
	 */
	String generateInvoiceNumber();

	/**
	 * addInvoice method to insert a new invoice.
	 * 
	 * @param parameterMap
	 * @throws ParseException
	 */
	void addInvoice(Map<String, Object> parameterMap) throws ParseException;
}
