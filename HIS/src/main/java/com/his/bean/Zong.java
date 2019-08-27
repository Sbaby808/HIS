package com.his.bean;

import java.util.Date;

/**  
* @ClassName: Zong  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author TRC
* @date 2019年8月26日  上午9:52:06
*    
*/
public class Zong {
	private One onelist;
	private Two twolist;
	private Three threelist;
	private Four fourlist;
	private Five fivelist;
	private Six sixlist;
	private Seven sevenlist;
	private Date time;
	private String ygxh;
	public Zong() {}
	public Zong(One onelist, Two twolist, Three threelist, Four fourlist, Five fivelist, Six sixlist, Seven sevenlist,Date time,String ygxh) {
		super();
		this.onelist = onelist;
		this.twolist = twolist;
		this.threelist = threelist;
		this.fourlist = fourlist;
		this.fivelist = fivelist;
		this.sixlist = sixlist;
		this.sevenlist = sevenlist;
		this.time=time;
		this.ygxh=ygxh;
	}
	
	public String getYgxh() {
		return ygxh;
	}
	public void setYgxh(String ygxh) {
		this.ygxh = ygxh;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public One getOnelist() {
		return onelist;
	}
	public void setOnelist(One onelist) {
		this.onelist = onelist;
	}
	public Two getTwolist() {
		return twolist;
	}
	public void setTwolist(Two twolist) {
		this.twolist = twolist;
	}
	public Three getThreelist() {
		return threelist;
	}
	public void setThreelist(Three threelist) {
		this.threelist = threelist;
	}
	public Four getFourlist() {
		return fourlist;
	}
	public void setFourlist(Four fourlist) {
		this.fourlist = fourlist;
	}
	public Five getFivelist() {
		return fivelist;
	}
	public void setFivelist(Five fivelist) {
		this.fivelist = fivelist;
	}
	public Six getSixlist() {
		return sixlist;
	}
	public void setSixlist(Six sixlist) {
		this.sixlist = sixlist;
	}
	public Seven getSevenlist() {
		return sevenlist;
	}
	public void setSevenlist(Seven sevenlist) {
		this.sevenlist = sevenlist;
	}
	
	

}
