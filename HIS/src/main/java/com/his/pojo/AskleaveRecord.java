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

	@Temporal(TemporalType.DATE)
	@Column(name="askleave_time")
	private Date askLeaveTime;
	
	@Column(name="askleave_type")
	private String askLeaveType;

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

	

	public Date getAskLeaveTime() {
		return askLeaveTime;
	}

	public void setAskLeaveTime(Date askLeaveTime) {
		this.askLeaveTime = askLeaveTime;
	}

	public String getAskLeaveType() {
		return askLeaveType;
	}

	public void setAskLeaveType(String askLeaveType) {
		this.askLeaveType = askLeaveType;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}