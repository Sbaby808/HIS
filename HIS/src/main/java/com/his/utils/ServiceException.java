package com.his.utils;

/**  
* @ClassName: ServiceException  
* @Description: 业务层异常类，用于抛出记录业务层异常信息
* @author Sbaby
* @date 2019年7月31日  下午4:48:19
*    
*/
public class ServiceException extends RuntimeException {

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
}
