package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * The persistent class for the HOSPITAL_NOTICE database table.
 * 
 */
@Entity
@Table(name="HOSPITAL_NOTICE")
@NamedQuery(name="HospitalNotice.findAll", query="SELECT h FROM HospitalNotice h")
public class HospitalNotice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RYTZ_ID")
	private String rytzId;

	@Column(name="RY_NOTE")
	private String ryNote;
	
	@JSONField(format="yyyy-MM-dd")
	@Column(name="HOS_NOTE_START_TIME")
	private Date hosNoteStartTime;
	
	@JSONField(format="yyyy-MM-dd")
	@Column(name="HOS_NOTE_END_TIME")
	private Date hosNoteEndTime;

	@JSONField(format="yyyy-MM-dd")
	@Column(name="RY_TIME")
	private Date ryTime;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional one-to-one association to SolveScheme
	@OneToOne(cascade  =CascadeType.MERGE)
	@JoinColumn(name="SCHE_ID")
	private SolveScheme solveScheme;

	public HospitalNotice() {
	}

	public Date getHosNoteStartTime() {
		return hosNoteStartTime;
	}

	public void setHosNoteStartTime(Date hosNoteStartTime) {
		this.hosNoteStartTime = hosNoteStartTime;
	}

	public Date getHosNoteEndTime() {
		return hosNoteEndTime;
	}

	public void setHosNoteEndTime(Date hosNoteEndTime) {
		this.hosNoteEndTime = hosNoteEndTime;
	}

	public String getRytzId() {
		return this.rytzId;
	}

	public void setRytzId(String rytzId) {
		this.rytzId = rytzId;
	}

	public String getRyNote() {
		return this.ryNote;
	}

	public void setRyNote(String ryNote) {
		this.ryNote = ryNote;
	}

	public Date getRyTime() {
		return this.ryTime;
	}

	public void setRyTime(Date ryTime) {
		this.ryTime = ryTime;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public SolveScheme getSolveScheme() {
		return this.solveScheme;
	}

	public void setSolveScheme(SolveScheme solveScheme) {
		this.solveScheme = solveScheme;
	}

}