package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ALLERGY_RECORD database table.
 * 
 */
@Entity
@Table(name="ALLERGY_RECORD")
@NamedQuery(name="AllergyRecord.findAll", query="SELECT a FROM AllergyRecord a")
public class AllergyRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ALLERGY_ID")
	private String allergyId;

	@Column(name="ALLERGY_DESC")
	private String allergyDesc;

	@Temporal(TemporalType.DATE)
	@Column(name="TEST_TIME")
	private Date testTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to MedicalCard
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private MedicalCard medicalCard;

	public AllergyRecord() {
	}

	public String getAllergyId() {
		return this.allergyId;
	}

	public void setAllergyId(String allergyId) {
		this.allergyId = allergyId;
	}

	public String getAllergyDesc() {
		return this.allergyDesc;
	}

	public void setAllergyDesc(String allergyDesc) {
		this.allergyDesc = allergyDesc;
	}

	public Date getTestTime() {
		return this.testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public MedicalCard getMedicalCard() {
		return this.medicalCard;
	}

	public void setMedicalCard(MedicalCard medicalCard) {
		this.medicalCard = medicalCard;
	}

}