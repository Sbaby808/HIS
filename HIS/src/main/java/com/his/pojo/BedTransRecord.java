package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BED_TRANS_RECORD database table.
 * 
 */
@Entity
@Table(name="BED_TRANS_RECORD")
@NamedQuery(name="BedTransRecord.findAll", query="SELECT b FROM BedTransRecord b")
public class BedTransRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BR_ID")
	private String brId;

	@Temporal(TemporalType.DATE)
	@Column(name="BR_TIME")
	private Date brTime;

	@Column(name="IN_BNAME")
	private String inBname;

	@Column(name="OUT_BNAME")
	private String outBname;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to HospitalizedPatient
	@ManyToOne
	@JoinColumn(name="HOSP_ID")
	private HospitalizedPatient hospitalizedPatient;

	public BedTransRecord() {
	}

	public String getBrId() {
		return this.brId;
	}

	public void setBrId(String brId) {
		this.brId = brId;
	}

	public Date getBrTime() {
		return this.brTime;
	}

	public void setBrTime(Date brTime) {
		this.brTime = brTime;
	}

	public String getInBname() {
		return this.inBname;
	}

	public void setInBname(String inBname) {
		this.inBname = inBname;
	}

	public String getOutBname() {
		return this.outBname;
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