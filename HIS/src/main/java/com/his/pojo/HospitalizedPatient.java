package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the HOSPITALIZED_PATIENTS database table.
 * 
 */
@Entity
@Table(name="HOSPITALIZED_PATIENTS")
@NamedQuery(name="HospitalizedPatient.findAll", query="SELECT h FROM HospitalizedPatient h")
public class HospitalizedPatient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOSP_ID")
	private String hospId;

	private BigDecimal balance;

	@Column(name="DEPOSIT_MONEY")
	private BigDecimal depositMoney;

	@Column(name="HOS_SOURCE")
	private String hosSource;

	@Column(name="HOSP_STATE")
	private String hospState;

	@Temporal(TemporalType.DATE)
	@Column(name="REGISTER_TIME")
	private Date registerTime;

	//bi-directional many-to-one association to BedTransRecord
	@OneToMany(mappedBy="hospitalizedPatient")
	private List<BedTransRecord> bedTransRecords;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-one association to HosBed
	@ManyToOne
	@JoinColumn(name="HOS_BID")
	private HosBed hosBed;

	//bi-directional many-to-one association to MedicalCard
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private MedicalCard medicalCard;

	//bi-directional many-to-one association to MedicalRecord
	@ManyToOne
	@JoinColumn(name="MED_RID")
	private MedicalRecord medicalRecord;

	//bi-directional many-to-one association to OutHospitaiRecord
	@ManyToOne
	@JoinColumn(name="OUT_RID")
	private OutHospitaiRecord outHospitaiRecord;

	//bi-directional many-to-one association to WardRoom
	@ManyToOne
	@JoinColumn(name="WROOM_ID")
	private WardRoom wardRoom;

	//bi-directional many-to-one association to HosBed
	@OneToMany(mappedBy="hospitalizedPatient")
	private List<HosBed> hosBeds;

	//bi-directional many-to-one association to HosPatientsApply
	@OneToMany(mappedBy="hospitalizedPatient")
	private List<HosPatientsApply> hosPatientsApplies;

	//bi-directional many-to-one association to HosPayRecord
	@OneToMany(mappedBy="hospitalizedPatient")
	private List<HosPayRecord> hosPayRecords;

	//bi-directional many-to-one association to MedicalRecord
	@OneToMany(mappedBy="hospitalizedPatient")
	private List<MedicalRecord> medicalRecords;

	//bi-directional many-to-one association to OutHospitaiRecord
	@OneToMany(mappedBy="hospitalizedPatient")
	private List<OutHospitaiRecord> outHospitaiRecords;

	//bi-directional many-to-one association to TransOfficeRecord
	@OneToMany(mappedBy="hospitalizedPatient")
	private List<TransOfficeRecord> transOfficeRecords;

	//bi-directional many-to-one association to HosEmp
	@OneToMany(mappedBy="hospitalizedPatient")
	private List<HosEmp> hosEmps;

	public HospitalizedPatient() {
	}

	public String getHospId() {
		return this.hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getDepositMoney() {
		return this.depositMoney;
	}

	public void setDepositMoney(BigDecimal depositMoney) {
		this.depositMoney = depositMoney;
	}

	public String getHosSource() {
		return this.hosSource;
	}

	public void setHosSource(String hosSource) {
		this.hosSource = hosSource;
	}

	public String getHospState() {
		return this.hospState;
	}

	public void setHospState(String hospState) {
		this.hospState = hospState;
	}

	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public List<BedTransRecord> getBedTransRecords() {
		return this.bedTransRecords;
	}

	public void setBedTransRecords(List<BedTransRecord> bedTransRecords) {
		this.bedTransRecords = bedTransRecords;
	}

	public BedTransRecord addBedTransRecord(BedTransRecord bedTransRecord) {
		getBedTransRecords().add(bedTransRecord);
		bedTransRecord.setHospitalizedPatient(this);

		return bedTransRecord;
	}

	public BedTransRecord removeBedTransRecord(BedTransRecord bedTransRecord) {
		getBedTransRecords().remove(bedTransRecord);
		bedTransRecord.setHospitalizedPatient(null);

		return bedTransRecord;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public HosBed getHosBed() {
		return this.hosBed;
	}

	public void setHosBed(HosBed hosBed) {
		this.hosBed = hosBed;
	}

	public MedicalCard getMedicalCard() {
		return this.medicalCard;
	}

	public void setMedicalCard(MedicalCard medicalCard) {
		this.medicalCard = medicalCard;
	}

	public MedicalRecord getMedicalRecord() {
		return this.medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public OutHospitaiRecord getOutHospitaiRecord() {
		return this.outHospitaiRecord;
	}

	public void setOutHospitaiRecord(OutHospitaiRecord outHospitaiRecord) {
		this.outHospitaiRecord = outHospitaiRecord;
	}

	public WardRoom getWardRoom() {
		return this.wardRoom;
	}

	public void setWardRoom(WardRoom wardRoom) {
		this.wardRoom = wardRoom;
	}

	public List<HosBed> getHosBeds() {
		return this.hosBeds;
	}

	public void setHosBeds(List<HosBed> hosBeds) {
		this.hosBeds = hosBeds;
	}

	public HosBed addHosBed(HosBed hosBed) {
		getHosBeds().add(hosBed);
		hosBed.setHospitalizedPatient(this);

		return hosBed;
	}

	public HosBed removeHosBed(HosBed hosBed) {
		getHosBeds().remove(hosBed);
		hosBed.setHospitalizedPatient(null);

		return hosBed;
	}

	public List<HosPatientsApply> getHosPatientsApplies() {
		return this.hosPatientsApplies;
	}

	public void setHosPatientsApplies(List<HosPatientsApply> hosPatientsApplies) {
		this.hosPatientsApplies = hosPatientsApplies;
	}

	public HosPatientsApply addHosPatientsApply(HosPatientsApply hosPatientsApply) {
		getHosPatientsApplies().add(hosPatientsApply);
		hosPatientsApply.setHospitalizedPatient(this);

		return hosPatientsApply;
	}

	public HosPatientsApply removeHosPatientsApply(HosPatientsApply hosPatientsApply) {
		getHosPatientsApplies().remove(hosPatientsApply);
		hosPatientsApply.setHospitalizedPatient(null);

		return hosPatientsApply;
	}

	public List<HosPayRecord> getHosPayRecords() {
		return this.hosPayRecords;
	}

	public void setHosPayRecords(List<HosPayRecord> hosPayRecords) {
		this.hosPayRecords = hosPayRecords;
	}

	public HosPayRecord addHosPayRecord(HosPayRecord hosPayRecord) {
		getHosPayRecords().add(hosPayRecord);
		hosPayRecord.setHospitalizedPatient(this);

		return hosPayRecord;
	}

	public HosPayRecord removeHosPayRecord(HosPayRecord hosPayRecord) {
		getHosPayRecords().remove(hosPayRecord);
		hosPayRecord.setHospitalizedPatient(null);

		return hosPayRecord;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return this.medicalRecords;
	}

	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		getMedicalRecords().add(medicalRecord);
		medicalRecord.setHospitalizedPatient(this);

		return medicalRecord;
	}

	public MedicalRecord removeMedicalRecord(MedicalRecord medicalRecord) {
		getMedicalRecords().remove(medicalRecord);
		medicalRecord.setHospitalizedPatient(null);

		return medicalRecord;
	}

	public List<OutHospitaiRecord> getOutHospitaiRecords() {
		return this.outHospitaiRecords;
	}

	public void setOutHospitaiRecords(List<OutHospitaiRecord> outHospitaiRecords) {
		this.outHospitaiRecords = outHospitaiRecords;
	}

	public OutHospitaiRecord addOutHospitaiRecord(OutHospitaiRecord outHospitaiRecord) {
		getOutHospitaiRecords().add(outHospitaiRecord);
		outHospitaiRecord.setHospitalizedPatient(this);

		return outHospitaiRecord;
	}

	public OutHospitaiRecord removeOutHospitaiRecord(OutHospitaiRecord outHospitaiRecord) {
		getOutHospitaiRecords().remove(outHospitaiRecord);
		outHospitaiRecord.setHospitalizedPatient(null);

		return outHospitaiRecord;
	}

	public List<TransOfficeRecord> getTransOfficeRecords() {
		return this.transOfficeRecords;
	}

	public void setTransOfficeRecords(List<TransOfficeRecord> transOfficeRecords) {
		this.transOfficeRecords = transOfficeRecords;
	}

	public TransOfficeRecord addTransOfficeRecord(TransOfficeRecord transOfficeRecord) {
		getTransOfficeRecords().add(transOfficeRecord);
		transOfficeRecord.setHospitalizedPatient(this);

		return transOfficeRecord;
	}

	public TransOfficeRecord removeTransOfficeRecord(TransOfficeRecord transOfficeRecord) {
		getTransOfficeRecords().remove(transOfficeRecord);
		transOfficeRecord.setHospitalizedPatient(null);

		return transOfficeRecord;
	}

	public List<HosEmp> getHosEmps() {
		return this.hosEmps;
	}

	public void setHosEmps(List<HosEmp> hosEmps) {
		this.hosEmps = hosEmps;
	}

	public HosEmp addHosEmp(HosEmp hosEmp) {
		getHosEmps().add(hosEmp);
		hosEmp.setHospitalizedPatient(this);

		return hosEmp;
	}

	public HosEmp removeHosEmp(HosEmp hosEmp) {
		getHosEmps().remove(hosEmp);
		hosEmp.setHospitalizedPatient(null);

		return hosEmp;
	}

}