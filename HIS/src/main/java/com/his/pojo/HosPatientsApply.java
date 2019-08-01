package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HOS_PATIENTS_APPLY database table.
 * 
 */
@Entity
@Table(name="HOS_PATIENTS_APPLY")
@NamedQuery(name="HosPatientsApply.findAll", query="SELECT h FROM HosPatientsApply h")
public class HosPatientsApply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_AID")
	private String hosAid;

	@Column(name="APPLY_RESULT")
	private String applyResult;

	@Temporal(TemporalType.DATE)
	@Column(name="APPLY_TIME")
	private Date applyTime;

	@Column(name="APPLY_TYPE")
	private String applyType;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to HospitalizedPatient
	@ManyToOne
	@JoinColumn(name="HOSP_ID")
	private HospitalizedPatient hospitalizedPatient;

	public HosPatientsApply() {
	}

	public String getHosAid() {
		return this.hosAid;
	}

	public void setHosAid(String hosAid) {
		this.hosAid = hosAid;
	}

	public String getApplyResult() {
		return this.applyResult;
	}

	public void setApplyResult(String applyResult) {
		this.applyResult = applyResult;
	}

	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyType() {
		return this.applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public HospitalizedPatient getHospitalizedPatient() {
		return this.hospitalizedPatient;
	}

	public void setHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		this.hospitalizedPatient = hospitalizedPatient;
	}

}