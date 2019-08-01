package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the OUT_HOSPITAI_RECORD database table.
 * 
 */
@Entity
@Table(name="OUT_HOSPITAI_RECORD")
@NamedQuery(name="OutHospitaiRecord.findAll", query="SELECT o FROM OutHospitaiRecord o")
public class OutHospitaiRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OUT_RID")
	private String outRid;

	@Temporal(TemporalType.DATE)
	@Column(name="OUT_REC_TIME")
	private Date outRecTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional one-to-one association to HospitalizedPatient
	@OneToOne
	@JoinColumn(name="HOSP_ID")
	private HospitalizedPatient hospitalizedPatient;

	public OutHospitaiRecord() {
	}

	public String getOutRid() {
		return this.outRid;
	}

	public void setOutRid(String outRid) {
		this.outRid = outRid;
	}

	public Date getOutRecTime() {
		return this.outRecTime;
	}

	public void setOutRecTime(Date outRecTime) {
		this.outRecTime = outRecTime;
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