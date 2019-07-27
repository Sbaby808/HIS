package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	@Temporal(TemporalType.DATE)
	@Column(name="RY_TIME")
	private Date ryTime;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-one association to SolveScheme
	@ManyToOne
	@JoinColumn(name="SCHE_ID")
	private SolveScheme solveScheme;

	//bi-directional many-to-one association to SolveScheme
	@OneToMany(mappedBy="hospitalNotice")
	private List<SolveScheme> solveSchemes;

	public HospitalNotice() {
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

	public List<SolveScheme> getSolveSchemes() {
		return this.solveSchemes;
	}

	public void setSolveSchemes(List<SolveScheme> solveSchemes) {
		this.solveSchemes = solveSchemes;
	}

	public SolveScheme addSolveScheme(SolveScheme solveScheme) {
		getSolveSchemes().add(solveScheme);
		solveScheme.setHospitalNotice(this);

		return solveScheme;
	}

	public SolveScheme removeSolveScheme(SolveScheme solveScheme) {
		getSolveSchemes().remove(solveScheme);
		solveScheme.setHospitalNotice(null);

		return solveScheme;
	}

}