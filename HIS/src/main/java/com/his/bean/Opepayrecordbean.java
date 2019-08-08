package com.his.bean;

import java.math.BigDecimal;
import java.util.Date;

/**  
* @ClassName: Opepayrecordbean  
* @Description: TODO手术缴费记录bean
* @author TRC
* @date 2019年8月5日  上午9:33:54
*    
*/
public class Opepayrecordbean {
    private String opepayname;
    private String brname;
    private BigDecimal money;
    private String ygname;
    private Date time;
	public Opepayrecordbean(String opepayname, String brname, BigDecimal money, String ygname, Date time) {
		super();
		this.opepayname = opepayname;
		this.brname = brname;
		this.money = money;
		this.ygname = ygname;
		this.time = time;
	}
	public String getOpepayname() {
		return opepayname;
	}
	public void setOpepayname(String opepayname) {
		this.opepayname = opepayname;
	}
	public String getBrname() {
		return brname;
	}
	public void setBrname(String brname) {
		this.brname = brname;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getYgname() {
		return ygname;
	}
	public void setYgname(String ygname) {
		this.ygname = ygname;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
    

}
