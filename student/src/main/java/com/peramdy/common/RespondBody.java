/**  
 * Project Name:student  
 * File Name:RespondBody.java  
 * Package Name:com.peramdy.common  
 * Date:2017年10月31日上午11:28:03  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package com.peramdy.common;

import java.io.Serializable;

/**  
 * ClassName:RespondBody <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年10月31日 上午11:28:03 <br/>  
 * @author   peramdy  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class RespondBody<T> implements Serializable {
	
	
	/**  
	 * serialVersionUID:
	 * @since JDK 1.7  
	 */
	private static final long serialVersionUID = 2042379214674765225L;
	
	
	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 状态信息
	 */
	private String  msg;
	/**
	 * 返回实体
	 */
	private T       object;
	
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	
	
	

}
  
