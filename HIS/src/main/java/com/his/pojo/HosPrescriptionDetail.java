package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the HOS_PRESCRIPTION_DETAIL database table.
 * 
 */
@Entity
@Table(name="HOS_PRESCRIPTION_DETAIL")
@NamedQuery(name="HosPrescriptionDetail.findAll", query="SELECT h FROM HosPrescriptionDetail h")
public class HosPrescriptionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HosPrescriptionDetailPK id;

	@Column(name="HOS_PRE_DNUM")
	private BigDecimal hosPreDnum;

	@Column(name="HOS_PRE_DUNIT")
	private String hosPreDunit;

	@Column(name="HOS_PRE_PRICE")
	private BigDecimal hosPrePrice;

	//bi-directional many-to-one association to DrugInformation
	@ManyToOne
	@JoinColumn(name="YP_ID", insertable=false, updatable=false)
	private DrugInformation drugInformation;

	//bi-directional many-to-one association to HosPrescription
	@ManyToOne
	@JoinColumn(name="HOS_PRE_ID", insertable=false, updatable=false)
	private HosPrescription hosPrescription;

	public HosPrescriptionDetail() {
	}

	public HosPrescriptionDetailPK getId() {
		return this.id;
	}

	public void setId(HosPrescriptionDetailPK id) {
		this.id = id;
	}

	public BigDecimal getHosPreDnum() {
		return this.hosPreDnum;
	}

	public void setHosPreDnum(BigDecimal hosPreDnum) {
		this.hosPreDnum = hosPreDnum;
	}

	public String getHosPreDunit() {
		return this.hosPreDunit;
	}

	public void setHosPreDunit(String hosPreDunit) {
		this.hosPreDunit = hosPreDunit;
	}

	public BigDecimal getHosPrePrice() {
		return this.hosPrePrice;
	}

	public void setHosPrePrice(BigDecimal hosPrePrice) {
		this.hosPrePrice = hosPrePrice;
	}

	public DrugInformation getDrugInformation() {
		return this.drugInformation;
	}

	public void setDrugInformation(DrugInformation drugInformation) {
		this.drugInformation = drugInformation;
	}

	public HosPrescription getHosPrescription() {
		return this.hosPrescription;
	}

	public void setHosPrescription(HosPrescription hosPrescription) {
		this.hosPrescription = hosPrescription;
	}

}