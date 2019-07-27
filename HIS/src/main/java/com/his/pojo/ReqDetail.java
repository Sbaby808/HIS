package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the REQ_DETAILS database table.
 * 
 */
@Entity
@Table(name="REQ_DETAILS")
@NamedQuery(name="ReqDetail.findAll", query="SELECT r FROM ReqDetail r")
public class ReqDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReqDetailPK id;

	@Column(name="REQ_NUM")
	private BigDecimal reqNum;

	//bi-directional many-to-one association to DrugInformation
	@ManyToOne
	@JoinColumn(name="YP_ID")
	private DrugInformation drugInformation;

	//bi-directional many-to-one association to OutpatientRequestionMedicine
	@ManyToOne
	@JoinColumn(name="REQ_ID")
	private OutpatientRequestionMedicine outpatientRequestionMedicine;

	public ReqDetail() {
	}

	public ReqDetailPK getId() {
		return this.id;
	}

	public void setId(ReqDetailPK id) {
		this.id = id;
	}

	public BigDecimal getReqNum() {
		return this.reqNum;
	}

	public void setReqNum(BigDecimal reqNum) {
		this.reqNum = reqNum;
	}

	public DrugInformation getDrugInformation() {
		return this.drugInformation;
	}

	public void setDrugInformation(DrugInformation drugInformation) {
		this.drugInformation = drugInformation;
	}

	public OutpatientRequestionMedicine getOutpatientRequestionMedicine() {
		return this.outpatientRequestionMedicine;
	}

	public void setOutpatientRequestionMedicine(OutpatientRequestionMedicine outpatientRequestionMedicine) {
		this.outpatientRequestionMedicine = outpatientRequestionMedicine;
	}

}