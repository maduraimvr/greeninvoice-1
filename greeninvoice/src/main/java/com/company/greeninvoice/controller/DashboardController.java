/**
 * 
 */
package com.company.greeninvoice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.company.greeninvoice.dto.User;
import com.company.greeninvoice.service.UserService;

/**
 * DashboardController class handles request for dashboard page.
 * @author Venkatraman
 *
 */
@Controller
public class DashboardController {

	@Autowired
	UserService userService; 
	
	@Autowired
    private HttpSession session;
	
	private static final String DASHBOARD_VIEW = "index2";
	
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	/**
	 * index method to initiate dashboard.
	 * @param userId
	 * @param request
	 * @return indexModelandView
	 */
    @RequestMapping(value="/dashboard/index", method=RequestMethod.GET)
    public ModelAndView index(@RequestParam String userId,HttpServletRequest request) {
    	 ModelAndView indexModelandView=new ModelAndView();
    	 User user = new User();
    	 user=userService.getUser(userId);
    	 request.setAttribute("userId", userId);
    	 session.setAttribute("user", user);
    	 indexModelandView.setViewName(DASHBOARD_VIEW);
    	 indexModelandView.addObject(user);
    	return indexModelandView;
    }
}
