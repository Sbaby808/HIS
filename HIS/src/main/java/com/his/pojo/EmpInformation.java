package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the EMP_INFORMATION database table.
 * 
 */
@Entity
@Table(name="EMP_INFORMATION")
@NamedQuery(name="EmpInformation.findAll", query="SELECT e FROM EmpInformation e")
public class EmpInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String ygxh;

	@Temporal(TemporalType.DATE)
	@Column(name="YG_BIRTH")
	private Date ygBirth;

	@Column(name="YG_EDUCATION")
	private String ygEducation;

	@Column(name="YG_EMAIL")
	private String ygEmail;

	@Column(name="YG_NAME")
	private String ygName;

	@Column(name="YG_NATION")
	private String ygNation;

	@Column(name="YG_PASSWORD")
	private String ygPassword;

	@Column(name="YG_PLACE")
	private String ygPlace;

	@Column(name="YG_SEX")
	private String ygSex;

	@Column(name="YG_TEL")
	private String ygTel;

	//bi-directional many-to-one association to AllergyRecord
	@OneToMany(mappedBy="empInformation")
	private List<AllergyRecord> allergyRecords;

	//bi-directional many-to-one association to AllocationOutbound
	@OneToMany(mappedBy="empInformation")
	private List<AllocationOutbound> allocationOutbounds;

	//bi-directional many-to-one association to AmbulanceRecord
	@OneToMany(mappedBy="empInformation1")
	private List<AmbulanceRecord> ambulanceRecords1;

	//bi-directional many-to-one association to AmbulanceRecord
	@OneToMany(mappedBy="empInformation2")
	private List<AmbulanceRecord> ambulanceRecords2;

	//bi-directional many-to-one association to AskleaveRecord
	@OneToMany(mappedBy="empInformation")
	private List<AskleaveRecord> askleaveRecords;

	//bi-directional many-to-one association to BackMedicine
	@OneToMany(mappedBy="empInformation")
	private List<BackMedicine> backMedicines;

	//bi-directional many-to-one association to BedTransRecord
	@OneToMany(mappedBy="empInformation")
	private List<BedTransRecord> bedTransRecords;

	//bi-directional many-to-one association to BusinessRecord
	@OneToMany(mappedBy="empInformation")
	private List<BusinessRecord> businessRecords;

	//bi-directional many-to-one association to CheckPay
	@OneToMany(mappedBy="empInformation")
	private List<CheckPay> checkPays;

	//bi-directional many-to-one association to CheckPayRecord
	@OneToMany(mappedBy="empInformation")
	private List<CheckPayRecord> checkPayRecords;

	//bi-directional many-to-one association to CheckResultForm
	@OneToMany(mappedBy="empInformation")
	private List<CheckResultForm> checkResultForms;

	//bi-directional many-to-one association to CheckWork
	@OneToMany(mappedBy="empInformation")
	private List<CheckWork> checkWorks;

	//bi-directional many-to-one association to DamagedMedicine
	@OneToMany(mappedBy="empInformation")
	private List<DamagedMedicine> damagedMedicines;

	//bi-directional many-to-one association to DrugScrap
	@OneToMany(mappedBy="empInformation")
	private List<DrugScrap> drugScraps;

	//bi-directional many-to-one association to TechnicalPost
	@ManyToOne
	@JoinColumn(name="TP_ID")
	private TechnicalPost technicalPost;

	//bi-directional many-to-one association to WaitingRoom
	@ManyToOne
	@JoinColumn(name="WAITING_ROOM_ID")
	private WaitingRoom waitingRoom;

	//bi-directional many-to-one association to Examination
	@OneToMany(mappedBy="empInformation")
	private List<Examination> examinations;

	//bi-directional many-to-one association to HospitalizedPatient
	@OneToMany(mappedBy="empInformation1")
	private List<HospitalizedPatient> hospitalizedPatients1;

	//bi-directional many-to-one association to HospitalizedPatient
	@OneToMany(mappedBy="empInformation2")
	private List<HospitalizedPatient> hospitalizedPatients2;

	//bi-directional many-to-one association to HosDrugRecord
	@OneToMany(mappedBy="empInformation")
	private List<HosDrugRecord> hosDrugRecords;

	//bi-directional many-to-one association to HosPatientsApply
	@OneToMany(mappedBy="empInformation")
	private List<HosPatientsApply> hosPatientsApplies;

	//bi-directional many-to-one association to HosPayRecord
	@OneToMany(mappedBy="empInformation")
	private List<HosPayRecord> hosPayRecords;

	//bi-directional many-to-one association to MedicinePay
	@OneToMany(mappedBy="empInformation")
	private List<MedicinePay> medicinePays;

	//bi-directional many-to-one association to OperationPay
	@OneToMany(mappedBy="empInformation")
	private List<OperationPay> operationPays;

	//bi-directional many-to-one association to OperationRecord
	@OneToMany(mappedBy="empInformation1")
	private List<OperationRecord> operationRecords1;

	//bi-directional many-to-one association to OperationRecord
	@OneToMany(mappedBy="empInformation2")
	private List<OperationRecord> operationRecords2;

	//bi-directional many-to-one association to OperationRecord
	@OneToMany(mappedBy="empInformation3")
	private List<OperationRecord> operationRecords3;

	//bi-directional many-to-one association to OperationRecord
	@OneToMany(mappedBy="empInformation4")
	private List<OperationRecord> operationRecords4;

	//bi-directional many-to-one association to OperPayRecord
	@OneToMany(mappedBy="empInformation")
	private List<OperPayRecord> operPayRecords;

	//bi-directional many-to-one association to OutpatientPay
	@OneToMany(mappedBy="empInformation")
	private List<OutpatientPay> outpatientPays;

	//bi-directional many-to-one association to OutpatientRegistration
	@OneToMany(mappedBy="empInformation1")
	private List<OutpatientRegistration> outpatientRegistrations1;

	//bi-directional many-to-one association to OutpatientRegistration
	@OneToMany(mappedBy="empInformation2")
	private List<OutpatientRegistration> outpatientRegistrations2;

	//bi-directional many-to-one association to OutpatientRequestionMedicine
	@OneToMany(mappedBy="empInformation")
	private List<OutpatientRequestionMedicine> outpatientRequestionMedicines;

	//bi-directional many-to-one association to Outstock
	@OneToMany(mappedBy="empInformation")
	private List<Outstock> outstocks;

	//bi-directional many-to-one association to OutHospitaiRecord
	@OneToMany(mappedBy="empInformation")
	private List<OutHospitaiRecord> outHospitaiRecords;

	//bi-directional many-to-one association to OutPrePay
	@OneToMany(mappedBy="empInformation")
	private List<OutPrePay> outPrePays;

	//bi-directional many-to-one association to PhaIn
	@OneToMany(mappedBy="empInformation")
	private List<PhaIn> phaIns;

	//bi-directional many-to-one association to PsInventoryTaking
	@OneToMany(mappedBy="empInformation")
	private List<PsInventoryTaking> psInventoryTakings;

	//bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy="empInformation")
	private List<Purchase> purchases;

	//bi-directional many-to-one association to PurchaseCheck
	@OneToMany(mappedBy="empInformation")
	private List<PurchaseCheck> purchaseChecks;

	//bi-directional many-to-one association to PutstockBack
	@OneToMany(mappedBy="empInformation")
	private List<PutstockBack> putstockBacks;

	//bi-directional many-to-one association to PutStock
	@OneToMany(mappedBy="empInformation")
	private List<PutStock> putStocks;

	//bi-directional many-to-one association to Transfer
	@OneToMany(mappedBy="empInformation")
	private List<Transfer> transfers;

	//bi-directional many-to-one association to TransOfficeRecord
	@OneToMany(mappedBy="empInformation")
	private List<TransOfficeRecord> transOfficeRecords;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="empInformation")
	private List<UserRole> userRoles;

	//bi-directional many-to-one association to UseDrugRecord
	@OneToMany(mappedBy="empInformation")
	private List<UseDrugRecord> useDrugRecords;

	//bi-directional many-to-one association to WaitingRoom
	@OneToMany(mappedBy="empInformation")
	private List<WaitingRoom> waitingRooms;

	//bi-directional many-to-one association to WorkoverRecord
	@OneToMany(mappedBy="empInformation")
	private List<WorkoverRecord> workoverRecords;

	//bi-directional many-to-one association to WorkTime
	@OneToMany(mappedBy="empInformation1")
	private List<WorkTime> workTimes1;

	//bi-directional many-to-one association to WorkTime
	@OneToMany(mappedBy="empInformation2")
	private List<WorkTime> workTimes2;

	public EmpInformation() {
	}

	public String getYgxh() {
		return this.ygxh;
	}

	public void setYgxh(String ygxh) {
		this.ygxh = ygxh;
	}

	public Date getYgBirth() {
		return this.ygBirth;
	}

	public void setYgBirth(Date ygBirth) {
		this.ygBirth = ygBirth;
	}

	public String getYgEducation() {
		return this.ygEducation;
	}

	public void setYgEducation(String ygEducation) {
		this.ygEducation = ygEducation;
	}

	public String getYgEmail() {
		return this.ygEmail;
	}

	public void setYgEmail(String ygEmail) {
		this.ygEmail = ygEmail;
	}

	public String getYgName() {
		return this.ygName;
	}

	public void setYgName(String ygName) {
		this.ygName = ygName;
	}

	public String getYgNation() {
		return this.ygNation;
	}

	public void setYgNation(String ygNation) {
		this.ygNation = ygNation;
	}

	public String getYgPassword() {
		return this.ygPassword;
	}

	public void setYgPassword(String ygPassword) {
		this.ygPassword = ygPassword;
	}

	public String getYgPlace() {
		return this.ygPlace;
	}

	public void setYgPlace(String ygPlace) {
		this.ygPlace = ygPlace;
	}

	public String getYgSex() {
		return this.ygSex;
	}

	public void setYgSex(String ygSex) {
		this.ygSex = ygSex;
	}

	public String getYgTel() {
		return this.ygTel;
	}

	public void setYgTel(String ygTel) {
		this.ygTel = ygTel;
	}

	public List<AllergyRecord> getAllergyRecords() {
		return this.allergyRecords;
	}

	public void setAllergyRecords(List<AllergyRecord> allergyRecords) {
		this.allergyRecords = allergyRecords;
	}

	public AllergyRecord addAllergyRecord(AllergyRecord allergyRecord) {
		getAllergyRecords().add(allergyRecord);
		allergyRecord.setEmpInformation(this);

		return allergyRecord;
	}

	public AllergyRecord removeAllergyRecord(AllergyRecord allergyRecord) {
		getAllergyRecords().remove(allergyRecord);
		allergyRecord.setEmpInformation(null);

		return allergyRecord;
	}

	public List<AllocationOutbound> getAllocationOutbounds() {
		return this.allocationOutbounds;
	}

	public void setAllocationOutbounds(List<AllocationOutbound> allocationOutbounds) {
		this.allocationOutbounds = allocationOutbounds;
	}

	public AllocationOutbound addAllocationOutbound(AllocationOutbound allocationOutbound) {
		getAllocationOutbounds().add(allocationOutbound);
		allocationOutbound.setEmpInformation(this);

		return allocationOutbound;
	}

	public AllocationOutbound removeAllocationOutbound(AllocationOutbound allocationOutbound) {
		getAllocationOutbounds().remove(allocationOutbound);
		allocationOutbound.setEmpInformation(null);

		return allocationOutbound;
	}

	public List<AmbulanceRecord> getAmbulanceRecords1() {
		return this.ambulanceRecords1;
	}

	public void setAmbulanceRecords1(List<AmbulanceRecord> ambulanceRecords1) {
		this.ambulanceRecords1 = ambulanceRecords1;
	}

	public AmbulanceRecord addAmbulanceRecords1(AmbulanceRecord ambulanceRecords1) {
		getAmbulanceRecords1().add(ambulanceRecords1);
		ambulanceRecords1.setEmpInformation1(this);

		return ambulanceRecords1;
	}

	public AmbulanceRecord removeAmbulanceRecords1(AmbulanceRecord ambulanceRecords1) {
		getAmbulanceRecords1().remove(ambulanceRecords1);
		ambulanceRecords1.setEmpInformation1(null);

		return ambulanceRecords1;
	}

	public List<AmbulanceRecord> getAmbulanceRecords2() {
		return this.ambulanceRecords2;
	}

	public void setAmbulanceRecords2(List<AmbulanceRecord> ambulanceRecords2) {
		this.ambulanceRecords2 = ambulanceRecords2;
	}

	public AmbulanceRecord addAmbulanceRecords2(AmbulanceRecord ambulanceRecords2) {
		getAmbulanceRecords2().add(ambulanceRecords2);
		ambulanceRecords2.setEmpInformation2(this);

		return ambulanceRecords2;
	}

	public AmbulanceRecord removeAmbulanceRecords2(AmbulanceRecord ambulanceRecords2) {
		getAmbulanceRecords2().remove(ambulanceRecords2);
		ambulanceRecords2.setEmpInformation2(null);

		return ambulanceRecords2;
	}

	public List<AskleaveRecord> getAskleaveRecords() {
		return this.askleaveRecords;
	}

	public void setAskleaveRecords(List<AskleaveRecord> askleaveRecords) {
		this.askleaveRecords = askleaveRecords;
	}

	public AskleaveRecord addAskleaveRecord(AskleaveRecord askleaveRecord) {
		getAskleaveRecords().add(askleaveRecord);
		askleaveRecord.setEmpInformation(this);

		return askleaveRecord;
	}

	public AskleaveRecord removeAskleaveRecord(AskleaveRecord askleaveRecord) {
		getAskleaveRecords().remove(askleaveRecord);
		askleaveRecord.setEmpInformation(null);

		return askleaveRecord;
	}

	public List<BackMedicine> getBackMedicines() {
		return this.backMedicines;
	}

	public void setBackMedicines(List<BackMedicine> backMedicines) {
		this.backMedicines = backMedicines;
	}

	public BackMedicine addBackMedicine(BackMedicine backMedicine) {
		getBackMedicines().add(backMedicine);
		backMedicine.setEmpInformation(this);

		return backMedicine;
	}

	public BackMedicine removeBackMedicine(BackMedicine backMedicine) {
		getBackMedicines().remove(backMedicine);
		backMedicine.setEmpInformation(null);

		return backMedicine;
	}

	public List<BedTransRecord> getBedTransRecords() {
		return this.bedTransRecords;
	}

	public void setBedTransRecords(List<BedTransRecord> bedTransRecords) {
		this.bedTransRecords = bedTransRecords;
	}

	public BedTransRecord addBedTransRecord(BedTransRecord bedTransRecord) {
		getBedTransRecords().add(bedTransRecord);
		bedTransRecord.setEmpInformation(this);

		return bedTransRecord;
	}

	public BedTransRecord removeBedTransRecord(BedTransRecord bedTransRecord) {
		getBedTransRecords().remove(bedTransRecord);
		bedTransRecord.setEmpInformation(null);

		return bedTransRecord;
	}

	public List<BusinessRecord> getBusinessRecords() {
		return this.businessRecords;
	}

	public void setBusinessRecords(List<BusinessRecord> businessRecords) {
		this.businessRecords = businessRecords;
	}

	public BusinessRecord addBusinessRecord(BusinessRecord businessRecord) {
		getBusinessRecords().add(businessRecord);
		businessRecord.setEmpInformation(this);

		return businessRecord;
	}

	public BusinessRecord removeBusinessRecord(BusinessRecord businessRecord) {
		getBusinessRecords().remove(businessRecord);
		businessRecord.setEmpInformation(null);

		return businessRecord;
	}

	public List<CheckPay> getCheckPays() {
		return this.checkPays;
	}

	public void setCheckPays(List<CheckPay> checkPays) {
		this.checkPays = checkPays;
	}

	public CheckPay addCheckPay(CheckPay checkPay) {
		getCheckPays().add(checkPay);
		checkPay.setEmpInformation(this);

		return checkPay;
	}

	public CheckPay removeCheckPay(CheckPay checkPay) {
		getCheckPays().remove(checkPay);
		checkPay.setEmpInformation(null);

		return checkPay;
	}

	public List<CheckPayRecord> getCheckPayRecords() {
		return this.checkPayRecords;
	}

	public void setCheckPayRecords(List<CheckPayRecord> checkPayRecords) {
		this.checkPayRecords = checkPayRecords;
	}

	public CheckPayRecord addCheckPayRecord(CheckPayRecord checkPayRecord) {
		getCheckPayRecords().add(checkPayRecord);
		checkPayRecord.setEmpInformation(this);

		return checkPayRecord;
	}

	public CheckPayRecord removeCheckPayRecord(CheckPayRecord checkPayRecord) {
		getCheckPayRecords().remove(checkPayRecord);
		checkPayRecord.setEmpInformation(null);

		return checkPayRecord;
	}

	public List<CheckResultForm> getCheckResultForms() {
		return this.checkResultForms;
	}

	public void setCheckResultForms(List<CheckResultForm> checkResultForms) {
		this.checkResultForms = checkResultForms;
	}

	public CheckResultForm addCheckResultForm(CheckResultForm checkResultForm) {
		getCheckResultForms().add(checkResultForm);
		checkResultForm.setEmpInformation(this);

		return checkResultForm;
	}

	public CheckResultForm removeCheckResultForm(CheckResultForm checkResultForm) {
		getCheckResultForms().remove(checkResultForm);
		checkResultForm.setEmpInformation(null);

		return checkResultForm;
	}

	public List<CheckWork> getCheckWorks() {
		return this.checkWorks;
	}

	public void setCheckWorks(List<CheckWork> checkWorks) {
		this.checkWorks = checkWorks;
	}

	public CheckWork addCheckWork(CheckWork checkWork) {
		getCheckWorks().add(checkWork);
		checkWork.setEmpInformation(this);

		return checkWork;
	}

	public CheckWork removeCheckWork(CheckWork checkWork) {
		getCheckWorks().remove(checkWork);
		checkWork.setEmpInformation(null);

		return checkWork;
	}

	public List<DamagedMedicine> getDamagedMedicines() {
		return this.damagedMedicines;
	}

	public void setDamagedMedicines(List<DamagedMedicine> damagedMedicines) {
		this.damagedMedicines = damagedMedicines;
	}

	public DamagedMedicine addDamagedMedicine(DamagedMedicine damagedMedicine) {
		getDamagedMedicines().add(damagedMedicine);
		damagedMedicine.setEmpInformation(this);

		return damagedMedicine;
	}

	public DamagedMedicine removeDamagedMedicine(DamagedMedicine damagedMedicine) {
		getDamagedMedicines().remove(damagedMedicine);
		damagedMedicine.setEmpInformation(null);

		return damagedMedicine;
	}

	public List<DrugScrap> getDrugScraps() {
		return this.drugScraps;
	}

	public void setDrugScraps(List<DrugScrap> drugScraps) {
		this.drugScraps = drugScraps;
	}

	public DrugScrap addDrugScrap(DrugScrap drugScrap) {
		getDrugScraps().add(drugScrap);
		drugScrap.setEmpInformation(this);

		return drugScrap;
	}

	public DrugScrap removeDrugScrap(DrugScrap drugScrap) {
		getDrugScraps().remove(drugScrap);
		drugScrap.setEmpInformation(null);

		return drugScrap;
	}

	public TechnicalPost getTechnicalPost() {
		return this.technicalPost;
	}

	public void setTechnicalPost(TechnicalPost technicalPost) {
		this.technicalPost = technicalPost;
	}

	public WaitingRoom getWaitingRoom() {
		return this.waitingRoom;
	}

	public void setWaitingRoom(WaitingRoom waitingRoom) {
		this.waitingRoom = waitingRoom;
	}

	public List<Examination> getExaminations() {
		return this.examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public Examination addExamination(Examination examination) {
		getExaminations().add(examination);
		examination.setEmpInformation(this);

		return examination;
	}

	public Examination removeExamination(Examination examination) {
		getExaminations().remove(examination);
		examination.setEmpInformation(null);

		return examination;
	}

	public List<HospitalizedPatient> getHospitalizedPatients1() {
		return this.hospitalizedPatients1;
	}

	public void setHospitalizedPatients1(List<HospitalizedPatient> hospitalizedPatients1) {
		this.hospitalizedPatients1 = hospitalizedPatients1;
	}

	public HospitalizedPatient addHospitalizedPatients1(HospitalizedPatient hospitalizedPatients1) {
		getHospitalizedPatients1().add(hospitalizedPatients1);
		hospitalizedPatients1.setEmpInformation1(this);

		return hospitalizedPatients1;
	}

	public HospitalizedPatient removeHospitalizedPatients1(HospitalizedPatient hospitalizedPatients1) {
		getHospitalizedPatients1().remove(hospitalizedPatients1);
		hospitalizedPatients1.setEmpInformation1(null);

		return hospitalizedPatients1;
	}

	public List<HospitalizedPatient> getHospitalizedPatients2() {
		return this.hospitalizedPatients2;
	}

	public void setHospitalizedPatients2(List<HospitalizedPatient> hospitalizedPatients2) {
		this.hospitalizedPatients2 = hospitalizedPatients2;
	}

	public HospitalizedPatient addHospitalizedPatients2(HospitalizedPatient hospitalizedPatients2) {
		getHospitalizedPatients2().add(hospitalizedPatients2);
		hospitalizedPatients2.setEmpInformation2(this);

		return hospitalizedPatients2;
	}

	public HospitalizedPatient removeHospitalizedPatients2(HospitalizedPatient hospitalizedPatients2) {
		getHospitalizedPatients2().remove(hospitalizedPatients2);
		hospitalizedPatients2.setEmpInformation2(null);

		return hospitalizedPatients2;
	}

	public List<HosDrugRecord> getHosDrugRecords() {
		return this.hosDrugRecords;
	}

	public void setHosDrugRecords(List<HosDrugRecord> hosDrugRecords) {
		this.hosDrugRecords = hosDrugRecords;
	}

	public HosDrugRecord addHosDrugRecord(HosDrugRecord hosDrugRecord) {
		getHosDrugRecords().add(hosDrugRecord);
		hosDrugRecord.setEmpInformation(this);

		return hosDrugRecord;
	}

	public HosDrugRecord removeHosDrugRecord(HosDrugRecord hosDrugRecord) {
		getHosDrugRecords().remove(hosDrugRecord);
		hosDrugRecord.setEmpInformation(null);

		return hosDrugRecord;
	}

	public List<HosPatientsApply> getHosPatientsApplies() {
		return this.hosPatientsApplies;
	}

	public void setHosPatientsApplies(List<HosPatientsApply> hosPatientsApplies) {
		this.hosPatientsApplies = hosPatientsApplies;
	}

	public HosPatientsApply addHosPatientsApply(HosPatientsApply hosPatientsApply) {
		getHosPatientsApplies().add(hosPatientsApply);
		hosPatientsApply.setEmpInformation(this);

		return hosPatientsApply;
	}

	public HosPatientsApply removeHosPatientsApply(HosPatientsApply hosPatientsApply) {
		getHosPatientsApplies().remove(hosPatientsApply);
		hosPatientsApply.setEmpInformation(null);

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
		hosPayRecord.setEmpInformation(this);

		return hosPayRecord;
	}

	public HosPayRecord removeHosPayRecord(HosPayRecord hosPayRecord) {
		getHosPayRecords().remove(hosPayRecord);
		hosPayRecord.setEmpInformation(null);

		return hosPayRecord;
	}

	public List<MedicinePay> getMedicinePays() {
		return this.medicinePays;
	}

	public void setMedicinePays(List<MedicinePay> medicinePays) {
		this.medicinePays = medicinePays;
	}

	public MedicinePay addMedicinePay(MedicinePay medicinePay) {
		getMedicinePays().add(medicinePay);
		medicinePay.setEmpInformation(this);

		return medicinePay;
	}

	public MedicinePay removeMedicinePay(MedicinePay medicinePay) {
		getMedicinePays().remove(medicinePay);
		medicinePay.setEmpInformation(null);

		return medicinePay;
	}

	public List<OperationPay> getOperationPays() {
		return this.operationPays;
	}

	public void setOperationPays(List<OperationPay> operationPays) {
		this.operationPays = operationPays;
	}

	public OperationPay addOperationPay(OperationPay operationPay) {
		getOperationPays().add(operationPay);
		operationPay.setEmpInformation(this);

		return operationPay;
	}

	public OperationPay removeOperationPay(OperationPay operationPay) {
		getOperationPays().remove(operationPay);
		operationPay.setEmpInformation(null);

		return operationPay;
	}

	public List<OperationRecord> getOperationRecords1() {
		return this.operationRecords1;
	}

	public void setOperationRecords1(List<OperationRecord> operationRecords1) {
		this.operationRecords1 = operationRecords1;
	}

	public OperationRecord addOperationRecords1(OperationRecord operationRecords1) {
		getOperationRecords1().add(operationRecords1);
		operationRecords1.setEmpInformation1(this);

		return operationRecords1;
	}

	public OperationRecord removeOperationRecords1(OperationRecord operationRecords1) {
		getOperationRecords1().remove(operationRecords1);
		operationRecords1.setEmpInformation1(null);

		return operationRecords1;
	}

	public List<OperationRecord> getOperationRecords2() {
		return this.operationRecords2;
	}

	public void setOperationRecords2(List<OperationRecord> operationRecords2) {
		this.operationRecords2 = operationRecords2;
	}

	public OperationRecord addOperationRecords2(OperationRecord operationRecords2) {
		getOperationRecords2().add(operationRecords2);
		operationRecords2.setEmpInformation2(this);

		return operationRecords2;
	}

	public OperationRecord removeOperationRecords2(OperationRecord operationRecords2) {
		getOperationRecords2().remove(operationRecords2);
		operationRecords2.setEmpInformation2(null);

		return operationRecords2;
	}

	public List<OperationRecord> getOperationRecords3() {
		return this.operationRecords3;
	}

	public void setOperationRecords3(List<OperationRecord> operationRecords3) {
		this.operationRecords3 = operationRecords3;
	}

	public OperationRecord addOperationRecords3(OperationRecord operationRecords3) {
		getOperationRecords3().add(operationRecords3);
		operationRecords3.setEmpInformation3(this);

		return operationRecords3;
	}

	public OperationRecord removeOperationRecords3(OperationRecord operationRecords3) {
		getOperationRecords3().remove(operationRecords3);
		operationRecords3.setEmpInformation3(null);

		return operationRecords3;
	}

	public List<OperationRecord> getOperationRecords4() {
		return this.operationRecords4;
	}

	public void setOperationRecords4(List<OperationRecord> operationRecords4) {
		this.operationRecords4 = operationRecords4;
	}

	public OperationRecord addOperationRecords4(OperationRecord operationRecords4) {
		getOperationRecords4().add(operationRecords4);
		operationRecords4.setEmpInformation4(this);

		return operationRecords4;
	}

	public OperationRecord removeOperationRecords4(OperationRecord operationRecords4) {
		getOperationRecords4().remove(operationRecords4);
		operationRecords4.setEmpInformation4(null);

		return operationRecords4;
	}

	public List<OperPayRecord> getOperPayRecords() {
		return this.operPayRecords;
	}

	public void setOperPayRecords(List<OperPayRecord> operPayRecords) {
		this.operPayRecords = operPayRecords;
	}

	public OperPayRecord addOperPayRecord(OperPayRecord operPayRecord) {
		getOperPayRecords().add(operPayRecord);
		operPayRecord.setEmpInformation(this);

		return operPayRecord;
	}

	public OperPayRecord removeOperPayRecord(OperPayRecord operPayRecord) {
		getOperPayRecords().remove(operPayRecord);
		operPayRecord.setEmpInformation(null);

		return operPayRecord;
	}

	public List<OutpatientPay> getOutpatientPays() {
		return this.outpatientPays;
	}

	public void setOutpatientPays(List<OutpatientPay> outpatientPays) {
		this.outpatientPays = outpatientPays;
	}

	public OutpatientPay addOutpatientPay(OutpatientPay outpatientPay) {
		getOutpatientPays().add(outpatientPay);
		outpatientPay.setEmpInformation(this);

		return outpatientPay;
	}

	public OutpatientPay removeOutpatientPay(OutpatientPay outpatientPay) {
		getOutpatientPays().remove(outpatientPay);
		outpatientPay.setEmpInformation(null);

		return outpatientPay;
	}

	public List<OutpatientRegistration> getOutpatientRegistrations1() {
		return this.outpatientRegistrations1;
	}

	public void setOutpatientRegistrations1(List<OutpatientRegistration> outpatientRegistrations1) {
		this.outpatientRegistrations1 = outpatientRegistrations1;
	}

	public OutpatientRegistration addOutpatientRegistrations1(OutpatientRegistration outpatientRegistrations1) {
		getOutpatientRegistrations1().add(outpatientRegistrations1);
		outpatientRegistrations1.setEmpInformation1(this);

		return outpatientRegistrations1;
	}

	public OutpatientRegistration removeOutpatientRegistrations1(OutpatientRegistration outpatientRegistrations1) {
		getOutpatientRegistrations1().remove(outpatientRegistrations1);
		outpatientRegistrations1.setEmpInformation1(null);

		return outpatientRegistrations1;
	}

	public List<OutpatientRegistration> getOutpatientRegistrations2() {
		return this.outpatientRegistrations2;
	}

	public void setOutpatientRegistrations2(List<OutpatientRegistration> outpatientRegistrations2) {
		this.outpatientRegistrations2 = outpatientRegistrations2;
	}

	public OutpatientRegistration addOutpatientRegistrations2(OutpatientRegistration outpatientRegistrations2) {
		getOutpatientRegistrations2().add(outpatientRegistrations2);
		outpatientRegistrations2.setEmpInformation2(this);

		return outpatientRegistrations2;
	}

	public OutpatientRegistration removeOutpatientRegistrations2(OutpatientRegistration outpatientRegistrations2) {
		getOutpatientRegistrations2().remove(outpatientRegistrations2);
		outpatientRegistrations2.setEmpInformation2(null);

		return outpatientRegistrations2;
	}

	public List<OutpatientRequestionMedicine> getOutpatientRequestionMedicines() {
		return this.outpatientRequestionMedicines;
	}

	public void setOutpatientRequestionMedicines(List<OutpatientRequestionMedicine> outpatientRequestionMedicines) {
		this.outpatientRequestionMedicines = outpatientRequestionMedicines;
	}

	public OutpatientRequestionMedicine addOutpatientRequestionMedicine(OutpatientRequestionMedicine outpatientRequestionMedicine) {
		getOutpatientRequestionMedicines().add(outpatientRequestionMedicine);
		outpatientRequestionMedicine.setEmpInformation(this);

		return outpatientRequestionMedicine;
	}

	public OutpatientRequestionMedicine removeOutpatientRequestionMedicine(OutpatientRequestionMedicine outpatientRequestionMedicine) {
		getOutpatientRequestionMedicines().remove(outpatientRequestionMedicine);
		outpatientRequestionMedicine.setEmpInformation(null);

		return outpatientRequestionMedicine;
	}

	public List<Outstock> getOutstocks() {
		return this.outstocks;
	}

	public void setOutstocks(List<Outstock> outstocks) {
		this.outstocks = outstocks;
	}

	public Outstock addOutstock(Outstock outstock) {
		getOutstocks().add(outstock);
		outstock.setEmpInformation(this);

		return outstock;
	}

	public Outstock removeOutstock(Outstock outstock) {
		getOutstocks().remove(outstock);
		outstock.setEmpInformation(null);

		return outstock;
	}

	public List<OutHospitaiRecord> getOutHospitaiRecords() {
		return this.outHospitaiRecords;
	}

	public void setOutHospitaiRecords(List<OutHospitaiRecord> outHospitaiRecords) {
		this.outHospitaiRecords = outHospitaiRecords;
	}

	public OutHospitaiRecord addOutHospitaiRecord(OutHospitaiRecord outHospitaiRecord) {
		getOutHospitaiRecords().add(outHospitaiRecord);
		outHospitaiRecord.setEmpInformation(this);

		return outHospitaiRecord;
	}

	public OutHospitaiRecord removeOutHospitaiRecord(OutHospitaiRecord outHospitaiRecord) {
		getOutHospitaiRecords().remove(outHospitaiRecord);
		outHospitaiRecord.setEmpInformation(null);

		return outHospitaiRecord;
	}

	public List<OutPrePay> getOutPrePays() {
		return this.outPrePays;
	}

	public void setOutPrePays(List<OutPrePay> outPrePays) {
		this.outPrePays = outPrePays;
	}

	public OutPrePay addOutPrePay(OutPrePay outPrePay) {
		getOutPrePays().add(outPrePay);
		outPrePay.setEmpInformation(this);

		return outPrePay;
	}

	public OutPrePay removeOutPrePay(OutPrePay outPrePay) {
		getOutPrePays().remove(outPrePay);
		outPrePay.setEmpInformation(null);

		return outPrePay;
	}

	public List<PhaIn> getPhaIns() {
		return this.phaIns;
	}

	public void setPhaIns(List<PhaIn> phaIns) {
		this.phaIns = phaIns;
	}

	public PhaIn addPhaIn(PhaIn phaIn) {
		getPhaIns().add(phaIn);
		phaIn.setEmpInformation(this);

		return phaIn;
	}

	public PhaIn removePhaIn(PhaIn phaIn) {
		getPhaIns().remove(phaIn);
		phaIn.setEmpInformation(null);

		return phaIn;
	}

	public List<PsInventoryTaking> getPsInventoryTakings() {
		return this.psInventoryTakings;
	}

	public void setPsInventoryTakings(List<PsInventoryTaking> psInventoryTakings) {
		this.psInventoryTakings = psInventoryTakings;
	}

	public PsInventoryTaking addPsInventoryTaking(PsInventoryTaking psInventoryTaking) {
		getPsInventoryTakings().add(psInventoryTaking);
		psInventoryTaking.setEmpInformation(this);

		return psInventoryTaking;
	}

	public PsInventoryTaking removePsInventoryTaking(PsInventoryTaking psInventoryTaking) {
		getPsInventoryTakings().remove(psInventoryTaking);
		psInventoryTaking.setEmpInformation(null);

		return psInventoryTaking;
	}

	public List<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Purchase addPurchas(Purchase purchas) {
		getPurchases().add(purchas);
		purchas.setEmpInformation(this);

		return purchas;
	}

	public Purchase removePurchas(Purchase purchas) {
		getPurchases().remove(purchas);
		purchas.setEmpInformation(null);

		return purchas;
	}

	public List<PurchaseCheck> getPurchaseChecks() {
		return this.purchaseChecks;
	}

	public void setPurchaseChecks(List<PurchaseCheck> purchaseChecks) {
		this.purchaseChecks = purchaseChecks;
	}

	public PurchaseCheck addPurchaseCheck(PurchaseCheck purchaseCheck) {
		getPurchaseChecks().add(purchaseCheck);
		purchaseCheck.setEmpInformation(this);

		return purchaseCheck;
	}

	public PurchaseCheck removePurchaseCheck(PurchaseCheck purchaseCheck) {
		getPurchaseChecks().remove(purchaseCheck);
		purchaseCheck.setEmpInformation(null);

		return purchaseCheck;
	}

	public List<PutstockBack> getPutstockBacks() {
		return this.putstockBacks;
	}

	public void setPutstockBacks(List<PutstockBack> putstockBacks) {
		this.putstockBacks = putstockBacks;
	}

	public PutstockBack addPutstockBack(PutstockBack putstockBack) {
		getPutstockBacks().add(putstockBack);
		putstockBack.setEmpInformation(this);

		return putstockBack;
	}

	public PutstockBack removePutstockBack(PutstockBack putstockBack) {
		getPutstockBacks().remove(putstockBack);
		putstockBack.setEmpInformation(null);

		return putstockBack;
	}

	public List<PutStock> getPutStocks() {
		return this.putStocks;
	}

	public void setPutStocks(List<PutStock> putStocks) {
		this.putStocks = putStocks;
	}

	public PutStock addPutStock(PutStock putStock) {
		getPutStocks().add(putStock);
		putStock.setEmpInformation(this);

		return putStock;
	}

	public PutStock removePutStock(PutStock putStock) {
		getPutStocks().remove(putStock);
		putStock.setEmpInformation(null);

		return putStock;
	}

	public List<Transfer> getTransfers() {
		return this.transfers;
	}

	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}

	public Transfer addTransfer(Transfer transfer) {
		getTransfers().add(transfer);
		transfer.setEmpInformation(this);

		return transfer;
	}

	public Transfer removeTransfer(Transfer transfer) {
		getTransfers().remove(transfer);
		transfer.setEmpInformation(null);

		return transfer;
	}

	public List<TransOfficeRecord> getTransOfficeRecords() {
		return this.transOfficeRecords;
	}

	public void setTransOfficeRecords(List<TransOfficeRecord> transOfficeRecords) {
		this.transOfficeRecords = transOfficeRecords;
	}

	public TransOfficeRecord addTransOfficeRecord(TransOfficeRecord transOfficeRecord) {
		getTransOfficeRecords().add(transOfficeRecord);
		transOfficeRecord.setEmpInformation(this);

		return transOfficeRecord;
	}

	public TransOfficeRecord removeTransOfficeRecord(TransOfficeRecord transOfficeRecord) {
		getTransOfficeRecords().remove(transOfficeRecord);
		transOfficeRecord.setEmpInformation(null);

		return transOfficeRecord;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setEmpInformation(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setEmpInformation(null);

		return userRole;
	}

	public List<UseDrugRecord> getUseDrugRecords() {
		return this.useDrugRecords;
	}

	public void setUseDrugRecords(List<UseDrugRecord> useDrugRecords) {
		this.useDrugRecords = useDrugRecords;
	}

	public UseDrugRecord addUseDrugRecord(UseDrugRecord useDrugRecord) {
		getUseDrugRecords().add(useDrugRecord);
		useDrugRecord.setEmpInformation(this);

		return useDrugRecord;
	}

	public UseDrugRecord removeUseDrugRecord(UseDrugRecord useDrugRecord) {
		getUseDrugRecords().remove(useDrugRecord);
		useDrugRecord.setEmpInformation(null);

		return useDrugRecord;
	}

	public List<WaitingRoom> getWaitingRooms() {
		return this.waitingRooms;
	}

	public void setWaitingRooms(List<WaitingRoom> waitingRooms) {
		this.waitingRooms = waitingRooms;
	}

	public WaitingRoom addWaitingRoom(WaitingRoom waitingRoom) {
		getWaitingRooms().add(waitingRoom);
		waitingRoom.setEmpInformation(this);

		return waitingRoom;
	}

	public WaitingRoom removeWaitingRoom(WaitingRoom waitingRoom) {
		getWaitingRooms().remove(waitingRoom);
		waitingRoom.setEmpInformation(null);

		return waitingRoom;
	}

	public List<WorkoverRecord> getWorkoverRecords() {
		return this.workoverRecords;
	}

	public void setWorkoverRecords(List<WorkoverRecord> workoverRecords) {
		this.workoverRecords = workoverRecords;
	}

	public WorkoverRecord addWorkoverRecord(WorkoverRecord workoverRecord) {
		getWorkoverRecords().add(workoverRecord);
		workoverRecord.setEmpInformation(this);

		return workoverRecord;
	}

	public WorkoverRecord removeWorkoverRecord(WorkoverRecord workoverRecord) {
		getWorkoverRecords().remove(workoverRecord);
		workoverRecord.setEmpInformation(null);

		return workoverRecord;
	}

	public List<WorkTime> getWorkTimes1() {
		return this.workTimes1;
	}

	public void setWorkTimes1(List<WorkTime> workTimes1) {
		this.workTimes1 = workTimes1;
	}

	public WorkTime addWorkTimes1(WorkTime workTimes1) {
		getWorkTimes1().add(workTimes1);
		workTimes1.setEmpInformation1(this);

		return workTimes1;
	}

	public WorkTime removeWorkTimes1(WorkTime workTimes1) {
		getWorkTimes1().remove(workTimes1);
		workTimes1.setEmpInformation1(null);

		return workTimes1;
	}

	public List<WorkTime> getWorkTimes2() {
		return this.workTimes2;
	}

	public void setWorkTimes2(List<WorkTime> workTimes2) {
		this.workTimes2 = workTimes2;
	}

	public WorkTime addWorkTimes2(WorkTime workTimes2) {
		getWorkTimes2().add(workTimes2);
		workTimes2.setEmpInformation2(this);

		return workTimes2;
	}

	public WorkTime removeWorkTimes2(WorkTime workTimes2) {
		getWorkTimes2().remove(workTimes2);
		workTimes2.setEmpInformation2(null);

		return workTimes2;
	}

}