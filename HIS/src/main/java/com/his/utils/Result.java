package com.his.utils;

/**  
* @ClassName: Result  
* @Description: 用于放回结果 
* @author crazy_long
* @date 2019年8月1日  下午4:44:25
*    
*/
public class Result {
	
	private String info;
	private boolean flag = false;
	
	public Result() {
		super();
		this.flag = true;
	}

	public Result(String info) {
		super();
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
