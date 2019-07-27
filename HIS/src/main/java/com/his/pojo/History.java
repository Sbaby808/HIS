package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
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

	@Column(name="SYMPTOM_DESC")
	private String symptomDesc;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-one association to HospitalizedPatient
	@ManyToOne
	@JoinColumn(name="HOSP_ID")
	private HospitalizedPatient hospitalizedPatient;

	//bi-directional many-to-one association to Illness
	@ManyToOne
	@JoinColumn(name="ILL_ID")
	private Illness illness;

	//bi-directional many-to-one association to OutpatientRegistration
	@ManyToOne
	@JoinColumn(name="REG_ID")
	private OutpatientRegistration outpatientRegistration;

	//bi-directional many-to-one association to Prescription
	@ManyToOne
	@JoinColumn(name="PRESCRIPTION_ID")
	private Prescription prescription;

	//bi-directional many-to-one association to SolveScheme
	@ManyToOne
	@JoinColumn(name="SCHE_ID")
	private SolveScheme solveScheme;

	//bi-directional many-to-one association to TreatmentEvaluation
	@ManyToOne
	@JoinColumn(name="OUTEVA_ID")
	private TreatmentEvaluation treatmentEvaluation;

	//bi-directional many-to-one association to OutpatientRegistration
	@OneToMany(mappedBy="history")
	private List<OutpatientRegistration> outpatientRegistrations;

	//bi-directional many-to-one association to Prescription
	@OneToMany(mappedBy="history")
	private List<Prescription> prescriptions;

	//bi-directional many-to-one association to SolveScheme
	@OneToMany(mappedBy="history")
	private List<SolveScheme> solveSchemes;

	//bi-directional many-to-one association to TreatmentEvaluation
	@OneToMany(mappedBy="history")
	private List<TreatmentEvaluation> treatmentEvaluations;

	public History() {
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

	public HospitalizedPatient getHospitalizedPatient() {
		return this.hospitalizedPatient;
	}

	public void setHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		this.hospitalizedPatient = hospitalizedPatient;
	}

	public Illness getIllness() {
		return this.illness;
	}

	public void setIllness(Illness illness) {
		this.illness = illness;
	}

	public OutpatientRegistration getOutpatientRegistration() {
		return this.outpatientRegistration;
	}

	public void setOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		this.outpatientRegistration = outpatientRegistration;
	}

	public Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public SolveScheme getSolveScheme() {
		return this.solveScheme;
	}

	public void setSolveScheme(SolveScheme solveScheme) {
		this.solveScheme = solveScheme;
	}

	public TreatmentEvaluation getTreatmentEvaluation() {
		return this.treatmentEvaluation;
	}

	public void setTreatmentEvaluation(TreatmentEvaluation treatmentEvaluation) {
		this.treatmentEvaluation = treatmentEvaluation;
	}

	public List<OutpatientRegistration> getOutpatientRegistrations() {
		return this.outpatientRegistrations;
	}

	public void setOutpatientRegistrations(List<OutpatientRegistration> outpatientRegistrations) {
		this.outpatientRegistrations = outpatientRegistrations;
	}

	public OutpatientRegistration addOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().add(outpatientRegistration);
		outpatientRegistration.setHistory(this);

		return outpatientRegistration;
	}

	public OutpatientRegistration removeOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().remove(outpatientRegistration);
		outpatientRegistration.setHistory(null);

		return outpatientRegistration;
	}

	public List<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Prescription addPrescription(Prescription prescription) {
		getPrescriptions().add(prescription);
		prescription.setHistory(this);

		return prescription;
	}

	public Prescription removePrescription(Prescription prescription) {
		getPrescriptions().remove(prescription);
		prescription.setHistory(null);

		return prescription;
	}

	public List<SolveScheme> getSolveSchemes() {
		return this.solveSchemes;
	}

	public void setSolveSchemes(List<SolveScheme> solveSchemes) {
		this.solveSchemes = solveSchemes;
	}

	public SolveScheme addSolveScheme(SolveScheme solveScheme) {
		getSolveSchemes().add(solveScheme);
		solveScheme.setHistory(this);

		return solveScheme;
	}

	public SolveScheme removeSolveScheme(SolveScheme solveScheme) {
		getSolveSchemes().remove(solveScheme);
		solveScheme.setHistory(null);

		return solveScheme;
	}

	public List<TreatmentEvaluation> getTreatmentEvaluations() {
		return this.treatmentEvaluations;
	}

	public void setTreatmentEvaluations(List<TreatmentEvaluation> treatmentEvaluations) {
		this.treatmentEvaluations = treatmentEvaluations;
	}

	public TreatmentEvaluation addTreatmentEvaluation(TreatmentEvaluation treatmentEvaluation) {
		getTreatmentEvaluations().add(treatmentEvaluation);
		treatmentEvaluation.setHistory(this);

		return treatmentEvaluation;
	}

	public TreatmentEvaluation removeTreatmentEvaluation(TreatmentEvaluation treatmentEvaluation) {
		getTreatmentEvaluations().remove(treatmentEvaluation);
		treatmentEvaluation.setHistory(null);

		return treatmentEvaluation;
	}

}