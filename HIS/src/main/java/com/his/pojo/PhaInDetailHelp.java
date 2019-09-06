package com.his.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;

/**  
* @ClassName: PhaInDetailHelp  
* @Description: 药房入库明细帮助类 
* @author crazy_long
* @date 2019年9月5日  下午11:46:09
*    
*/
public class PhaInDetailHelp {
	
	private String pckcId;

	private String phaInId;
	
	private BigDecimal phaInNum;
	
	private String deptId;
	
	private String ckId;


	public String getPckcId() {
		return pckcId;
	}

	public void setPckcId(String pckcId) {
		this.pckcId = pckcId;
	}

	public String getPhaInId() {
		return phaInId;
	}

	public void setPhaInId(String phaInId) {
		this.phaInId = phaInId;
	}

	public BigDecimal getPhaInNum() {
		return phaInNum;
	}

	public void setPhaInNum(BigDecimal phaInNum) {
		this.phaInNum = phaInNum;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCkId() {
		return ckId;
	}

	public void setCkId(String ckId) {
		this.ckId = ckId;
	}
	
	
}
