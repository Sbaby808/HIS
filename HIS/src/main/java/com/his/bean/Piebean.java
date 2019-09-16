package com.his.bean;

/**  
* @ClassName: Piebean  
* @Description: TODO(饼图)  
* @author TRC
* @date 2019年9月10日  下午4:34:46
*    
*/
public class Piebean {
	private int value;
	private String name;
	
	
	public Piebean(int value,String name) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	

}
