package com.his.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**  
* @ClassName: PutStockInformation  
* @Description: 药品入库信息保存辅助类 
* @author crazy_long
* @date 2019年8月17日  下午2:48:08
*    
*/
public class PutStockInformation {
	
	private String ypId;
	
	private BigDecimal putNumber;
	
	private Date ypProduceDate;
	
	private String ygxh;
	
	private Date putStockDate;
	
	public PutStockInformation() {}

	public PutStockInformation(String ypId, BigDecimal putNumber, Date ypProduceDate, String ygxh, Date putStockDate) {
		super();
		this.ypId = ypId;
		this.putNumber = putNumber;
		this.ypProduceDate = ypProduceDate;
		this.ygxh = ygxh;
		this.putStockDate = putStockDate;
	}



	public String getYpId() {
		return ypId;
	}

	public void setYpId(String ypId) {
		this.ypId = ypId;
	}

	public BigDecimal getPutNumber() {
		return putNumber;
	}

	public void setPutNumber(BigDecimal putNumber) {
		this.putNumber = putNumber;
	}

	public Date getYpProduceDate() {
		return ypProduceDate;
	}

	public void setYpProduceDate(Date ypProduceDate) {
		this.ypProduceDate = ypProduceDate;
	}

	public String getYgxh() {
		return ygxh;
	}

	public void setYgxh(String ygxh) {
		this.ygxh = ygxh;
	}

	public Date getPutStockDate() {
		return putStockDate;
	}

	public void setPutStockDate(Date putStockDate) {
		this.putStockDate = putStockDate;
	}

	
	
}
