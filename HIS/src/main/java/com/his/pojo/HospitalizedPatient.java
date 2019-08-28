package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

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
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name="HOSP_ID")
	private String hospId;

	private BigDecimal balance;

	@Column(name="DEPOSIT_MONEY")
	private BigDecimal depositMoney;

	@Column(name="HOS_BID")
	private String hosBid;

	@Column(name="HOS_SOURCE")
	private String hosSource;

	@Column(name="HOSP_STATE")
	private String hospState;

	@Column(name="MED_RID")
	private String medRid;

	@Column(name="OUT_RID")
	private String outRid;

	@JSONField(format="yyyy-MM-dd")
	@Column(name="REGISTER_TIME")
	private Date registerTime;
	
	@Column(name="HOS_NOTE")
	private String hosNote;

	//bi-directional many-to-one association to BedTransRecord
	@OneToMany(mappedBy="hospitalizedPatient")
	@JSONField(serialize=false)
	private List<BedTransRecord> bedTransRecords;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-one association to MedicalCard
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private MedicalCard medicalCard;

	//bi-directional many-to-one association to WardRoom
	@ManyToOne
	@JoinColumn(name="WROOM_ID")
	private WardRoom wardRoom;

	//bi-directional many-to-one association to HosEmp
	@OneToMany(mappedBy="hospitalizedPatient")
	@JSONField(serialize=false)
	private List<HosEmp> hosEmps;

	//bi-directional many-to-one association to HosPatientsApply
	@OneToMany(mappedBy="hospitalizedPatient")
	@JSONField(serialize=false)
	private List<HosPatientsApply> hosPatientsApplies;

	//bi-directional many-to-one association to HosPayRecord
	@OneToMany(mappedBy="hospitalizedPatient")
	@JSONField(serialize=false)
	private List<HosPayRecord> hosPayRecords;

	//bi-directional many-to-one association to TransOfficeRecord
	@OneToMany(mappedBy="hospitalizedPatient")
	@JSONField(serialize=false)
	private List<TransOfficeRecord> transOfficeRecords;

	//bi-directional one-to-one association to MedicalRecord
	@OneToOne(mappedBy="hospitalizedPatient")
	@JSONField(serialize=false)
	private MedicalRecord medicalRecord;

	//bi-directional one-to-one association to HosBed
	@OneToOne(mappedBy="hospitalizedPatient")
	private HosBed hosBed;

	//bi-directional one-to-one association to OutHospitaiRecord
	@OneToOne(mappedBy="hospitalizedPatient")
	@JSONField(serialize=false)
	private OutHospitaiRecord outHospitaiRecord;

	public HospitalizedPatient() {
	}

	
	
	public String getHosNote() {
		return hosNote;
	}

	public void setHosNote(String hosNote) {
		this.hosNote = hosNote;
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

	public String getHosBid() {
		return this.hosBid;
	}

	public void setHosBid(String hosBid) {
		this.hosBid = hosBid;
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

	public String getMedRid() {
		return this.medRid;
	}

	public void setMedRid(String medRid) {
		this.medRid = medRid;
	}

	public String getOutRid() {
		return this.outRid;
	}

	public void setOutRid(String outRid) {
		this.outRid = outRid;
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

	public MedicalCard getMedicalCard() {
		return this.medicalCard;
	}

	public void setMedicalCard(MedicalCard medicalCard) {
		this.medicalCard = medicalCard;
	}

	public WardRoom getWardRoom() {
		return this.wardRoom;
	}

	public void setWardRoom(WardRoom wardRoom) {
		this.wardRoom = wardRoom;
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

	public MedicalRecord getMedicalRecord() {
		return this.medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public HosBed getHosBed() {
		return this.hosBed;
	}

	public void setHosBed(HosBed hosBed) {
		this.hosBed = hosBed;
	}

	public OutHospitaiRecord getOutHospitaiRecord() {
		return this.outHospitaiRecord;
	}

	public void setOutHospitaiRecord(OutHospitaiRecord outHospitaiRecord) {
		this.outHospitaiRecord = outHospitaiRecord;
	}

}