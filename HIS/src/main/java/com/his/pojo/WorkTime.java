package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the WORK_TIME database table.
 * 
 */
@Entity
@Table(name="WORK_TIME")
@NamedQuery(name="WorkTime.findAll", query="SELECT w FROM WorkTime w")
public class WorkTime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PB_ID")
	private String pbId;

	@Temporal(TemporalType.DATE)
	@Column(name="PB_DATE")
	private Date pbDate;

	@Column(name="PB_TYPE")
	private String pbType;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="EMP_YGXH")
	private EmpInformation empInformation1;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation2;

	public WorkTime() {
	}

	public String getPbId() {
		return this.pbId;
	}

	public void setPbId(String pbId) {
		this.pbId = pbId;
	}

	public Date getPbDate() {
		return this.pbDate;
	}

	public void setPbDate(Date pbDate) {
		this.pbDate = pbDate;
	}

	public String getPbType() {
		return this.pbType;
	}

	public void setPbType(String pbType) {
		this.pbType = pbType;
	}

	public EmpInformation getEmpInformation1() {
		return this.empInformation1;
	}

	public void setEmpInformation1(EmpInformation empInformation1) {
		this.empInformation1 = empInformation1;
	}

	public EmpInformation getEmpInformation2() {
		return this.empInformation2;
	}

	public void setEmpInformation2(EmpInformation empInformation2) {
		this.empInformation2 = empInformation2;
	}

}