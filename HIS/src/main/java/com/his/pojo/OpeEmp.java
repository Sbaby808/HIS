package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OPE_EMP database table.
 * 
 */
@Entity
@Table(name="OPE_EMP")
@NamedQuery(name="OpeEmp.findAll", query="SELECT o FROM OpeEmp o")
public class OpeEmp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OpeEmpPK id;

	private String duty;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH", insertable=false, updatable=false)
	private EmpInformation empInformation;

	//bi-directional many-to-one association to OperationRecord
	@ManyToOne
	@JoinColumn(name="OPE_ID", insertable=false, updatable=false)
	private OperationRecord operationRecord;

	public OpeEmp() {
	}

	public OpeEmpPK getId() {
		return this.id;
	}

	public void setId(OpeEmpPK id) {
		this.id = id;
	}

	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public OperationRecord getOperationRecord() {
		return this.operationRecord;
	}

	public void setOperationRecord(OperationRecord operationRecord) {
		this.operationRecord = operationRecord;
	}

}