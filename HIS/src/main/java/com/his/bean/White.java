package com.his.bean;

import java.util.List;

import com.his.pojo.EmpInformation;

/**  
* @ClassName: White  
* @Description: TODO(白班)  
* @author TRC
* @date 2019年8月26日  上午9:46:05
*    
*/
public class White {
	private List<EmpInformation> zrlist;
	private List<EmpInformation> fzrlist;
	private List<EmpInformation> hslist;
	public White() {}
	public White(List<EmpInformation> zrlist, List<EmpInformation> fzrlist, List<EmpInformation> hslist) {
		super();
		this.zrlist = zrlist;
		this.fzrlist = fzrlist;
		this.hslist = hslist;
	}
	public List<EmpInformation> getZrlist() {
		return zrlist;
	}
	public void setZrlist(List<EmpInformation> zrlist) {
		this.zrlist = zrlist;
	}
	public List<EmpInformation> getFzrlist() {
		return fzrlist;
	}
	public void setFzrlist(List<EmpInformation> fzrlist) {
		this.fzrlist = fzrlist;
	}
	public List<EmpInformation> getHslist() {
		return hslist;
	}
	public void setHslist(List<EmpInformation> hslist) {
		this.hslist = hslist;
	}
	
	
}
