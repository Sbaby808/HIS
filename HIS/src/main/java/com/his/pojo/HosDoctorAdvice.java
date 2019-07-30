package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the HOS_DOCTOR_ADVICE database table.
 * 
 */
@Entity
@Table(name="HOS_DOCTOR_ADVICE")
@NamedQuery(name="HosDoctorAdvice.findAll", query="SELECT h FROM HosDoctorAdvice h")
public class HosDoctorAdvice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_DCO_ID")
	private String hosDcoId;

	@Column(name="HOS_CONTENT")
	private String hosContent;

	@Temporal(TemporalType.DATE)
	@Column(name="HOS_DO_TIME")
	private Date hosDoTime;

	@Temporal(TemporalType.DATE)
	@Column(name="HOS_END_TIME")
	private Date hosEndTime;

	@Temporal(TemporalType.DATE)
	@Column(name="HOS_START_TIME")
	private Date hosStartTime;

	//bi-directional many-to-one association to HosCheckNotice
	@OneToMany(mappedBy="hosDoctorAdvice")
	@JSONField(serialize=false)
	private List<HosCheckNotice> hosCheckNotices;

	//bi-directional many-to-one association to HosDiagnosticRecord
	@OneToMany(mappedBy="hosDoctorAdvice")
	@JSONField(serialize=false)
	private List<HosDiagnosticRecord> hosDiagnosticRecords;

	//bi-directional many-to-one association to HosDiagnosticRecord
	@ManyToOne
	@JoinColumn(name="HOS_DIAG_ID")
	private HosDiagnosticRecord hosDiagnosticRecord;

	//bi-directional many-to-one association to HosOutNotice
	@OneToMany(mappedBy="hosDoctorAdvice")
	@JSONField(serialize=false)
	private List<HosOutNotice> hosOutNotices;

	//bi-directional many-to-one association to HosSurgeryNotice
	@OneToMany(mappedBy="hosDoctorAdvice")
	@JSONField(serialize=false)
	private List<HosSurgeryNotice> hosSurgeryNotices;

	public HosDoctorAdvice() {
	}

	public String getHosDcoId() {
		return this.hosDcoId;
	}

	public void setHosDcoId(String hosDcoId) {
		this.hosDcoId = hosDcoId;
	}

	public String getHosContent() {
		return this.hosContent;
	}

	public void setHosContent(String hosContent) {
		this.hosContent = hosContent;
	}

	public Date getHosDoTime() {
		return this.hosDoTime;
	}

	public void setHosDoTime(Date hosDoTime) {
		this.hosDoTime = hosDoTime;
	}

	public Date getHosEndTime() {
		return this.hosEndTime;
	}

	public void setHosEndTime(Date hosEndTime) {
		this.hosEndTime = hosEndTime;
	}

	public Date getHosStartTime() {
		return this.hosStartTime;
	}

	public void setHosStartTime(Date hosStartTime) {
		this.hosStartTime = hosStartTime;
	}

	public List<HosCheckNotice> getHosCheckNotices() {
		return this.hosCheckNotices;
	}

	public void setHosCheckNotices(List<HosCheckNotice> hosCheckNotices) {
		this.hosCheckNotices = hosCheckNotices;
	}

	public HosCheckNotice addHosCheckNotice(HosCheckNotice hosCheckNotice) {
		getHosCheckNotices().add(hosCheckNotice);
		hosCheckNotice.setHosDoctorAdvice(this);

		return hosCheckNotice;
	}

	public HosCheckNotice removeHosCheckNotice(HosCheckNotice hosCheckNotice) {
		getHosCheckNotices().remove(hosCheckNotice);
		hosCheckNotice.setHosDoctorAdvice(null);

		return hosCheckNotice;
	}

	public List<HosDiagnosticRecord> getHosDiagnosticRecords() {
		return this.hosDiagnosticRecords;
	}

	public void setHosDiagnosticRecords(List<HosDiagnosticRecord> hosDiagnosticRecords) {
		this.hosDiagnosticRecords = hosDiagnosticRecords;
	}

	public HosDiagnosticRecord addHosDiagnosticRecord(HosDiagnosticRecord hosDiagnosticRecord) {
		getHosDiagnosticRecords().add(hosDiagnosticRecord);
		hosDiagnosticRecord.setHosDoctorAdvice(this);

		return hosDiagnosticRecord;
	}

	public HosDiagnosticRecord removeHosDiagnosticRecord(HosDiagnosticRecord hosDiagnosticRecord) {
		getHosDiagnosticRecords().remove(hosDiagnosticRecord);
		hosDiagnosticRecord.setHosDoctorAdvice(null);

		return hosDiagnosticRecord;
	}

	public HosDiagnosticRecord getHosDiagnosticRecord() {
		return this.hosDiagnosticRecord;
	}

	public void setHosDiagnosticRecord(HosDiagnosticRecord hosDiagnosticRecord) {
		this.hosDiagnosticRecord = hosDiagnosticRecord;
	}

	public List<HosOutNotice> getHosOutNotices() {
		return this.hosOutNotices;
	}

	public void setHosOutNotices(List<HosOutNotice> hosOutNotices) {
		this.hosOutNotices = hosOutNotices;
	}

	public HosOutNotice addHosOutNotice(HosOutNotice hosOutNotice) {
		getHosOutNotices().add(hosOutNotice);
		hosOutNotice.setHosDoctorAdvice(this);

		return hosOutNotice;
	}

	public HosOutNotice removeHosOutNotice(HosOutNotice hosOutNotice) {
		getHosOutNotices().remove(hosOutNotice);
		hosOutNotice.setHosDoctorAdvice(null);

		return hosOutNotice;
	}

	public List<HosSurgeryNotice> getHosSurgeryNotices() {
		return this.hosSurgeryNotices;
	}

	public void setHosSurgeryNotices(List<HosSurgeryNotice> hosSurgeryNotices) {
		this.hosSurgeryNotices = hosSurgeryNotices;
	}

	public HosSurgeryNotice addHosSurgeryNotice(HosSurgeryNotice hosSurgeryNotice) {
		getHosSurgeryNotices().add(hosSurgeryNotice);
		hosSurgeryNotice.setHosDoctorAdvice(this);

		return hosSurgeryNotice;
	}

	public HosSurgeryNotice removeHosSurgeryNotice(HosSurgeryNotice hosSurgeryNotice) {
		getHosSurgeryNotices().remove(hosSurgeryNotice);
		hosSurgeryNotice.setHosDoctorAdvice(null);

		return hosSurgeryNotice;
	}

}