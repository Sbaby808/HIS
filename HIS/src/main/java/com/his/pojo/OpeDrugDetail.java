package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the OPE_DRUG_DETAILS database table.
 * 
 */
@Entity
@Table(name="OPE_DRUG_DETAILS")
@NamedQuery(name="OpeDrugDetail.findAll", query="SELECT o FROM OpeDrugDetail o")
public class OpeDrugDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OpeDrugDetailPK id;

	@Column(name="OPE_DRUG_NUM")
	private BigDecimal opeDrugNum;

	@Column(name="OPE_DRUG_TIME")
	private String opeDrugTime;

	@Column(name="OPE_DRUG_UNIT")
	private String opeDrugUnit;

	//bi-directional many-to-one association to Medicine
	@ManyToOne
	@JoinColumn(name="MEDICINE_ID", insertable=false, updatable=false)
	private Medicine medicine;

	//bi-directional many-to-one association to OperationRecord
	@ManyToOne
	@JoinColumn(name="OPE_ID", insertable=false, updatable=false)
	private OperationRecord operationRecord;

	public OpeDrugDetail() {
	}

	public OpeDrugDetailPK getId() {
		return this.id;
	}

	public void setId(OpeDrugDetailPK id) {
		this.id = id;
	}

	public BigDecimal getOpeDrugNum() {
		return this.opeDrugNum;
	}

	public void setOpeDrugNum(BigDecimal opeDrugNum) {
		this.opeDrugNum = opeDrugNum;
	}

	public String getOpeDrugTime() {
		return this.opeDrugTime;
	}

	public void setOpeDrugTime(String opeDrugTime) {
		this.opeDrugTime = opeDrugTime;
	}

	public String getOpeDrugUnit() {
		return this.opeDrugUnit;
	}

	public void setOpeDrugUnit(String opeDrugUnit) {
		this.opeDrugUnit = opeDrugUnit;
	}

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public OperationRecord getOperationRecord() {
		return this.operationRecord;
	}

	public void setOperationRecord(OperationRecord operationRecord) {
		this.operationRecord = operationRecord;
	}

}