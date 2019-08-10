package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the HISTORY database table.
 * 
 */
@Entity
@NamedQuery(name="History.findAll", query="SELECT h FROM History h")
public class History implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HISTORY_ID")
	private String historyId;

	@Temporal(TemporalType.DATE)
	@Column(name="HIS_TIME")
	private Date hisTime;

	@Column(name="OUTEVA_ID")
	private String outevaId;

	@Column(name="PRESCRIPTION_ID")
	private String prescriptionId;

	@Column(name="SYMPTOM_DESC")
	private String symptomDesc;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-one association to Illness
	@ManyToOne
	@JoinColumn(name="ILL_ID")
	private Illness illness;

	//bi-directional one-to-one association to Prescription
	@OneToOne(mappedBy="history")
	private Prescription prescription;

	//bi-directional one-to-one association to SolveScheme
	@OneToMany(mappedBy="history")
	@JSONField(serialize=false)
	private List<SolveScheme> solveScheme;

	//bi-directional one-to-one association to OutpatientRegistration
	@OneToOne
	@JoinColumn(name="REG_ID")
	private OutpatientRegistration outpatientRegistration;

	//bi-directional one-to-one association to TreatmentEvaluation
	@OneToOne(mappedBy="history")
	private TreatmentEvaluation treatmentEvaluation;

	public History() {
	}

	
	public List<SolveScheme> getSolveScheme() {
		return solveScheme;
	}


	public void setSolveScheme(List<SolveScheme> solveScheme) {
		this.solveScheme = solveScheme;
	}


	public String getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public Date getHisTime() {
		return this.hisTime;
	}

	public void setHisTime(Date hisTime) {
		this.hisTime = hisTime;
	}

	public String getOutevaId() {
		return this.outevaId;
	}

	public void setOutevaId(String outevaId) {
		this.outevaId = outevaId;
	}

	public String getPrescriptionId() {
		return this.prescriptionId;
	}

	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getSymptomDesc() {
		return this.symptomDesc;
	}

	public void setSymptomDesc(String symptomDesc) {
		this.symptomDesc = symptomDesc;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Illness getIllness() {
		return this.illness;
	}

	public void setIllness(Illness illness) {
		this.illness = illness;
	}

	public Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public OutpatientRegistration getOutpatientRegistration() {
		return this.outpatientRegistration;
	}

	public void setOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		this.outpatientRegistration = outpatientRegistration;
	}

	public TreatmentEvaluation getTreatmentEvaluation() {
		return this.treatmentEvaluation;
	}

	public void setTreatmentEvaluation(TreatmentEvaluation treatmentEvaluation) {
		this.treatmentEvaluation = treatmentEvaluation;
	}

}