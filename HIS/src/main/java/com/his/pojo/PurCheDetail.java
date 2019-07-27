package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PUR_CHE_DETAILS database table.
 * 
 */
@Entity
@Table(name="PUR_CHE_DETAILS")
@NamedQuery(name="PurCheDetail.findAll", query="SELECT p FROM PurCheDetail p")
public class PurCheDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PurCheDetailPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="PRU_CHE_DETPROD")
	private Date pruCheDetprod;

	@Column(name="PRU_CHE_DETREASON")
	private String pruCheDetreason;

	@Column(name="PUR_CHE_DETDEAL")
	private String purCheDetdeal;

	@Column(name="PUR_CHE_DETID")
	private BigDecimal purCheDetid;

	//bi-directional many-to-one association to DrugInformation
	@ManyToOne
	@JoinColumn(name="YP_ID")
	private DrugInformation drugInformation;

	//bi-directional many-to-one association to PurchaseCheck
	@ManyToOne
	@JoinColumn(name="PUR_CHA_ID")
	private PurchaseCheck purchaseCheck;

	public PurCheDetail() {
	}

	public PurCheDetailPK getId() {
		return this.id;
	}

	public void setId(PurCheDetailPK id) {
		this.id = id;
	}

	public Date getPruCheDetprod() {
		return this.pruCheDetprod;
	}

	public void setPruCheDetprod(Date pruCheDetprod) {
		this.pruCheDetprod = pruCheDetprod;
	}

	public String getPruCheDetreason() {
		return this.pruCheDetreason;
	}

	public void setPruCheDetreason(String pruCheDetreason) {
		this.pruCheDetreason = pruCheDetreason;
	}

	public String getPurCheDetdeal() {
		return this.purCheDetdeal;
	}

	public void setPurCheDetdeal(String purCheDetdeal) {
		this.purCheDetdeal = purCheDetdeal;
	}

	public BigDecimal getPurCheDetid() {
		return this.purCheDetid;
	}

	public void setPurCheDetid(BigDecimal purCheDetid) {
		this.purCheDetid = purCheDetid;
	}

	public DrugInformation getDrugInformation() {
		return this.drugInformation;
	}

	public void setDrugInformation(DrugInformation drugInformation) {
		this.drugInformation = drugInformation;
	}

	public PurchaseCheck getPurchaseCheck() {
		return this.purchaseCheck;
	}

	public void setPurchaseCheck(PurchaseCheck purchaseCheck) {
		this.purchaseCheck = purchaseCheck;
	}

}