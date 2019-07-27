package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OPERATION_RECORD database table.
 * 
 */
@Entity
@Table(name="OPERATION_RECORD")
@NamedQuery(name="OperationRecord.findAll", query="SELECT o FROM OperationRecord o")
public class OperationRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OPE_ID")
	private String opeId;

	@Column(name="OPE_DIAGNOSE")
	private String opeDiagnose;

	@Column(name="OPE_JG")
	private String opeJg;

	@Column(name="OPE_STATUS")
	private String opeStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="OPE_TIME")
	private Date opeTime;

	@Column(name="OPE_TYPE")
	private String opeType;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation1;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="EMP_YGXH3")
	private EmpInformation empInformation2;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="EMP_YGXH2")
	private EmpInformation empInformation3;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="EMP_YGXH")
	private EmpInformation empInformation4;

	//bi-directional many-to-one association to OpeDrugDetail
	@OneToMany(mappedBy="operationRecord")
	private List<OpeDrugDetail> opeDrugDetails;

	public OperationRecord() {
	}

	public String getOpeId() {
		return this.opeId;
	}

	public void setOpeId(String opeId) {
		this.opeId = opeId;
	}

	public String getOpeDiagnose() {
		return this.opeDiagnose;
	}

	public void setOpeDiagnose(String opeDiagnose) {
		this.opeDiagnose = opeDiagnose;
	}

	public String getOpeJg() {
		return this.opeJg;
	}

	public void setOpeJg(String opeJg) {
		this.opeJg = opeJg;
	}

	public String getOpeStatus() {
		return this.opeStatus;
	}

	public void setOpeStatus(String opeStatus) {
		this.opeStatus = opeStatus;
	}

	public Date getOpeTime() {
		return this.opeTime;
	}

	public void setOpeTime(Date opeTime) {
		this.opeTime = opeTime;
	}

	public String getOpeType() {
		return this.opeType;
	}

	public void setOpeType(String opeType) {
		this.opeType = opeType;
	}

	public EmpInformation getEmpInformation1() {
		return this.empInformation1;
	}

	public void setEmpInformation1(EmpInformation empInformation1) {
		this.empInformation1 = empInformation1;
	}

	public EmpInformation getEmpInformation2() {
		return this.empInformation2;
	}

	public void setEmpInformation2(EmpInformation empInformation2) {
		this.empInformation2 = empInformation2;
	}

	public EmpInformation getEmpInformation3() {
		return this.empInformation3;
	}

	public void setEmpInformation3(EmpInformation empInformation3) {
		this.empInformation3 = empInformation3;
	}

	public EmpInformation getEmpInformation4() {
		return this.empInformation4;
	}

	public void setEmpInformation4(EmpInformation empInformation4) {
		this.empInformation4 = empInformation4;
	}

	public List<OpeDrugDetail> getOpeDrugDetails() {
		return this.opeDrugDetails;
	}

	public void setOpeDrugDetails(List<OpeDrugDetail> opeDrugDetails) {
		this.opeDrugDetails = opeDrugDetails;
	}

	public OpeDrugDetail addOpeDrugDetail(OpeDrugDetail opeDrugDetail) {
		getOpeDrugDetails().add(opeDrugDetail);
		opeDrugDetail.setOperationRecord(this);

		return opeDrugDetail;
	}

	public OpeDrugDetail removeOpeDrugDetail(OpeDrugDetail opeDrugDetail) {
		getOpeDrugDetails().remove(opeDrugDetail);
		opeDrugDetail.setOperationRecord(null);

		return opeDrugDetail;
	}

}