package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

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

	@Column(name="EXAM_ID")
	private String examId;

	@Column(name="HISTORY_ID")
	private String historyId;

	@Column(name="OUT_MID")
	private String outMid;

	@Column(name="PAY_ID")
	private String payId;

	@Column(name="REG_STATUS")
	private String regStatus;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name="REG_TIME")
	private Date regTime;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="TIME_TYPE")
	private String timeType;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-one association to MedicalCard
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private MedicalCard medicalCard;

	//bi-directional many-to-one association to TechnicalPost
	@ManyToOne
	@JoinColumn(name="TP_ID")
	private TechnicalPost technicalPost;

	//bi-directional many-to-one association to RegEmp
	@OneToMany(mappedBy="outpatientRegistration")
	@JSONField(serialize=false)
	private List<RegEmp> regEmps;

	//bi-directional one-to-one association to OutMedicalRecord
	@OneToOne(mappedBy="outpatientRegistration")
	private OutMedicalRecord outMedicalRecord;

	//bi-directional one-to-one association to History
	@OneToOne(mappedBy="outpatientRegistration")
	@JSONField(serialize=false)
	private History history;

	//bi-directional one-to-one association to Examination
	@OneToOne(mappedBy="outpatientRegistration")
	private Examination examination;

	//bi-directional one-to-one association to OutpatientPay
	@OneToOne(mappedBy="outpatientRegistration")
	private OutpatientPay outpatientPay;

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

	public String getExamId() {
		return this.examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public String getOutMid() {
		return this.outMid;
	}

	public void setOutMid(String outMid) {
		this.outMid = outMid;
	}

	public String getPayId() {
		return this.payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
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

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public MedicalCard getMedicalCard() {
		return this.medicalCard;
	}

	public void setMedicalCard(MedicalCard medicalCard) {
		this.medicalCard = medicalCard;
	}

	public TechnicalPost getTechnicalPost() {
		return this.technicalPost;
	}

	public void setTechnicalPost(TechnicalPost technicalPost) {
		this.technicalPost = technicalPost;
	}

	public List<RegEmp> getRegEmps() {
		return this.regEmps;
	}

	public void setRegEmps(List<RegEmp> regEmps) {
		this.regEmps = regEmps;
	}

	public RegEmp addRegEmp(RegEmp regEmp) {
		getRegEmps().add(regEmp);
		regEmp.setOutpatientRegistration(this);

		return regEmp;
	}

	public RegEmp removeRegEmp(RegEmp regEmp) {
		getRegEmps().remove(regEmp);
		regEmp.setOutpatientRegistration(null);

		return regEmp;
	}

	public OutMedicalRecord getOutMedicalRecord() {
		return this.outMedicalRecord;
	}

	public void setOutMedicalRecord(OutMedicalRecord outMedicalRecord) {
		this.outMedicalRecord = outMedicalRecord;
	}

	public History getHistory() {
		return this.history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public Examination getExamination() {
		return this.examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public OutpatientPay getOutpatientPay() {
		return this.outpatientPay;
	}

	public void setOutpatientPay(OutpatientPay outpatientPay) {
		this.outpatientPay = outpatientPay;
	}

}