package com.company.greeninvoice.service;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.greeninvoice.dao.InvoiceDao;
import com.company.greeninvoice.dao.UserDao;
import com.company.greeninvoice.dto.Customer;
import com.company.greeninvoice.dto.Invoice;
import com.company.greeninvoice.dto.User;
import com.company.greeninvoice.entity.Address;
import com.company.greeninvoice.entity.ItemDetail;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

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
	
	public static final String DEFAULT_INVOICE_NUMBER = "0001";
	
	public static final String INVOICE_FORMATTER = "%04d";
	
	public static final String MONTH_FORMATTER = "%02d";
	
	@Autowired
	InvoiceDao invoiceDao;
	
	@Autowired
	UserDao userDao;

	@Transactional
	@Override
	public String generateInvoiceNumber() {
		Order order=new Order(Direction.ASC, "createdTime");
		Sort sortConditions=new Sort(new Order(Direction.DESC, "createdTime"));
		List<Invoice> invoiceList=invoiceDao.findAll(sortConditions);
		Invoice invoice=new Invoice();
		if(invoiceList.isEmpty()) {
			LocalDate todayDate=LocalDate.now();
			String yearCode = String.valueOf(todayDate.getYear()%1000);
			int monthValue=LocalDate.now().getMonth().getValue();
			String monthCode=String.format(MONTH_FORMATTER, monthValue);
			return INVOICE_CODE+SEPERATOR+yearCode+monthCode+SEPERATOR+DEFAULT_INVOICE_NUMBER;
		}else {
			invoice=invoiceList.get(0);
			return incrementInvoiceNumber(invoice);
		}
	}

	private String incrementInvoiceNumber(Invoice invoice) {
		String invoiceNumber=invoice.getInvoiceNumber();
		LocalDate todayDate=LocalDate.now();
		String[] invoiceCodeArray = invoiceNumber.split(SEPERATOR);
		int lastInvoiceNumber=Integer.parseInt(invoiceCodeArray[2]);
		String number =DEFAULT_INVOICE_NUMBER;
		if(lastInvoiceNumber<9999) {
			number = String.format(INVOICE_FORMATTER, lastInvoiceNumber+1);
		}
		String dateCode = String.valueOf(todayDate.getYear()%1000);
		int monthValue=LocalDate.now().getMonth().getValue();
		String monthCode=String.format(MONTH_FORMATTER, monthValue);
		return INVOICE_CODE+SEPERATOR+dateCode+monthCode+SEPERATOR+number;
	}

	
	@Override
	public void addInvoice(Map<String,Object> parameterMap) throws ParseException {
		List<ItemDetail> itemDetailsList= new ArrayList<>();
		List<Map<String,Object>> duplicateList= (List<Map<String,Object>>) parameterMap.get("itemDetailsList");
		duplicateList.stream().forEach(action->{
			ItemDetail duplicateItem=new ItemDetail();
			duplicateItem.setSerialNumber(action.get("serialNumber").toString());
			duplicateItem.setItemDescription(action.get("itemDescription").toString());
			duplicateItem.setItemQuantity(Integer.valueOf(action.get("itemQuantity").toString()));
			duplicateItem.setItemRate(Float.valueOf(action.get("itemRate").toString().replaceAll("[\\s+,₹]", "")));
			duplicateItem.setAmount(Float.valueOf(action.get("amount").toString().replaceAll("[\\s+,₹]", "")));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			duplicateItem.setCreatedTime(date);
			itemDetailsList.add(duplicateItem);
		});
		Type type=new TypeToken<List<ItemDetail>>() {
		}.getType();
		Gson gson = new Gson();
		gson.toJson(itemDetailsList);
		System.out.println(gson.toJson(itemDetailsList,type));
		ItemDetail itemdetail=new ItemDetail();
    	Customer customerInformation=new Customer();
    	customerInformation.setCustomerName(parameterMap.get("customerName").toString());
    	customerInformation.setMale((parameterMap.get("gender").equals("M")?true:false));
    	Address customerAddress=new Address();
    	customerAddress.setAddressLine1(parameterMap.get("addressLine1").toString());
    	customerAddress.setAddressLine2(parameterMap.get("addressLine2").toString());
    	customerAddress.setCity(parameterMap.get("addressLine3").toString());
    	customerAddress.setCountry(parameterMap.get("country").toString());
    	customerAddress.setPhoneNumber(parameterMap.get("phoneNumber").toString());
    	Invoice invoice=new Invoice();
    	invoice.setInvoiceNumber(parameterMap.get("invoiceNumber").toString());
    	String dateString =parameterMap.get("invoiceDate").toString();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = formatter.parse(dateString);
		java.sql.Date invoiceDate = new java.sql.Date(date.getTime()); 
    	invoice.setInvoiceDate(invoiceDate);
    	invoice.setCustomerDetails(customerInformation);
    	invoice.setItemdetailsList(itemDetailsList);
    	String taxPercentage=parameterMap.get("taxPercentage").toString();
    	invoice.setTaxPercentage(Float.valueOf(taxPercentage.replaceAll("[\\s+,%]", "")));
    	String shippingCharges=parameterMap.get("shippingCharges").toString();
    	invoice.setShippingCharges(Float.valueOf(shippingCharges.replaceAll("[\\s+,₹]", "")));
    	String subTotalAmount=parameterMap.get("subTotalAmount").toString();
    	invoice.setSubTotalAmount(Float.valueOf(subTotalAmount.replaceAll("[\\s+,₹]", "")));
    	String taxAmount=parameterMap.get("taxAmount").toString();
    	invoice.setTaxAmount(Float.valueOf(taxAmount.replaceAll("[\\s+,₹]", "")));
    	String totalBillAmount=parameterMap.get("totalBillAmount").toString();
    	invoice.setTotalAmount(Float.valueOf(totalBillAmount.replaceAll("[\\s+,₹]", "")));
    	invoice.setPaymentMethod("CASH");
    	invoice.setInvoiceStatus("OPEN");
    	invoiceDao.saveAndFlush(invoice);
	}

}
