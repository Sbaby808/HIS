package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the AMBULANCE_RECORD database table.
 * 
 */
@Entity
@Table(name="AMBULANCE_RECORD")
@NamedQuery(name="AmbulanceRecord.findAll", query="SELECT a FROM AmbulanceRecord a")
public class AmbulanceRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AMB_ID")
	private String ambId;

	@Column(name="AMB_ADDRESS")
	private String ambAddress;

	@Column(name="AMB_TEL")
	private String ambTel;

	@Temporal(TemporalType.DATE)
	@Column(name="AMB_TIME")
	private Date ambTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="EMP_YGXH")
	private EmpInformation empInformation1;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation2;

	//bi-directional many-to-one association to OtherProject
	@ManyToOne
	@JoinColumn(name="PROJECT_ID")
	private OtherProject otherProject;

	public AmbulanceRecord() {
	}

	public String getAmbId() {
		return this.ambId;
	}

	public void setAmbId(String ambId) {
		this.ambId = ambId;
	}

	public String getAmbAddress() {
		return this.ambAddress;
	}

	public void setAmbAddress(String ambAddress) {
		this.ambAddress = ambAddress;
	}

	public String getAmbTel() {
		return this.ambTel;
	}

	public void setAmbTel(String ambTel) {
		this.ambTel = ambTel;
	}

	public Date getAmbTime() {
		return this.ambTime;
	}

	public void setAmbTime(Date ambTime) {
		this.ambTime = ambTime;
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

	public OtherProject getOtherProject() {
		return this.otherProject;
	}

	public void setOtherProject(OtherProject otherProject) {
		this.otherProject = otherProject;
	}

}