package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the HOS_DRUG_DETAIL database table.
 * 
 */
@Entity
@Table(name="HOS_DRUG_DETAIL")
@NamedQuery(name="HosDrugDetail.findAll", query="SELECT h FROM HosDrugDetail h")
public class HosDrugDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HosDrugDetailPK id;

	@Column(name="DRUG_USE_NUM")
	private BigDecimal drugUseNum;

	@Column(name="DRUG_USE_UNIT")
	private String drugUseUnit;
	
	//bi-directional many-to-one association to DrugInformation
	@ManyToOne
	@JoinColumn(name="YP_ID", insertable=false, updatable=false)
	private DrugInformation drugInformation;
	
	//bi-directional many-to-one association to DrugInformation
	@ManyToOne
	@JoinColumn(name="HOS_DRUG_RID", insertable=false, updatable=false)
	private HosDrugRecord hosDrugRecord;

	public DrugInformation getDrugInformation() {
		return drugInformation;
	}

	public void setDrugInformation(DrugInformation drugInformation) {
		this.drugInformation = drugInformation;
	}

	public HosDrugRecord getHosDrugRecord() {
		return hosDrugRecord;
	}

	public void setHosDrugRecord(HosDrugRecord hosDrugRecord) {
		this.hosDrugRecord = hosDrugRecord;
	}

	public HosDrugDetail() {
	}

	public HosDrugDetailPK getId() {
		return this.id;
	}

	public void setId(HosDrugDetailPK id) {
		this.id = id;
	}

	public BigDecimal getDrugUseNum() {
		return this.drugUseNum;
	}

	public void setDrugUseNum(BigDecimal drugUseNum) {
		this.drugUseNum = drugUseNum;
	}

	public String getDrugUseUnit() {
		return this.drugUseUnit;
	}

	public void setDrugUseUnit(String drugUseUnit) {
		this.drugUseUnit = drugUseUnit;
	}

}