package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

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

	//bi-directional many-to-one association to OperPayRecord
	@OneToMany(mappedBy="operationRecord")
	@JSONField(serialize=false)
	private List<OperPayRecord> operPayRecords;

	//bi-directional many-to-one association to OpeDrugDetail
	@OneToMany(mappedBy="operationRecord")
	@JSONField(serialize=false)
	private List<OpeDrugDetail> opeDrugDetails;

	//bi-directional many-to-one association to OpeEmp
	@OneToMany(mappedBy="operationRecord")
	
	private List<OpeEmp> opeEmps;

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

	public List<OperPayRecord> getOperPayRecords() {
		return this.operPayRecords;
	}

	public void setOperPayRecords(List<OperPayRecord> operPayRecords) {
		this.operPayRecords = operPayRecords;
	}

	public OperPayRecord addOperPayRecord(OperPayRecord operPayRecord) {
		getOperPayRecords().add(operPayRecord);
		operPayRecord.setOperationRecord(this);

		return operPayRecord;
	}

	public OperPayRecord removeOperPayRecord(OperPayRecord operPayRecord) {
		getOperPayRecords().remove(operPayRecord);
		operPayRecord.setOperationRecord(null);

		return operPayRecord;
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

	public List<OpeEmp> getOpeEmps() {
		return this.opeEmps;
	}

	public void setOpeEmps(List<OpeEmp> opeEmps) {
		this.opeEmps = opeEmps;
	}

	public OpeEmp addOpeEmp(OpeEmp opeEmp) {
		getOpeEmps().add(opeEmp);
		opeEmp.setOperationRecord(this);

		return opeEmp;
	}

	public OpeEmp removeOpeEmp(OpeEmp opeEmp) {
		getOpeEmps().remove(opeEmp);
		opeEmp.setOperationRecord(null);

		return opeEmp;
	}

}