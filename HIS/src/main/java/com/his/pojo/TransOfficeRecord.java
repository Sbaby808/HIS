package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * The persistent class for the TRANS_OFFICE_RECORD database table.
 * 
 */
@Entity
@Table(name="TRANS_OFFICE_RECORD")
@NamedQuery(name="TransOfficeRecord.findAll", query="SELECT t FROM TransOfficeRecord t")
public class TransOfficeRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TRANS_OID")
	private String transOid;

	@Column(name="IN_DNAME")
	private String inDname;

	@Column(name="OUT_DNAME")
	private String outDname;
	
	@Column(name="IN_WNAME")
	private String inWname;

	@Column(name="OUT_WNAME")
	private String outWname;
	
	@Column(name="IN_RNAME")
	private String inRname;

	@Column(name="OUT_RNAME")
	private String outRname;
	
	@Column(name="IN_BNAME")
	private String inBname;

	@Column(name="OUT_BNAME")
	private String outBname;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name="OUT_OFFICE_TIME")
	private Date outOfficeTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to HospitalizedPatient
	@ManyToOne
	@JoinColumn(name="HOSP_ID")
	private HospitalizedPatient hospitalizedPatient;

	public TransOfficeRecord() {
	}

	public String getTransOid() {
		return this.transOid;
	}

	public void setTransOid(String transOid) {
		this.transOid = transOid;
	}

	public String getInDname() {
		return this.inDname;
	}

	public void setInDname(String inDname) {
		this.inDname = inDname;
	}

	public String getOutDname() {
		return this.outDname;
	}

	public void setOutDname(String outDname) {
		this.outDname = outDname;
	}

	public Date getOutOfficeTime() {
		return this.outOfficeTime;
	}

	public void setOutOfficeTime(Date outOfficeTime) {
		this.outOfficeTime = outOfficeTime;
	}
	

	public String getInWname() {
		return inWname;
	}

	public void setInWname(String inWname) {
		this.inWname = inWname;
	}

	public String getOutWname() {
		return outWname;
	}

	public void setOutWname(String outWname) {
		this.outWname = outWname;
	}

	public String getInRname() {
		return inRname;
	}

	public void setInRname(String inRname) {
		this.inRname = inRname;
	}

	public String getOutRname() {
		return outRname;
	}

	public void setOutRname(String outRname) {
		this.outRname = outRname;
	}

	public String getInBname() {
		return inBname;
	}

	public void setInBname(String inBname) {
		this.inBname = inBname;
	}

	public String getOutBname() {
		return outBname;
	}

	public void setOutBname(String outBname) {
		this.outBname = outBname;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public HospitalizedPatient getHospitalizedPatient() {
		return this.hospitalizedPatient;
	}

	public void setHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		this.hospitalizedPatient = hospitalizedPatient;
	}

}