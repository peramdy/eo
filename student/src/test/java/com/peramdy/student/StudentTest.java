package com.peramdy.student;

import org.junit.Test;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;

/**
 *  
 * @author peramdy  
 * @version   
 * @since JDK 1.7
 * 
 */
public class StudentTest {
	
	
	@ApolloConfig
	private Config config;
	
	/**
	 * 
	 * testOne:
	 *  
	 * @author peramdy    
	 * @since JDK 1.7
	 */
	@Test
	public void testOne() {
		
	}
	
	
	@Test
	public void testApolloConfig() {
					
//	    config = ConfigService.getConfig("10001.hello");
		String value= config.getProperty("test", "0");
		System.out.println(value);
	}

}
  
