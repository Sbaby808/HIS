package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the HOS_DIAGNOSTIC_RECORD database table.
 * 
 */
@Entity
@Table(name="HOS_DIAGNOSTIC_RECORD")
@NamedQuery(name="HosDiagnosticRecord.findAll", query="SELECT h FROM HosDiagnosticRecord h")
public class HosDiagnosticRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_DIAG_ID")
	private String hosDiagId;

	@Column(name="HOS_DIAG_DESC")
	private String hosDiagDesc;

	@Column(name="HOS_DIAG_RESULT")
	private String hosDiagResult;

	@Temporal(TemporalType.DATE)
	@Column(name="HOS_DIAG_TIME")
	private Date hosDiagTime;

	//bi-directional many-to-one association to HosDoctorAdvice
	@ManyToOne
	@JoinColumn(name="HOS_DCO_ID")
	private HosDoctorAdvice hosDoctorAdvice;

	//bi-directional many-to-one association to HosPrescription
	@ManyToOne
	@JoinColumn(name="HOS_PRE_ID")
	private HosPrescription hosPrescription;

	//bi-directional many-to-one association to MedicalRecord
	@ManyToOne
	@JoinColumn(name="MED_RID")
	private MedicalRecord medicalRecord;

	//bi-directional many-to-one association to HosDoctorAdvice
	@OneToMany(mappedBy="hosDiagnosticRecord")
	private List<HosDoctorAdvice> hosDoctorAdvices;

	//bi-directional many-to-one association to HosPrescription
	@OneToMany(mappedBy="hosDiagnosticRecord")
	private List<HosPrescription> hosPrescriptions;

	public HosDiagnosticRecord() {
	}

	public String getHosDiagId() {
		return this.hosDiagId;
	}

	public void setHosDiagId(String hosDiagId) {
		this.hosDiagId = hosDiagId;
	}

	public String getHosDiagDesc() {
		return this.hosDiagDesc;
	}

	public void setHosDiagDesc(String hosDiagDesc) {
		this.hosDiagDesc = hosDiagDesc;
	}

	public String getHosDiagResult() {
		return this.hosDiagResult;
	}

	public void setHosDiagResult(String hosDiagResult) {
		this.hosDiagResult = hosDiagResult;
	}

	public Date getHosDiagTime() {
		return this.hosDiagTime;
	}

	public void setHosDiagTime(Date hosDiagTime) {
		this.hosDiagTime = hosDiagTime;
	}

	public HosDoctorAdvice getHosDoctorAdvice() {
		return this.hosDoctorAdvice;
	}

	public void setHosDoctorAdvice(HosDoctorAdvice hosDoctorAdvice) {
		this.hosDoctorAdvice = hosDoctorAdvice;
	}

	public HosPrescription getHosPrescription() {
		return this.hosPrescription;
	}

	public void setHosPrescription(HosPrescription hosPrescription) {
		this.hosPrescription = hosPrescription;
	}

	public MedicalRecord getMedicalRecord() {
		return this.medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public List<HosDoctorAdvice> getHosDoctorAdvices() {
		return this.hosDoctorAdvices;
	}

	public void setHosDoctorAdvices(List<HosDoctorAdvice> hosDoctorAdvices) {
		this.hosDoctorAdvices = hosDoctorAdvices;
	}

	public HosDoctorAdvice addHosDoctorAdvice(HosDoctorAdvice hosDoctorAdvice) {
		getHosDoctorAdvices().add(hosDoctorAdvice);
		hosDoctorAdvice.setHosDiagnosticRecord(this);

		return hosDoctorAdvice;
	}

	public HosDoctorAdvice removeHosDoctorAdvice(HosDoctorAdvice hosDoctorAdvice) {
		getHosDoctorAdvices().remove(hosDoctorAdvice);
		hosDoctorAdvice.setHosDiagnosticRecord(null);

		return hosDoctorAdvice;
	}

	public List<HosPrescription> getHosPrescriptions() {
		return this.hosPrescriptions;
	}

	public void setHosPrescriptions(List<HosPrescription> hosPrescriptions) {
		this.hosPrescriptions = hosPrescriptions;
	}

	public HosPrescription addHosPrescription(HosPrescription hosPrescription) {
		getHosPrescriptions().add(hosPrescription);
		hosPrescription.setHosDiagnosticRecord(this);

		return hosPrescription;
	}

	public HosPrescription removeHosPrescription(HosPrescription hosPrescription) {
		getHosPrescriptions().remove(hosPrescription);
		hosPrescription.setHosDiagnosticRecord(null);

		return hosPrescription;
	}

}