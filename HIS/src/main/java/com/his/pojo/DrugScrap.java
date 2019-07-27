package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the DRUG_SCRAP database table.
 * 
 */
@Entity
@Table(name="DRUG_SCRAP")
@NamedQuery(name="DrugScrap.findAll", query="SELECT d FROM DrugScrap d")
public class DrugScrap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BF_ID")
	private String bfId;

	@Temporal(TemporalType.DATE)
	@Column(name="BF_DATE")
	private Date bfDate;

	@Column(name="BF_REASON")
	private String bfReason;

	@Column(name="BF_TYPE")
	private String bfType;

	@Column(name="LOST_MONEY")
	private BigDecimal lostMoney;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to DrugScrapDetail
	@OneToMany(mappedBy="drugScrap")
	private List<DrugScrapDetail> drugScrapDetails;

	public DrugScrap() {
	}

	public String getBfId() {
		return this.bfId;
	}

	public void setBfId(String bfId) {
		this.bfId = bfId;
	}

	public Date getBfDate() {
		return this.bfDate;
	}

	public void setBfDate(Date bfDate) {
		this.bfDate = bfDate;
	}

	public String getBfReason() {
		return this.bfReason;
	}

	public void setBfReason(String bfReason) {
		this.bfReason = bfReason;
	}

	public String getBfType() {
		return this.bfType;
	}

	public void setBfType(String bfType) {
		this.bfType = bfType;
	}

	public BigDecimal getLostMoney() {
		return this.lostMoney;
	}

	public void setLostMoney(BigDecimal lostMoney) {
		this.lostMoney = lostMoney;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<DrugScrapDetail> getDrugScrapDetails() {
		return this.drugScrapDetails;
	}

	public void setDrugScrapDetails(List<DrugScrapDetail> drugScrapDetails) {
		this.drugScrapDetails = drugScrapDetails;
	}

	public DrugScrapDetail addDrugScrapDetail(DrugScrapDetail drugScrapDetail) {
		getDrugScrapDetails().add(drugScrapDetail);
		drugScrapDetail.setDrugScrap(this);

		return drugScrapDetail;
	}

	public DrugScrapDetail removeDrugScrapDetail(DrugScrapDetail drugScrapDetail) {
		getDrugScrapDetails().remove(drugScrapDetail);
		drugScrapDetail.setDrugScrap(null);

		return drugScrapDetail;
	}

}