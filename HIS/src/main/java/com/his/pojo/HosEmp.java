package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HOS_EMP database table.
 * 
 */
@Entity
@Table(name="HOS_EMP")
@NamedQuery(name="HosEmp.findAll", query="SELECT h FROM HosEmp h")
public class HosEmp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HosEmpPK id;

	@Column(name="HOS_DUTY")
	private String hosDuty;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH", insertable=false, updatable=false)
	private EmpInformation empInformation;

	//bi-directional many-to-one association to HospitalizedPatient
	@ManyToOne
	@JoinColumn(name="HOSP_ID", insertable=false, updatable=false)
	private HospitalizedPatient hospitalizedPatient;

	public HosEmp() {
	}

	public HosEmpPK getId() {
		return this.id;
	}

	public void setId(HosEmpPK id) {
		this.id = id;
	}

	public String getHosDuty() {
		return this.hosDuty;
	}

	public void setHosDuty(String hosDuty) {
		this.hosDuty = hosDuty;
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