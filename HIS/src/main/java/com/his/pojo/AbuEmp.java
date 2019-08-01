package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ABU_EMP database table.
 * 
 */
@Entity
@Table(name="ABU_EMP")
@NamedQuery(name="AbuEmp.findAll", query="SELECT a FROM AbuEmp a")
public class AbuEmp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AbuEmpPK id;

	@Column(name="ABU_DUTY")
	private String abuDuty;

	//bi-directional many-to-one association to AmbulanceRecord
	@ManyToOne
	@JoinColumn(name="AMB_ID", insertable=false, updatable=false)
	private AmbulanceRecord ambulanceRecord;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH", insertable=false, updatable=false)
	private EmpInformation empInformation;

	public AbuEmp() {
	}

	public AbuEmpPK getId() {
		return this.id;
	}

	public void setId(AbuEmpPK id) {
		this.id = id;
	}

	public String getAbuDuty() {
		return this.abuDuty;
	}

	public void setAbuDuty(String abuDuty) {
		this.abuDuty = abuDuty;
	}

	public AmbulanceRecord getAmbulanceRecord() {
		return this.ambulanceRecord;
	}

	public void setAmbulanceRecord(AmbulanceRecord ambulanceRecord) {
		this.ambulanceRecord = ambulanceRecord;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}