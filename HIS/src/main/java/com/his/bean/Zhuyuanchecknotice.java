package com.his.bean;

import java.util.Date;

/**  
* @ClassName: Zhuyuanchecknotice  
* @Description: TODO(住院检查通知单bean)  
* @author TRC
* @date 2019年8月19日  上午9:23:49
*    
*/
public class Zhuyuanchecknotice {
	private String noticeid;
	private String cheid;
	private String chename;
	private String brid;
	private String brname;
	private String beizhu;
	private Date time;
	public Zhuyuanchecknotice() {}
	public Zhuyuanchecknotice(String noticeid, String cheid, String chename, String brid, String brname, String beizhu,
			Date time) {
		super();
		this.noticeid = noticeid;
		this.cheid = cheid;
		this.chename = chename;
		this.brid = brid;
		this.brname = brname;
		this.beizhu = beizhu;
		this.time = time;
	}
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}
	public String getCheid() {
		return cheid;
	}
	public void setCheid(String cheid) {
		this.cheid = cheid;
	}
	public String getChename() {
		return chename;
	}
	public void setChename(String chename) {
		this.chename = chename;
	}
	public String getBrid() {
		return brid;
	}
	public void setBrid(String brid) {
		this.brid = brid;
	}
	public String getBrname() {
		return brname;
	}
	public void setBrname(String brname) {
		this.brname = brname;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	

}
