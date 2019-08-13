package com.his.bean;

import java.math.BigDecimal;

/**  
* @ClassName: Addmedicbean  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author TRC
* @date 2019年8月9日  上午9:04:48
*    
*/
public class Addmedicbean {
	private String pcid;
	private String yaoname;
	private BigDecimal total;
	private String opeid;
	
	public Addmedicbean(String pcid, String yaoname, BigDecimal total, String opeid) {
		super();
		this.pcid = pcid;
		this.yaoname = yaoname;
		this.total = total;
		this.opeid = opeid;
	}
	public String getPcid() {
		return pcid;
	}
	public void setPcid(String pcid) {
		this.pcid = pcid;
	}
	public String getYaoname() {
		return yaoname;
	}
	public void setYaoname(String yaoname) {
		this.yaoname = yaoname;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getOpeid() {
		return opeid;
	}
	public void setOpeid(String opeid) {
		this.opeid = opeid;
	}
	
            
}
