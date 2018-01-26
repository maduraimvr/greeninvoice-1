/**
 * 
 */
package com.company.greeninvoice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Venkatraman
 *
 */
@Controller
public class ErrorController {

    @RequestMapping("/error")
	public String show404(){
		return "errorpages/page_404";
	}
}
