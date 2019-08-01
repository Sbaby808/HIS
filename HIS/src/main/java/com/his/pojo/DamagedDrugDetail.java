package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DAMAGED_DRUG_DETAILS database table.
 * 
 */
@Entity
@Table(name="DAMAGED_DRUG_DETAILS")
@NamedQuery(name="DamagedDrugDetail.findAll", query="SELECT d FROM DamagedDrugDetail d")
public class DamagedDrugDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DamagedDrugDetailPK id;

	@Column(name="DAM_NUM")
	private BigDecimal damNum;

	//bi-directional many-to-one association to DamagedMedicine
	@ManyToOne
	@JoinColumn(name="DAMAGED_ID", insertable=false, updatable=false)
	private DamagedMedicine damagedMedicine;

	//bi-directional many-to-one association to Medicine
	@ManyToOne
	@JoinColumn(name="MEDICINE_ID", insertable=false, updatable=false)
	private Medicine medicine;

	public DamagedDrugDetail() {
	}

	public DamagedDrugDetailPK getId() {
		return this.id;
	}

	public void setId(DamagedDrugDetailPK id) {
		this.id = id;
	}

	public BigDecimal getDamNum() {
		return this.damNum;
	}

	public void setDamNum(BigDecimal damNum) {
		this.damNum = damNum;
	}

	public DamagedMedicine getDamagedMedicine() {
		return this.damagedMedicine;
	}

	public void setDamagedMedicine(DamagedMedicine damagedMedicine) {
		this.damagedMedicine = damagedMedicine;
	}

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

}