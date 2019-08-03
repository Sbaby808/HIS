package com.his.bean;

import java.util.Date;

/**  
* @ClassName: OpeNoticebean  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author TRC
* @date 2019年8月2日  下午3:15:33
*    
*/
public class OpeNoticebean {
private String moperId;
private String moperComment;
private Date moperTime;
private String opexiang;
private String brname;
public OpeNoticebean(String moperId, String moperComment, Date moperTime, String opexiang, String brname) {
	super();
	this.moperId = moperId;
	this.moperComment = moperComment;
	this.moperTime = moperTime;
	this.opexiang = opexiang;
	this.brname = brname;
}
public String getMoperId() {
	return moperId;
}
public void setMoperId(String moperId) {
	this.moperId = moperId;
}
public String getMoperComment() {
	return moperComment;
}
public void setMoperComment(String moperComment) {
	this.moperComment = moperComment;
}
public Date getMoperTime() {
	return moperTime;
}
public void setMoperTime(Date moperTime) {
	this.moperTime = moperTime;
}
public String getOpexiang() {
	return opexiang;
}
public void setOpexiang(String opexiang) {
	this.opexiang = opexiang;
}
public String getBrname() {
	return brname;
}
public void setBrname(String brname) {
	this.brname = brname;
}

}
