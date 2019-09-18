package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
	
	@Column(name="STATUS")
	private String status;

	//bi-directional many-to-one association to AbuEmp
	@OneToMany(mappedBy="ambulanceRecord")
	private List<AbuEmp> abuEmps;

	//bi-directional many-to-one association to OtherProject
	@ManyToOne
	@JoinColumn(name="PROJECT_ID")
	private OtherProject otherProject;

	public AmbulanceRecord() {
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<AbuEmp> getAbuEmps() {
		return this.abuEmps;
	}

	public void setAbuEmps(List<AbuEmp> abuEmps) {
		this.abuEmps = abuEmps;
	}

	public AbuEmp addAbuEmp(AbuEmp abuEmp) {
		getAbuEmps().add(abuEmp);
		abuEmp.setAmbulanceRecord(this);

		return abuEmp;
	}

	public AbuEmp removeAbuEmp(AbuEmp abuEmp) {
		getAbuEmps().remove(abuEmp);
		abuEmp.setAmbulanceRecord(null);

		return abuEmp;
	}

	public OtherProject getOtherProject() {
		return this.otherProject;
	}

	public void setOtherProject(OtherProject otherProject) {
		this.otherProject = otherProject;
	}

}