/**  
 * Project Name:common-utils  
 * File Name:RespondCode.java  
 * Package Name:com.permady.code  
 * Date:2017��10��31������11:58:00  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package com.permady.code;  
/**  
 * ClassName:RespondCode <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017��10��31�� ����11:58:00 <br/>  
 * @author   peramdy  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public enum RespondCode {
	
	
	OK(200,"����ɹ�"),
	NOT_FOUND(404,"");
	
	
	private Integer code;
	private String    msg;
	

	private RespondCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	
	

}
  
