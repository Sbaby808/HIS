package com.his.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.his.pojo.EmpInformation;

/**  
* @ClassName: OperationPaybean  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author TRC
* @date 2019年7月31日  下午3:44:18
*    
*/
public class OperationPaybean {
private String operPayId;
private String operPayDesc;
private String operPayName;
private BigDecimal operPayPrice;
private Date operPayTime;
private String ygname;
private String ygxh;

public OperationPaybean(String operPayId, String operPayDesc, String operPayName, BigDecimal operPayPrice,
		Date operPayTime, String ygname,String ygxh) {
	super();
	this.operPayId = operPayId;
	this.operPayDesc = operPayDesc;
	this.operPayName = operPayName;
	this.operPayPrice = operPayPrice;
	this.operPayTime = operPayTime;
	this.ygname = ygname;
	this.ygxh=ygxh;
}
public String getOperPayId() {
	return operPayId;
}
public void setOperPayId(String operPayId) {
	this.operPayId = operPayId;
}
public String getOperPayDesc() {
	return operPayDesc;
}
public void setOperPayDesc(String operPayDesc) {
	this.operPayDesc = operPayDesc;
}
public String getOperPayName() {
	return operPayName;
}
public void setOperPayName(String operPayName) {
	this.operPayName = operPayName;
}
public BigDecimal getOperPayPrice() {
	return operPayPrice;
}
public void setOperPayPrice(BigDecimal operPayPrice) {
	this.operPayPrice = operPayPrice;
}
public Date getOperPayTime() {
	return operPayTime;
}
public void setOperPayTime(Date operPayTime) {
	this.operPayTime = operPayTime;
}
public String getYgname() {
	return ygname;
}
public void setYgname(String ygname) {
	this.ygname = ygname;
}
public String getYgxh() {
	return ygxh;
}
public void setYgxh(String ygxh) {
	this.ygxh = ygxh;
}

}
