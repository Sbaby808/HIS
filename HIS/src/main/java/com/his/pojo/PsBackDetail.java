package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PS_BACK_DETAILS database table.
 * 
 */
@Entity
@Table(name="PS_BACK_DETAILS")
@NamedQuery(name="PsBackDetail.findAll", query="SELECT p FROM PsBackDetail p")
public class PsBackDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PsBackDetailPK id;

	@Column(name="PS_DRUG_AMOUNT")
	private String psDrugAmount;

	@Column(name="PS_DRUG_NUM")
	private BigDecimal psDrugNum;

	@Column(name="PS_DRUG_UNIT")
	private String psDrugUnit;

	//bi-directional many-to-one association to DrugWarehouse
	@ManyToOne
	@JoinColumn(name="PCKC_ID")
	private DrugWarehouse drugWarehouse;

	//bi-directional many-to-one association to PutstockBack
	@ManyToOne
	@JoinColumn(name="PS_BACK_ID_")
	private PutstockBack putstockBack;

	public PsBackDetail() {
	}

	public PsBackDetailPK getId() {
		return this.id;
	}

	public void setId(PsBackDetailPK id) {
		this.id = id;
	}

	public String getPsDrugAmount() {
		return this.psDrugAmount;
	}

	public void setPsDrugAmount(String psDrugAmount) {
		this.psDrugAmount = psDrugAmount;
	}

	public BigDecimal getPsDrugNum() {
		return this.psDrugNum;
	}

	public void setPsDrugNum(BigDecimal psDrugNum) {
		this.psDrugNum = psDrugNum;
	}

	public String getPsDrugUnit() {
		return this.psDrugUnit;
	}

	public void setPsDrugUnit(String psDrugUnit) {
		this.psDrugUnit = psDrugUnit;
	}

	public DrugWarehouse getDrugWarehouse() {
		return this.drugWarehouse;
	}

	public void setDrugWarehouse(DrugWarehouse drugWarehouse) {
		this.drugWarehouse = drugWarehouse;
	}

	public PutstockBack getPutstockBack() {
		return this.putstockBack;
	}

	public void setPutstockBack(PutstockBack putstockBack) {
		this.putstockBack = putstockBack;
	}

}