package com.his.bean;

/**  
* @ClassName: Opeemp  
* @Description: TODO(手术员工bean)  
* @author TRC
* @date 2019年8月6日  下午3:10:32
*    
*/
public class Opeempbean {
	private String name;
	private String dute;
	private String yggh;
	public Opeempbean(String name, String dute,String yggh) {
		super();
		this.name = name;
		this.dute = dute;
		this.yggh = yggh;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDute() {
		return dute;
	}
	public void setDute(String dute) {
		this.dute = dute;
	}
	public String getYggh() {
		return yggh;
	}
	public void setYggh(String yggh) {
		this.yggh = yggh;
	}
	
 
}
