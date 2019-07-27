package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CHANGE_DRUG_DETAILS database table.
 * 
 */
@Entity
@Table(name="CHANGE_DRUG_DETAILS")
@NamedQuery(name="ChangeDrugDetail.findAll", query="SELECT c FROM ChangeDrugDetail c")
public class ChangeDrugDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ChangeDrugDetailPK id;

	@Column(name="CHANGE_DRUG_NUM")
	private BigDecimal changeDrugNum;

	//bi-directional many-to-one association to AllocationOutbound
	@ManyToOne
	@JoinColumn(name="ALLO_ID")
	private AllocationOutbound allocationOutbound;

	//bi-directional many-to-one association to Medicine
	@ManyToOne
	@JoinColumn(name="MEDICINE_ID")
	private Medicine medicine;

	public ChangeDrugDetail() {
	}

	public ChangeDrugDetailPK getId() {
		return this.id;
	}

	public void setId(ChangeDrugDetailPK id) {
		this.id = id;
	}

	public BigDecimal getChangeDrugNum() {
		return this.changeDrugNum;
	}

	public void setChangeDrugNum(BigDecimal changeDrugNum) {
		this.changeDrugNum = changeDrugNum;
	}

	public AllocationOutbound getAllocationOutbound() {
		return this.allocationOutbound;
	}

	public void setAllocationOutbound(AllocationOutbound allocationOutbound) {
		this.allocationOutbound = allocationOutbound;
	}

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

}