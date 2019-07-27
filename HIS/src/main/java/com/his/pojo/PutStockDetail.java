package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PUT_STOCK_DETAILS database table.
 * 
 */
@Entity
@Table(name="PUT_STOCK_DETAILS")
@NamedQuery(name="PutStockDetail.findAll", query="SELECT p FROM PutStockDetail p")
public class PutStockDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PutStockDetailPK id;

	private String apperance;

	@Column(name="PUT_NUM")
	private BigDecimal putNum;

	//bi-directional many-to-one association to DrugWarehouse
	@ManyToOne
	@JoinColumn(name="PCKC_ID")
	private DrugWarehouse drugWarehouse;

	//bi-directional many-to-one association to PutStock
	@ManyToOne
	@JoinColumn(name="RK_ID")
	private PutStock putStock;

	public PutStockDetail() {
	}

	public PutStockDetailPK getId() {
		return this.id;
	}

	public void setId(PutStockDetailPK id) {
		this.id = id;
	}

	public String getApperance() {
		return this.apperance;
	}

	public void setApperance(String apperance) {
		this.apperance = apperance;
	}

	public BigDecimal getPutNum() {
		return this.putNum;
	}

	public void setPutNum(BigDecimal putNum) {
		this.putNum = putNum;
	}

	public DrugWarehouse getDrugWarehouse() {
		return this.drugWarehouse;
	}

	public void setDrugWarehouse(DrugWarehouse drugWarehouse) {
		this.drugWarehouse = drugWarehouse;
	}

	public PutStock getPutStock() {
		return this.putStock;
	}

	public void setPutStock(PutStock putStock) {
		this.putStock = putStock;
	}

}