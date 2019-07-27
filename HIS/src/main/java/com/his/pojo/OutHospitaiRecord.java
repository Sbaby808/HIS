package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	//bi-directional many-to-one association to HospitalizedPatient
	@OneToMany(mappedBy="outHospitaiRecord")
	private List<HospitalizedPatient> hospitalizedPatients;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to HospitalizedPatient
	@ManyToOne
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

	public List<HospitalizedPatient> getHospitalizedPatients() {
		return this.hospitalizedPatients;
	}

	public void setHospitalizedPatients(List<HospitalizedPatient> hospitalizedPatients) {
		this.hospitalizedPatients = hospitalizedPatients;
	}

	public HospitalizedPatient addHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().add(hospitalizedPatient);
		hospitalizedPatient.setOutHospitaiRecord(this);

		return hospitalizedPatient;
	}

	public HospitalizedPatient removeHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().remove(hospitalizedPatient);
		hospitalizedPatient.setOutHospitaiRecord(null);

		return hospitalizedPatient;
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