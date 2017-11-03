/**  
 * Project Name:student  
 * File Name:RequestEntity.java  
 * Package Name:com.peramdy.common  
 * Date:2017年10月31日上午11:27:25  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package com.peramdy.common;

import java.io.Serializable;
import java.util.Map;

/**  
 * ClassName:RequestEntity <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年10月31日 上午11:27:25 <br/>  
 * @author   peramdy  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class RequestEntity implements Serializable {

	
	private static final long serialVersionUID = -2297918397503212424L;
	
	private Map<String,Object> parms;

	public RequestEntity() {
		super();
	}

	public Map<String, Object> getParms() {
		return parms;
	}
	
	/**
	 * 
	 * setMap:入参键值对
	 * key-->value 
	 *  
	 * @author peramdy  
	 * @param parms  
	 * @since JDK 1.7
	 */
	public void setParms(Map<String, Object> parms) {
		this.parms = parms;
	}
	
}
  
