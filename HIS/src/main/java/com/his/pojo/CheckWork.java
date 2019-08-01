package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CHECK_WORK database table.
 * 
 */
@Entity
@Table(name="CHECK_WORK")
@NamedQuery(name="CheckWork.findAll", query="SELECT c FROM CheckWork c")
public class CheckWork implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CHECK_WID")
	private String checkWid;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_WDATE")
	private Date checkWdate;

	@Column(name="CHECK_WTYPE")
	private String checkWtype;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	public CheckWork() {
	}

	public String getCheckWid() {
		return this.checkWid;
	}

	public void setCheckWid(String checkWid) {
		this.checkWid = checkWid;
	}

	public Date getCheckWdate() {
		return this.checkWdate;
	}

	public void setCheckWdate(Date checkWdate) {
		this.checkWdate = checkWdate;
	}

	public String getCheckWtype() {
		return this.checkWtype;
	}

	public void setCheckWtype(String checkWtype) {
		this.checkWtype = checkWtype;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}