package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BACK_DETAILS database table.
 * 
 */
@Entity
@Table(name="BACK_DETAILS")
@NamedQuery(name="BackDetail.findAll", query="SELECT b FROM BackDetail b")
public class BackDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BackDetailPK id;

	@Column(name="BACK_NUM")
	private BigDecimal backNum;

	//bi-directional many-to-one association to BackMedicine
	@ManyToOne
	@JoinColumn(name="BACK_ID", insertable=false, updatable=false)
	private BackMedicine backMedicine;

	//bi-directional many-to-one association to Medicine
	@ManyToOne
	@JoinColumn(name="MEDICINE_ID", insertable=false, updatable=false)
	private Medicine medicine;

	public BackDetail() {
	}

	public BackDetailPK getId() {
		return this.id;
	}

	public void setId(BackDetailPK id) {
		this.id = id;
	}

	public BigDecimal getBackNum() {
		return this.backNum;
	}

	public void setBackNum(BigDecimal backNum) {
		this.backNum = backNum;
	}

	public BackMedicine getBackMedicine() {
		return this.backMedicine;
	}

	public void setBackMedicine(BackMedicine backMedicine) {
		this.backMedicine = backMedicine;
	}

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

}