package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * The persistent class for the HOS_CHECK_NOTICE database table.
 * 
 */
@Entity
@Table(name="HOS_CHECK_NOTICE")
@NamedQuery(name="HosCheckNotice.findAll", query="SELECT h FROM HosCheckNotice h")
public class HosCheckNotice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name="HOS_CHECK_NID")
	private String hosCheckNid;

	@Column(name="HOS_CHECK_NOTE")
	private String hosCheckNote;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name="HOS_CHECK_NTIME")
	private Date hosCheckNtime;

	//bi-directional many-to-one association to HosDoctorAdvice
	@ManyToOne
	@JoinColumn(name="HOS_DCO_ID")
	private HosDoctorAdvice hosDoctorAdvice;

	public HosCheckNotice() {
	}

	public String getHosCheckNid() {
		return this.hosCheckNid;
	}

	public void setHosCheckNid(String hosCheckNid) {
		this.hosCheckNid = hosCheckNid;
	}

	public String getHosCheckNote() {
		return this.hosCheckNote;
	}

	public void setHosCheckNote(String hosCheckNote) {
		this.hosCheckNote = hosCheckNote;
	}

	public Date getHosCheckNtime() {
		return this.hosCheckNtime;
	}

	public void setHosCheckNtime(Date hosCheckNtime) {
		this.hosCheckNtime = hosCheckNtime;
	}

	public HosDoctorAdvice getHosDoctorAdvice() {
		return this.hosDoctorAdvice;
	}

	public void setHosDoctorAdvice(HosDoctorAdvice hosDoctorAdvice) {
		this.hosDoctorAdvice = hosDoctorAdvice;
	}

}