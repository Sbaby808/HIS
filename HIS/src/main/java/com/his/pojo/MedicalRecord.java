package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MEDICAL_RECORD database table.
 * 
 */
@Entity
@Table(name="MEDICAL_RECORD")
@NamedQuery(name="MedicalRecord.findAll", query="SELECT m FROM MedicalRecord m")
public class MedicalRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name="MED_RID")
	private String medRid;

	@Column(name="MED_IN_DEPT")
	private String medInDept;

	@Column(name="MED_IN_DIAGNOSE")
	private String medInDiagnose;

	@Column(name="MED_IN_SITUATION")
	private String medInSituation;

	@Temporal(TemporalType.DATE)
	@Column(name="MED_IN_TIME")
	private Date medInTime;

	@Column(name="MED_OTHER")
	private String medOther;

	@Column(name="MED_OUT_DEPT")
	private String medOutDept;

	@Column(name="MED_OUT_DIAGNOSE")
	private String medOutDiagnose;

	@Column(name="MED_OUT_SITUATION")
	private String medOutSituation;

	@Temporal(TemporalType.DATE)
	@Column(name="MED_OUT_TIME")
	private Date medOutTime;

	//bi-directional many-to-one association to HosDiagnosticRecord
	@OneToMany(mappedBy="medicalRecord")
	@JSONField(serialize=false)
	private List<HosDiagnosticRecord> hosDiagnosticRecords;

	//bi-directional many-to-one association to HosOtherCost
	@OneToMany(mappedBy="medicalRecord")
	@JSONField(serialize=false)
	private List<HosOtherCost> hosOtherCosts;

	//bi-directional one-to-one association to HospitalizedPatient
	@OneToOne
	@JSONField(serialize=false)
	@JoinColumn(name="HOSP_ID")
	private HospitalizedPatient hospitalizedPatient;

	public MedicalRecord() {
	}

	public String getMedRid() {
		return this.medRid;
	}

	public void setMedRid(String medRid) {
		this.medRid = medRid;
	}

	public String getMedInDept() {
		return this.medInDept;
	}

	public void setMedInDept(String medInDept) {
		this.medInDept = medInDept;
	}

	public String getMedInDiagnose() {
		return this.medInDiagnose;
	}

	public void setMedInDiagnose(String medInDiagnose) {
		this.medInDiagnose = medInDiagnose;
	}

	public String getMedInSituation() {
		return this.medInSituation;
	}

	public void setMedInSituation(String medInSituation) {
		this.medInSituation = medInSituation;
	}

	public Date getMedInTime() {
		return this.medInTime;
	}

	public void setMedInTime(Date medInTime) {
		this.medInTime = medInTime;
	}

	public String getMedOther() {
		return this.medOther;
	}

	public void setMedOther(String medOther) {
		this.medOther = medOther;
	}

	public String getMedOutDept() {
		return this.medOutDept;
	}

	public void setMedOutDept(String medOutDept) {
		this.medOutDept = medOutDept;
	}

	public String getMedOutDiagnose() {
		return this.medOutDiagnose;
	}

	public void setMedOutDiagnose(String medOutDiagnose) {
		this.medOutDiagnose = medOutDiagnose;
	}

	public String getMedOutSituation() {
		return this.medOutSituation;
	}

	public void setMedOutSituation(String medOutSituation) {
		this.medOutSituation = medOutSituation;
	}

	public Date getMedOutTime() {
		return this.medOutTime;
	}

	public void setMedOutTime(Date medOutTime) {
		this.medOutTime = medOutTime;
	}

	public List<HosDiagnosticRecord> getHosDiagnosticRecords() {
		return this.hosDiagnosticRecords;
	}

	public void setHosDiagnosticRecords(List<HosDiagnosticRecord> hosDiagnosticRecords) {
		this.hosDiagnosticRecords = hosDiagnosticRecords;
	}

	public HosDiagnosticRecord addHosDiagnosticRecord(HosDiagnosticRecord hosDiagnosticRecord) {
		getHosDiagnosticRecords().add(hosDiagnosticRecord);
		hosDiagnosticRecord.setMedicalRecord(this);

		return hosDiagnosticRecord;
	}

	public HosDiagnosticRecord removeHosDiagnosticRecord(HosDiagnosticRecord hosDiagnosticRecord) {
		getHosDiagnosticRecords().remove(hosDiagnosticRecord);
		hosDiagnosticRecord.setMedicalRecord(null);

		return hosDiagnosticRecord;
	}

	public List<HosOtherCost> getHosOtherCosts() {
		return this.hosOtherCosts;
	}

	public void setHosOtherCosts(List<HosOtherCost> hosOtherCosts) {
		this.hosOtherCosts = hosOtherCosts;
	}

	public HosOtherCost addHosOtherCost(HosOtherCost hosOtherCost) {
		getHosOtherCosts().add(hosOtherCost);
		hosOtherCost.setMedicalRecord(this);

		return hosOtherCost;
	}

	public HosOtherCost removeHosOtherCost(HosOtherCost hosOtherCost) {
		getHosOtherCosts().remove(hosOtherCost);
		hosOtherCost.setMedicalRecord(null);

		return hosOtherCost;
	}

	public HospitalizedPatient getHospitalizedPatient() {
		return this.hospitalizedPatient;
	}

	public void setHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		this.hospitalizedPatient = hospitalizedPatient;
	}

}