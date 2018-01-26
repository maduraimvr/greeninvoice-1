package com.company.greeninvoice.service;

import org.springframework.stereotype.Service;

import com.company.greeninvoice.dto.User;

/**
 * InvoiceService interface handles all methods for userDto.
 * 
 * @author Venkatraman
 *
 */
@Service
public interface UserService {
	
	/**
	 * getUser method to get single user entity.
	 * @param userId
	 * @return the User pojo.
	 */
	User getUser(String userId);

}
