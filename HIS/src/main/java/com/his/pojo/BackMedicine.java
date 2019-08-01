package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the BACK_MEDICINE database table.
 * 
 */
@Entity
@Table(name="BACK_MEDICINE")
@NamedQuery(name="BackMedicine.findAll", query="SELECT b FROM BackMedicine b")
public class BackMedicine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BACK_ID")
	private String backId;

	@Column(name="BACK_REASON")
	private String backReason;

	@Temporal(TemporalType.DATE)
	@Column(name="BACK_TIME")
	private Date backTime;

	//bi-directional many-to-one association to BackDetail
	@OneToMany(mappedBy="backMedicine")
	private List<BackDetail> backDetails;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to Prescription
	@ManyToOne
	@JoinColumn(name="PRESCRIPTION_ID")
	private Prescription prescription;

	public BackMedicine() {
	}

	public String getBackId() {
		return this.backId;
	}

	public void setBackId(String backId) {
		this.backId = backId;
	}

	public String getBackReason() {
		return this.backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	public Date getBackTime() {
		return this.backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public List<BackDetail> getBackDetails() {
		return this.backDetails;
	}

	public void setBackDetails(List<BackDetail> backDetails) {
		this.backDetails = backDetails;
	}

	public BackDetail addBackDetail(BackDetail backDetail) {
		getBackDetails().add(backDetail);
		backDetail.setBackMedicine(this);

		return backDetail;
	}

	public BackDetail removeBackDetail(BackDetail backDetail) {
		getBackDetails().remove(backDetail);
		backDetail.setBackMedicine(null);

		return backDetail;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

}