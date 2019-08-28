package com.his.bean;

import java.util.List;

import com.his.pojo.EmpInformation;

/**  
* @ClassName: Night  
* @Description: TODO(晚班)  
* @author TRC
* @date 2019年8月26日  上午9:47:27
*    
*/
public class Night {
	private List<EmpInformation> zrlist;
	private List<EmpInformation> fzrlist;
	private List<EmpInformation> hslist;
	public Night() {}
	public Night(List<EmpInformation> zrlist, List<EmpInformation> fzrlist, List<EmpInformation> hslist) {
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
