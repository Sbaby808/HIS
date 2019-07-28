package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PURCHASE_DETAILS database table.
 * 
 */
@Entity
@Table(name="PURCHASE_DETAILS")
@NamedQuery(name="PurchaseDetail.findAll", query="SELECT p FROM PurchaseDetail p")
public class PurchaseDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PurchaseDetailPK id;

	@Column(name="PURCHASE_NUM")
	private BigDecimal purchaseNum;

	//bi-directional many-to-one association to DrugInformation
	@ManyToOne
	@JoinColumn(name="YP_ID", insertable=false, updatable=false)
	private DrugInformation drugInformation;

	//bi-directional many-to-one association to Purchase
	@ManyToOne
	@JoinColumn(name="CG_ID", insertable=false, updatable=false)
	private Purchase purchase;

	public PurchaseDetail() {
	}

	public PurchaseDetailPK getId() {
		return this.id;
	}

	public void setId(PurchaseDetailPK id) {
		this.id = id;
	}

	public BigDecimal getPurchaseNum() {
		return this.purchaseNum;
	}

	public void setPurchaseNum(BigDecimal purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public DrugInformation getDrugInformation() {
		return this.drugInformation;
	}

	public void setDrugInformation(DrugInformation drugInformation) {
		this.drugInformation = drugInformation;
	}

	public Purchase getPurchase() {
		return this.purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

}