package com.his.bean;

import java.util.Date;

/**  
* @ClassName: Checknoticebean  
* @Description: TODO(检查通知单bean)  
* @author TRC
* @date 2019年8月13日  下午4:19:38
*    
*/
public class Checknoticebean {
	private String noticeid;
	private String brname;
	private String brid;
	private String checkname;
	private String checkid;
	private String beizhu;
    private Date noticetime;
    public Checknoticebean() {
    	
    }
	public Checknoticebean(String noticeid,String brname, String brid,String checkname ,String checkid,   String beizhu,
			Date noticetime) {
		super();
		this.noticeid = noticeid;
		this.brname = brname;
		this.brid = brid;
		this.checkname = checkname;
		this.checkid = checkid;
		this.beizhu = beizhu;
		this.noticetime = noticetime;
	}
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}
	public String getBrname() {
		return brname;
	}
	public void setBrname(String brname) {
		this.brname = brname;
	}
	public String getBrid() {
		return brid;
	}
	public void setBrid(String brid) {
		this.brid = brid;
	}
	public String getCheckname() {
		return checkname;
	}
	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public Date getNoticetime() {
		return noticetime;
	}
	public void setNoticetime(Date noticetime) {
		this.noticetime = noticetime;
	}

    
}
