package com.his.bean;

import java.util.Date;

/**  
* @ClassName: OperationRecord  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author TRC
* @date 2019年8月1日  下午3:17:26
*    
*/
public class OperationRecordbean {
private String opeId;
private String brname;
private String operPayNameString;
private String opeJg;
private String opeStatus;
private Date opeTime;
private String opeType;
private String opeDiagnose;
public OperationRecordbean(String opeId, String brname, String operPayNameString, String opeJg, String opeStatus,
		Date opeTime, String opeType,String opeDiagnose) {
	super();
	this.opeId = opeId;
	this.brname = brname;
	this.operPayNameString = operPayNameString;
	this.opeJg = opeJg;
	this.opeStatus = opeStatus;
	this.opeTime = opeTime;
	this.opeType = opeType;
	this.opeDiagnose=opeDiagnose;
}
public String getOpeId() {
	return opeId;
}
public void setOpeId(String opeId) {
	this.opeId = opeId;
}
public String getBrname() {
	return brname;
}
public void setBrname(String brname) {
	this.brname = brname;
}
public String getOperPayNameString() {
	return operPayNameString;
}
public void setOperPayNameString(String operPayNameString) {
	this.operPayNameString = operPayNameString;
}
public String getOpeJg() {
	return opeJg;
}
public void setOpeJg(String opeJg) {
	this.opeJg = opeJg;
}
public String getOpeStatus() {
	return opeStatus;
}
public void setOpeStatus(String opeStatus) {
	this.opeStatus = opeStatus;
}
public Date getOpeTime() {
	return opeTime;
}
public void setOpeTime(Date opeTime) {
	this.opeTime = opeTime;
}
public String getOpeType() {
	return opeType;
}
public void setOpeType(String opeType) {
	this.opeType = opeType;
}
public String getOpeDiagnose() {
	return opeDiagnose;
}
public void setOpeDiagnose(String opeDiagnose) {
	this.opeDiagnose = opeDiagnose;
}

}
