package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OUTPATIENT_REGISTRATION database table.
 * 
 */
@Entity
@Table(name="OUTPATIENT_REGISTRATION")
@NamedQuery(name="OutpatientRegistration.findAll", query="SELECT o FROM OutpatientRegistration o")
public class OutpatientRegistration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REG_ID")
	private String regId;

	@Temporal(TemporalType.DATE)
	@Column(name="DO_DATE")
	private Date doDate;

	@Column(name="REG_STATUS")
	private String regStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="REG_TIME")
	private Date regTime;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="TIME_TYPE")
	private String timeType;

	//bi-directional many-to-one association to Examination
	@OneToMany(mappedBy="outpatientRegistration")
	private List<Examination> examinations;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="outpatientRegistration")
	private List<History> histories;

	//bi-directional many-to-one association to OutpatientPay
	@OneToMany(mappedBy="outpatientRegistration")
	private List<OutpatientPay> outpatientPays;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation1;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="EMP_YGXH")
	private EmpInformation empInformation2;

	//bi-directional many-to-one association to Examination
	@ManyToOne
	@JoinColumn(name="EXAM_ID")
	private Examination examination;

	//bi-directional many-to-one association to History
	@ManyToOne
	@JoinColumn(name="HISTORY_ID")
	private History history;

	//bi-directional many-to-one association to MedicalCard
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private MedicalCard medicalCard;

	//bi-directional many-to-one association to OutpatientPay
	@ManyToOne
	@JoinColumn(name="PAY_ID")
	private OutpatientPay outpatientPay;

	//bi-directional many-to-one association to OutMedicalRecord
	@ManyToOne
	@JoinColumn(name="OUT_MID")
	private OutMedicalRecord outMedicalRecord;

	//bi-directional many-to-one association to TechnicalPost
	@ManyToOne
	@JoinColumn(name="TP_ID")
	private TechnicalPost technicalPost;

	//bi-directional many-to-one association to OutMedicalRecord
	@OneToMany(mappedBy="outpatientRegistration")
	private List<OutMedicalRecord> outMedicalRecords;

	public OutpatientRegistration() {
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Date getDoDate() {
		return this.doDate;
	}

	public void setDoDate(Date doDate) {
		this.doDate = doDate;
	}

	public String getRegStatus() {
		return this.regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getTimeType() {
		return this.timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public List<Examination> getExaminations() {
		return this.examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public Examination addExamination(Examination examination) {
		getExaminations().add(examination);
		examination.setOutpatientRegistration(this);

		return examination;
	}

	public Examination removeExamination(Examination examination) {
		getExaminations().remove(examination);
		examination.setOutpatientRegistration(null);

		return examination;
	}

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setOutpatientRegistration(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setOutpatientRegistration(null);

		return history;
	}

	public List<OutpatientPay> getOutpatientPays() {
		return this.outpatientPays;
	}

	public void setOutpatientPays(List<OutpatientPay> outpatientPays) {
		this.outpatientPays = outpatientPays;
	}

	public OutpatientPay addOutpatientPay(OutpatientPay outpatientPay) {
		getOutpatientPays().add(outpatientPay);
		outpatientPay.setOutpatientRegistration(this);

		return outpatientPay;
	}

	public OutpatientPay removeOutpatientPay(OutpatientPay outpatientPay) {
		getOutpatientPays().remove(outpatientPay);
		outpatientPay.setOutpatientRegistration(null);

		return outpatientPay;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public Examination getExamination() {
		return this.examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public History getHistory() {
		return this.history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public MedicalCard getMedicalCard() {
		return this.medicalCard;
	}

	public void setMedicalCard(MedicalCard medicalCard) {
		this.medicalCard = medicalCard;
	}

	public OutpatientPay getOutpatientPay() {
		return this.outpatientPay;
	}

	public void setOutpatientPay(OutpatientPay outpatientPay) {
		this.outpatientPay = outpatientPay;
	}

	public OutMedicalRecord getOutMedicalRecord() {
		return this.outMedicalRecord;
	}

	public void setOutMedicalRecord(OutMedicalRecord outMedicalRecord) {
		this.outMedicalRecord = outMedicalRecord;
	}

	public TechnicalPost getTechnicalPost() {
		return this.technicalPost;
	}

	public void setTechnicalPost(TechnicalPost technicalPost) {
		this.technicalPost = technicalPost;
	}

	public List<OutMedicalRecord> getOutMedicalRecords() {
		return this.outMedicalRecords;
	}

	public void setOutMedicalRecords(List<OutMedicalRecord> outMedicalRecords) {
		this.outMedicalRecords = outMedicalRecords;
	}

	public OutMedicalRecord addOutMedicalRecord(OutMedicalRecord outMedicalRecord) {
		getOutMedicalRecords().add(outMedicalRecord);
		outMedicalRecord.setOutpatientRegistration(this);

		return outMedicalRecord;
	}

	public OutMedicalRecord removeOutMedicalRecord(OutMedicalRecord outMedicalRecord) {
		getOutMedicalRecords().remove(outMedicalRecord);
		outMedicalRecord.setOutpatientRegistration(null);

		return outMedicalRecord;
	}

}