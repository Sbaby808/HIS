package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MEDICAL_CARD database table.
 * 
 */
@Entity
@Table(name="MEDICAL_CARD")
@NamedQuery(name="MedicalCard.findAll", query="SELECT m FROM MedicalCard m")
public class MedicalCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CARD_ID")
	private String cardId;

	private BigDecimal age;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name="CARD_NAME")
	private String cardName;

	@Column(name="CARD_NUM")
	private BigDecimal cardNum;

	private String country;

	private String gender;

	@Column(name="LINK_ADDRESS")
	private String linkAddress;

	@Column(name="LINK_PERSON")
	private String linkPerson;

	@Column(name="LINK_PHONE")
	private String linkPhone;

	@Column(name="LINK_RELATION")
	private String linkRelation;

	private String married;
	
	private String tel;

	private String passwd;

	@Column(name="PERSON_ID")
	private String personId;

	@Column(name="POSTAL_CODE")
	private String postalCode;

	private String profession;

	@Column(name="WORK_UNIT")
	private String workUnit;


	//bi-directional many-to-one association to AllergyRecord
	@OneToMany(mappedBy="medicalCard")
	@JSONField(serialize=false)
	private List<AllergyRecord> allergyRecords;

	//bi-directional many-to-one association to CheckPayRecord
	@OneToMany(mappedBy="medicalCard")
	@JSONField(serialize=false)
	private List<CheckPayRecord> checkPayRecords;

	//bi-directional many-to-one association to HospitalizedPatient
	@OneToMany(mappedBy="medicalCard")
	@JSONField(serialize=false)
	private List<HospitalizedPatient> hospitalizedPatients;

	//bi-directional many-to-one association to OperPayRecord
	@OneToMany(mappedBy="medicalCard")
	@JSONField(serialize=false)
	private List<OperPayRecord> operPayRecords;

	//bi-directional many-to-one association to OutpatientRegistration
	@OneToMany(mappedBy="medicalCard")
	@JSONField(serialize=false)
	private List<OutpatientRegistration> outpatientRegistrations;

	//bi-directional many-to-one association to RemainRecord
	@OneToMany(mappedBy="medicalCard")
	@JSONField(serialize=false)
	private List<RemainRecord> remainRecords;

	public MedicalCard() {
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public BigDecimal getAge() {
		return this.age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public BigDecimal getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(BigDecimal cardNum) {
		this.cardNum = cardNum;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLinkAddress() {
		return this.linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getLinkPerson() {
		return this.linkPerson;
	}

	public void setLinkPerson(String linkPerson) {
		this.linkPerson = linkPerson;
	}

	public String getLinkPhone() {
		return this.linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getLinkRelation() {
		return this.linkRelation;
	}

	public void setLinkRelation(String linkRelation) {
		this.linkRelation = linkRelation;
	}

	public String getMarried() {
		return this.married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getWorkUnit() {
		return this.workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}


	public List<AllergyRecord> getAllergyRecords() {
		return this.allergyRecords;
	}

	public void setAllergyRecords(List<AllergyRecord> allergyRecords) {
		this.allergyRecords = allergyRecords;
	}

	public AllergyRecord addAllergyRecord(AllergyRecord allergyRecord) {
		getAllergyRecords().add(allergyRecord);
		allergyRecord.setMedicalCard(this);

		return allergyRecord;
	}

	public AllergyRecord removeAllergyRecord(AllergyRecord allergyRecord) {
		getAllergyRecords().remove(allergyRecord);
		allergyRecord.setMedicalCard(null);

		return allergyRecord;
	}

	public List<CheckPayRecord> getCheckPayRecords() {
		return this.checkPayRecords;
	}

	public void setCheckPayRecords(List<CheckPayRecord> checkPayRecords) {
		this.checkPayRecords = checkPayRecords;
	}

	public CheckPayRecord addCheckPayRecord(CheckPayRecord checkPayRecord) {
		getCheckPayRecords().add(checkPayRecord);
		checkPayRecord.setMedicalCard(this);

		return checkPayRecord;
	}

	public CheckPayRecord removeCheckPayRecord(CheckPayRecord checkPayRecord) {
		getCheckPayRecords().remove(checkPayRecord);
		checkPayRecord.setMedicalCard(null);

		return checkPayRecord;
	}

	public List<HospitalizedPatient> getHospitalizedPatients() {
		return this.hospitalizedPatients;
	}

	public void setHospitalizedPatients(List<HospitalizedPatient> hospitalizedPatients) {
		this.hospitalizedPatients = hospitalizedPatients;
	}

	public HospitalizedPatient addHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().add(hospitalizedPatient);
		hospitalizedPatient.setMedicalCard(this);

		return hospitalizedPatient;
	}

	public HospitalizedPatient removeHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().remove(hospitalizedPatient);
		hospitalizedPatient.setMedicalCard(null);

		return hospitalizedPatient;
	}

	public List<OperPayRecord> getOperPayRecords() {
		return this.operPayRecords;
	}

	public void setOperPayRecords(List<OperPayRecord> operPayRecords) {
		this.operPayRecords = operPayRecords;
	}

	public OperPayRecord addOperPayRecord(OperPayRecord operPayRecord) {
		getOperPayRecords().add(operPayRecord);
		operPayRecord.setMedicalCard(this);

		return operPayRecord;
	}

	public OperPayRecord removeOperPayRecord(OperPayRecord operPayRecord) {
		getOperPayRecords().remove(operPayRecord);
		operPayRecord.setMedicalCard(null);

		return operPayRecord;
	}

	public List<OutpatientRegistration> getOutpatientRegistrations() {
		return this.outpatientRegistrations;
	}

	public void setOutpatientRegistrations(List<OutpatientRegistration> outpatientRegistrations) {
		this.outpatientRegistrations = outpatientRegistrations;
	}

	public OutpatientRegistration addOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().add(outpatientRegistration);
		outpatientRegistration.setMedicalCard(this);

		return outpatientRegistration;
	}

	public OutpatientRegistration removeOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().remove(outpatientRegistration);
		outpatientRegistration.setMedicalCard(null);

		return outpatientRegistration;
	}

	public List<RemainRecord> getRemainRecords() {
		return this.remainRecords;
	}

	public void setRemainRecords(List<RemainRecord> remainRecords) {
		this.remainRecords = remainRecords;
	}

	public RemainRecord addRemainRecord(RemainRecord remainRecord) {
		getRemainRecords().add(remainRecord);
		remainRecord.setMedicalCard(this);

		return remainRecord;
	}

	public RemainRecord removeRemainRecord(RemainRecord remainRecord) {
		getRemainRecords().remove(remainRecord);
		remainRecord.setMedicalCard(null);

		return remainRecord;
	}

}