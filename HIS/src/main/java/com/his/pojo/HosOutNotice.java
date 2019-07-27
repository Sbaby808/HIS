package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HOS_OUT_NOTICE database table.
 * 
 */
@Entity
@Table(name="HOS_OUT_NOTICE")
@NamedQuery(name="HosOutNotice.findAll", query="SELECT h FROM HosOutNotice h")
public class HosOutNotice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_OUT_ID")
	private String hosOutId;

	@Column(name="HOS_OUT_NOTE")
	private String hosOutNote;

	@Temporal(TemporalType.DATE)
	@Column(name="HOS_OUT_TIME")
	private Date hosOutTime;

	//bi-directional many-to-one association to HosDoctorAdvice
	@ManyToOne
	@JoinColumn(name="HOS_DCO_ID")
	private HosDoctorAdvice hosDoctorAdvice;

	public HosOutNotice() {
	}

	public String getHosOutId() {
		return this.hosOutId;
	}

	public void setHosOutId(String hosOutId) {
		this.hosOutId = hosOutId;
	}

	public String getHosOutNote() {
		return this.hosOutNote;
	}

	public void setHosOutNote(String hosOutNote) {
		this.hosOutNote = hosOutNote;
	}

	public Date getHosOutTime() {
		return this.hosOutTime;
	}

	public void setHosOutTime(Date hosOutTime) {
		this.hosOutTime = hosOutTime;
	}

	public HosDoctorAdvice getHosDoctorAdvice() {
		return this.hosDoctorAdvice;
	}

	public void setHosDoctorAdvice(HosDoctorAdvice hosDoctorAdvice) {
		this.hosDoctorAdvice = hosDoctorAdvice;
	}

}