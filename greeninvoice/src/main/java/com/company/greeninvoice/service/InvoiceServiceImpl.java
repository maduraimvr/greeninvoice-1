package com.company.greeninvoice.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.greeninvoice.dao.InvoiceDao;
import com.company.greeninvoice.dao.UserDao;
import com.company.greeninvoice.dto.Customer;
import com.company.greeninvoice.dto.Invoice;
import com.company.greeninvoice.dto.User;
import com.company.greeninvoice.entity.ItemDetail;

/**
 * InvoiceServiceImpl class handles all methods for invoice.
 * 
 * @author Venkatraman
 *
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{

	public static final String EMPTY_STRING = "";
	
	public static final String INVOICE_CODE = "INV";
	
	public static final String SEPERATOR = "-";
	
	public static final String DEFAULT_INVOICE_NUMBER = "0000";
	
	public static final String INVOICE_FORMATTER = "%04d";
	
	@Autowired
	InvoiceDao invoiceDao;
	
	@Autowired
	UserDao userDao;

	@Transactional
	@Override
	public String generateInvoiceNumber() {
		Invoice invoice=new Invoice();
//		inserTesttUser();
		return "";
	}

	/**
	 * For testing (DO NOT USE)
	 */
	private void insertTestInvoice() {
		Invoice invoice=new Invoice();
		List<ItemDetail> itemdetailsList=new ArrayList<>();
		ItemDetail itemDetail=new ItemDetail();
		ItemDetail itemDetail2=new ItemDetail();
		ItemDetail itemDetail3=new ItemDetail();
		LocalDate todayDate=LocalDate.now();
		String yearCode = String.valueOf(todayDate.getYear()%1000);
		String monthCode = LocalDate.now().getMonth().name().substring(0, 3);
		String invoiceNumber=INVOICE_CODE+SEPERATOR+yearCode+monthCode+SEPERATOR+"0002";
		invoice.setInvoiceNumber(invoiceNumber);
		Customer customer=new Customer();
		customer.setCustomerName("VENKAT");
		customer.setMale(true);
		invoice.setCustomerDetails(customer);
		invoice.setTotalAmount((float) 25.02);
		invoice.setInvoiceDate(Date.valueOf(todayDate));
		itemDetail.setSerialNumber("200");
		itemDetail.setItemDescription("sun");
		itemDetail.setItemQuantity(2);
		itemDetail.setItemRate(25);
		itemDetail.setAmount(50);
		itemdetailsList.add(itemDetail);
		itemDetail2.setSerialNumber("100");
		itemDetail2.setItemDescription("moon");
		itemDetail2.setItemQuantity(9);
		itemDetail2.setItemRate(255);
		itemDetail2.setAmount(100);
		itemdetailsList.add(itemDetail2);
		invoice.setItemdetailsList( itemdetailsList);
		Invoice invoice2= invoiceDao.saveAndFlush(invoice);
	}

	/**
	 * For testing (DO NOT USE)
	 */
	private void inserTesttUser() {
		User user=new User();
		user.setUserName("admin");
		user.setFirstName("Venkatraman");
		user.setLastName("Muthukrishnan");
		user.setPassword("admin");
		user.setEmail("admin@greeninvoice.com");
		user.setProfileImage("admin.png");
		userDao.save(user);
	}

}
