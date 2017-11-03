/**  
 * Project Name:student  
 * File Name:LoginController.java  
 * Package Name:com.peramdy.controller  
 * Date:2017锟斤拷10锟斤拷31锟斤拷锟斤拷锟斤拷11:09:11  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/

package com.peramdy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

/**
 * 
 * @author peramdy
 * @version
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/test")
	public String test() {
		Config config = ConfigService.getAppConfig();
		String parm = config.getProperty("timeout", "0");
		return parm;
	}

	
}
