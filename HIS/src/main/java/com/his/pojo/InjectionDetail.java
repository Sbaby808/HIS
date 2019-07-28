package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the INJECTION_DETAIL database table.
 * 
 */
@Entity
@Table(name="INJECTION_DETAIL")
@NamedQuery(name="InjectionDetail.findAll", query="SELECT i FROM InjectionDetail i")
public class InjectionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InjectionDetailPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="DRUG_TIME")
	private Date drugTime;

	@Column(name="DRUG_WAY")
	private String drugWay;

	@Column(name="PS_DRUG_NUM")
	private BigDecimal psDrugNum;

	@Column(name="PS_DRUG_UNIT")
	private String psDrugUnit;

	//bi-directional many-to-one association to Medicine
	@ManyToOne
	@JoinColumn(name="MEDICINE_ID", insertable=false, updatable=false)
	private Medicine medicine;

	//bi-directional many-to-one association to UseDrugRecord
	@ManyToOne
	@JoinColumn(name="INJ_ID", insertable=false, updatable=false)
	private UseDrugRecord useDrugRecord;

	public InjectionDetail() {
	}

	public InjectionDetailPK getId() {
		return this.id;
	}

	public void setId(InjectionDetailPK id) {
		this.id = id;
	}

	public Date getDrugTime() {
		return this.drugTime;
	}

	public void setDrugTime(Date drugTime) {
		this.drugTime = drugTime;
	}

	public String getDrugWay() {
		return this.drugWay;
	}

	public void setDrugWay(String drugWay) {
		this.drugWay = drugWay;
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

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public UseDrugRecord getUseDrugRecord() {
		return this.useDrugRecord;
	}

	public void setUseDrugRecord(UseDrugRecord useDrugRecord) {
		this.useDrugRecord = useDrugRecord;
	}

}