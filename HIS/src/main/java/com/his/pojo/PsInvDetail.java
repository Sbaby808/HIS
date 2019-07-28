package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PS_INV_DETAILS database table.
 * 
 */
@Entity
@Table(name="PS_INV_DETAILS")
@NamedQuery(name="PsInvDetail.findAll", query="SELECT p FROM PsInvDetail p")
public class PsInvDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PsInvDetailPK id;

	@Column(name="BOOK_NUM")
	private BigDecimal bookNum;

	@Column(name="REAL_NUM")
	private BigDecimal realNum;

	@Column(name="SUB_NUM")
	private BigDecimal subNum;

	@Column(name="SUB_REASON")
	private String subReason;

	//bi-directional many-to-one association to DrugWarehouse
	@ManyToOne
	@JoinColumn(name="PCKC_ID", insertable=false, updatable=false)
	private DrugWarehouse drugWarehouse;

	//bi-directional many-to-one association to PsInventoryTaking
	@ManyToOne
	@JoinColumn(name="INVENTORY_TAKING_ID", insertable=false, updatable=false)
	private PsInventoryTaking psInventoryTaking;

	public PsInvDetail() {
	}

	public PsInvDetailPK getId() {
		return this.id;
	}

	public void setId(PsInvDetailPK id) {
		this.id = id;
	}

	public BigDecimal getBookNum() {
		return this.bookNum;
	}

	public void setBookNum(BigDecimal bookNum) {
		this.bookNum = bookNum;
	}

	public BigDecimal getRealNum() {
		return this.realNum;
	}

	public void setRealNum(BigDecimal realNum) {
		this.realNum = realNum;
	}

	public BigDecimal getSubNum() {
		return this.subNum;
	}

	public void setSubNum(BigDecimal subNum) {
		this.subNum = subNum;
	}

	public String getSubReason() {
		return this.subReason;
	}

	public void setSubReason(String subReason) {
		this.subReason = subReason;
	}

	public DrugWarehouse getDrugWarehouse() {
		return this.drugWarehouse;
	}

	public void setDrugWarehouse(DrugWarehouse drugWarehouse) {
		this.drugWarehouse = drugWarehouse;
	}

	public PsInventoryTaking getPsInventoryTaking() {
		return this.psInventoryTaking;
	}

	public void setPsInventoryTaking(PsInventoryTaking psInventoryTaking) {
		this.psInventoryTaking = psInventoryTaking;
	}

}