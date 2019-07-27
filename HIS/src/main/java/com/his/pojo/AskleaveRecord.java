package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ASKLEAVE_RECORD database table.
 * 
 */
@Entity
@Table(name="ASKLEAVE_RECORD")
@NamedQuery(name="AskleaveRecord.findAll", query="SELECT a FROM AskleaveRecord a")
public class AskleaveRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OFF_ID")
	private String offId;

	@Column(name="OFF_DAYS")
	private BigDecimal offDays;

	@Temporal(TemporalType.DATE)
	@Column(name="OFF_END_TIME")
	private Date offEndTime;

	@Temporal(TemporalType.DATE)
	@Column(name="OFF_START_TIME")
	private Date offStartTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	public AskleaveRecord() {
	}

	public String getOffId() {
		return this.offId;
	}

	public void setOffId(String offId) {
		this.offId = offId;
	}

	public BigDecimal getOffDays() {
		return this.offDays;
	}

	public void setOffDays(BigDecimal offDays) {
		this.offDays = offDays;
	}

	public Date getOffEndTime() {
		return this.offEndTime;
	}

	public void setOffEndTime(Date offEndTime) {
		this.offEndTime = offEndTime;
	}

	public Date getOffStartTime() {
		return this.offStartTime;
	}

	public void setOffStartTime(Date offStartTime) {
		this.offStartTime = offStartTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}