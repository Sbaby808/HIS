package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the WORKOVER_RECORD database table.
 * 
 */
@Entity
@Table(name="WORKOVER_RECORD")
@NamedQuery(name="WorkoverRecord.findAll", query="SELECT w FROM WorkoverRecord w")
public class WorkoverRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JB_ID")
	private String jbId;

	@Temporal(TemporalType.DATE)
	@Column(name="JB_DATE")
	private Date jbDate;

	@Column(name="JB_DAYS")
	private BigDecimal jbDays;

	@Temporal(TemporalType.DATE)
	@Column(name="JB_END_TIME")
	private Date jbEndTime;

	@Temporal(TemporalType.DATE)
	@Column(name="JB_START_TIME")
	private Date jbStartTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	public WorkoverRecord() {
	}

	public String getJbId() {
		return this.jbId;
	}

	public void setJbId(String jbId) {
		this.jbId = jbId;
	}

	public Date getJbDate() {
		return this.jbDate;
	}

	public void setJbDate(Date jbDate) {
		this.jbDate = jbDate;
	}

	public BigDecimal getJbDays() {
		return this.jbDays;
	}

	public void setJbDays(BigDecimal jbDays) {
		this.jbDays = jbDays;
	}

	public Date getJbEndTime() {
		return this.jbEndTime;
	}

	public void setJbEndTime(Date jbEndTime) {
		this.jbEndTime = jbEndTime;
	}

	public Date getJbStartTime() {
		return this.jbStartTime;
	}

	public void setJbStartTime(Date jbStartTime) {
		this.jbStartTime = jbStartTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}