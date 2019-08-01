package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
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

	@Temporal(TemporalType.DATE)
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