package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


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
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name="HOS_DIAG_ID")
	private String hosDiagId;

	@Column(name="HOS_DCO_ID")
	private String hosDcoId;

	@Column(name="HOS_DIAG_DESC")
	private String hosDiagDesc;

	@Column(name="HOS_DIAG_RESULT")
	private String hosDiagResult;

	@Temporal(TemporalType.TIME)
	@JSONField(format="yyyy-MM-dd")
	@Column(name="HOS_DIAG_TIME")
	private Date hosDiagTime;

	@Column(name="HOS_PRE_ID")
	private String hosPreId;

	//bi-directional many-to-one association to MedicalRecord
	@ManyToOne
	@JoinColumn(name="MED_RID")
	private MedicalRecord medicalRecord;

	//bi-directional one-to-one association to HosDoctorAdvice
	@OneToOne(mappedBy="hosDiagnosticRecord")
	@JSONField(serialize=false)
	private HosDoctorAdvice hosDoctorAdvice;

	//bi-directional one-to-one association to HosPrescription
	@OneToOne(mappedBy="hosDiagnosticRecord")
	@JSONField(serialize=false)
	private HosPrescription hosPrescription;

	public HosDiagnosticRecord() {
	}

	public String getHosDiagId() {
		return this.hosDiagId;
	}

	public void setHosDiagId(String hosDiagId) {
		this.hosDiagId = hosDiagId;
	}

	public String getHosDcoId() {
		return this.hosDcoId;
	}

	public void setHosDcoId(String hosDcoId) {
		this.hosDcoId = hosDcoId;
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

	public String getHosPreId() {
		return this.hosPreId;
	}

	public void setHosPreId(String hosPreId) {
		this.hosPreId = hosPreId;
	}

	public MedicalRecord getMedicalRecord() {
		return this.medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
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

}