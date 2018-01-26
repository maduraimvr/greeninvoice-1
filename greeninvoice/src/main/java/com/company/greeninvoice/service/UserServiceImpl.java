package com.company.greeninvoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.greeninvoice.dao.UserDao;
import com.company.greeninvoice.dto.User;

/**
 * InvoiceService interface handles all methods for userDto.
 * 
 * @author Venkatraman
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public User getUser(String userId) {
		return userDao.findOne(userId);
	}

}
