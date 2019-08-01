package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USE_DRUG_RECORD database table.
 * 
 */
@Entity
@Table(name="USE_DRUG_RECORD")
@NamedQuery(name="UseDrugRecord.findAll", query="SELECT u FROM UseDrugRecord u")
public class UseDrugRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INJ_ID")
	private String injId;

	@Temporal(TemporalType.DATE)
	@Column(name="INJ_TIME")
	private Date injTime;

	//bi-directional many-to-one association to InjectionDetail
	@OneToMany(mappedBy="useDrugRecord")
	private List<InjectionDetail> injectionDetails;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional one-to-one association to Prescription
	@OneToOne
	@JoinColumn(name="PRESCRIPTION_ID")
	private Prescription prescription;

	public UseDrugRecord() {
	}

	public String getInjId() {
		return this.injId;
	}

	public void setInjId(String injId) {
		this.injId = injId;
	}

	public Date getInjTime() {
		return this.injTime;
	}

	public void setInjTime(Date injTime) {
		this.injTime = injTime;
	}

	public List<InjectionDetail> getInjectionDetails() {
		return this.injectionDetails;
	}

	public void setInjectionDetails(List<InjectionDetail> injectionDetails) {
		this.injectionDetails = injectionDetails;
	}

	public InjectionDetail addInjectionDetail(InjectionDetail injectionDetail) {
		getInjectionDetails().add(injectionDetail);
		injectionDetail.setUseDrugRecord(this);

		return injectionDetail;
	}

	public InjectionDetail removeInjectionDetail(InjectionDetail injectionDetail) {
		getInjectionDetails().remove(injectionDetail);
		injectionDetail.setUseDrugRecord(null);

		return injectionDetail;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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