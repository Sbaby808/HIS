package com.his.bean;

/**  
* @ClassName: Medicinebean  
* @Description: TODO(药品名和批次id的bean)  
* @author TRC
* @date 2019年8月8日  上午11:04:39
*    
*/
public class Medicinebean {
	private String label;
	private String key;
	public Medicinebean(String label, String key) {
		super();
		this.label = label;
		this.key = key;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	

}
