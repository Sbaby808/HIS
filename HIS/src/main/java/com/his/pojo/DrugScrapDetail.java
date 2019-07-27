package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DRUG_SCRAP_DETAILS database table.
 * 
 */
@Entity
@Table(name="DRUG_SCRAP_DETAILS")
@NamedQuery(name="DrugScrapDetail.findAll", query="SELECT d FROM DrugScrapDetail d")
public class DrugScrapDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DrugScrapDetailPK id;

	@Column(name="BF_NUM")
	private BigDecimal bfNum;

	//bi-directional many-to-one association to DrugScrap
	@ManyToOne
	@JoinColumn(name="BF_ID")
	private DrugScrap drugScrap;

	//bi-directional many-to-one association to DrugWarehouse
	@ManyToOne
	@JoinColumn(name="PCKC_ID")
	private DrugWarehouse drugWarehouse;

	public DrugScrapDetail() {
	}

	public DrugScrapDetailPK getId() {
		return this.id;
	}

	public void setId(DrugScrapDetailPK id) {
		this.id = id;
	}

	public BigDecimal getBfNum() {
		return this.bfNum;
	}

	public void setBfNum(BigDecimal bfNum) {
		this.bfNum = bfNum;
	}

	public DrugScrap getDrugScrap() {
		return this.drugScrap;
	}

	public void setDrugScrap(DrugScrap drugScrap) {
		this.drugScrap = drugScrap;
	}

	public DrugWarehouse getDrugWarehouse() {
		return this.drugWarehouse;
	}

	public void setDrugWarehouse(DrugWarehouse drugWarehouse) {
		this.drugWarehouse = drugWarehouse;
	}

}