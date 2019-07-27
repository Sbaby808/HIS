package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PHA_IN_DETAILS database table.
 * 
 */
@Entity
@Table(name="PHA_IN_DETAILS")
@NamedQuery(name="PhaInDetail.findAll", query="SELECT p FROM PhaInDetail p")
public class PhaInDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PhaInDetailPK id;

	@Column(name="PHA_IN_NUM")
	private BigDecimal phaInNum;

	//bi-directional many-to-one association to Medicine
	@ManyToOne
	@JoinColumn(name="MEDICINE_ID")
	private Medicine medicine;

	//bi-directional many-to-one association to PhaIn
	@ManyToOne
	@JoinColumn(name="PHA_IN_ID")
	private PhaIn phaIn;

	public PhaInDetail() {
	}

	public PhaInDetailPK getId() {
		return this.id;
	}

	public void setId(PhaInDetailPK id) {
		this.id = id;
	}

	public BigDecimal getPhaInNum() {
		return this.phaInNum;
	}

	public void setPhaInNum(BigDecimal phaInNum) {
		this.phaInNum = phaInNum;
	}

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public PhaIn getPhaIn() {
		return this.phaIn;
	}

	public void setPhaIn(PhaIn phaIn) {
		this.phaIn = phaIn;
	}

}