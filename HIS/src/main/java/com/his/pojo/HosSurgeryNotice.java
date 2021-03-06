package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HOS_SURGERY_NOTICE database table.
 * 
 */
@Entity
@Table(name="HOS_SURGERY_NOTICE")
@NamedQuery(name="HosSurgeryNotice.findAll", query="SELECT h FROM HosSurgeryNotice h")
public class HosSurgeryNotice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_SUR_ID")
	private String hosSurId;

	@Column(name="HOS_SUR_NOTE")
	private String hosSurNote;

	@Temporal(TemporalType.DATE)
	@Column(name="HOS_SUR_TIME")
	private Date hosSurTime;

	//bi-directional many-to-one association to HosDoctorAdvice
	@ManyToOne
	@JoinColumn(name="HOS_DCO_ID")
	private HosDoctorAdvice hosDoctorAdvice;

	public HosSurgeryNotice() {
	}

	public String getHosSurId() {
		return this.hosSurId;
	}

	public void setHosSurId(String hosSurId) {
		this.hosSurId = hosSurId;
	}

	public String getHosSurNote() {
		return this.hosSurNote;
	}

	public void setHosSurNote(String hosSurNote) {
		this.hosSurNote = hosSurNote;
	}

	public Date getHosSurTime() {
		return this.hosSurTime;
	}

	public void setHosSurTime(Date hosSurTime) {
		this.hosSurTime = hosSurTime;
	}

	public HosDoctorAdvice getHosDoctorAdvice() {
		return this.hosDoctorAdvice;
	}

	public void setHosDoctorAdvice(HosDoctorAdvice hosDoctorAdvice) {
		this.hosDoctorAdvice = hosDoctorAdvice;
	}

}