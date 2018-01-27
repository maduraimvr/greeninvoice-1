/**
 * 
 */
package com.company.greeninvoice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.company.greeninvoice.dto.Customer;
import com.company.greeninvoice.dto.Invoice;
import com.company.greeninvoice.dto.User;
import com.company.greeninvoice.entity.Address;
import com.company.greeninvoice.entity.ItemDetail;
import com.company.greeninvoice.service.InvoiceService;
import com.company.greeninvoice.service.UserService;

/**
 * @author Venkatraman
 *
 */
@Controller
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	UserService userService;
	
	@Autowired
    private HttpSession session;
	
	private static final String ADD_INVOICE_VIEW = "add_invoice";
	
	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService=invoiceService;
	}
	
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	/**
	 * addInvoice method to add a new invoice.
	 * 
	 * @param request
	 * @return modelAndView
	 */
    @RequestMapping("/invoice/add")
    public ModelAndView addInvoice(HttpServletRequest request){
    	Map<String,Object> businessDataMap=new HashMap<>();
	    ModelAndView modelAndView=new ModelAndView();
	    String invoiceNumber = invoiceService.generateInvoiceNumber();
	    businessDataMap.put("invoiceNumber", invoiceNumber);
	    User user = (User) session.getAttribute("user");
	    request.setAttribute("userId", user.getUserName());
	    businessDataMap.put("user", user);
	    modelAndView.addAllObjects(businessDataMap);
	    modelAndView.setViewName(ADD_INVOICE_VIEW);
	    return modelAndView;
    }
    
    /**
     * submitInvoice method gets called when invoice is submitted.
     * 
     * @param parameterMap
     * @return businessMap
     * @throws ParseException
     */
    @RequestMapping(value="/invoice/add/submit", method=RequestMethod.POST,
            produces="application/json")
    public @ResponseBody Map<String,Object> submitInvoice(@RequestBody Map<String,Object> parameterMap) throws ParseException{
    	invoiceService.addInvoice(parameterMap);
	Map<String,Object> businessMap=new HashMap<>();
	businessMap.put("isSuccessful", true);
	return businessMap;
    }
    
}
