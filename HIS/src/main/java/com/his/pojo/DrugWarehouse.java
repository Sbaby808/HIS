package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the DRUG_WAREHOUSE database table.
 * 
 */
@Entity
@Table(name="DRUG_WAREHOUSE")
@NamedQuery(name="DrugWarehouse.findAll", query="SELECT d FROM DrugWarehouse d")
public class DrugWarehouse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PCKC_ID")
	private String pckcId;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRE_DATE")
	private Date expireDate;

	@Column(name="NOW_NUMBER")
	private BigDecimal nowNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="PRODUCE_DATE")
	private Date produceDate;

	@Column(name="PUT_NUMBER")
	private BigDecimal putNumber;
	
	@Column(name="STATE")
	private String state;

	//bi-directional many-to-one association to DrugScrapDetail
	@OneToMany(mappedBy="drugWarehouse")
	@JSONField(serialize=false)
	private List<DrugScrapDetail> drugScrapDetails;

	//bi-directional many-to-one association to DrugInformation
	@ManyToOne
	@JoinColumn(name="YP_ID")
	private DrugInformation drugInformation;

	//bi-directional many-to-one association to Medicine
	@OneToMany(mappedBy="drugWarehouse")
	@JSONField(serialize=false)
	private List<Medicine> medicines;

	//bi-directional many-to-one association to OutstockDetail
	@OneToMany(mappedBy="drugWarehouse")
	@JSONField(serialize=false)
	private List<OutstockDetail> outstockDetails;

	//bi-directional many-to-one association to PsBackDetail
	@OneToMany(mappedBy="drugWarehouse")
	@JSONField(serialize=false)
	private List<PsBackDetail> psBackDetails;

	//bi-directional many-to-one association to PsInvDetail
	@OneToMany(mappedBy="drugWarehouse")
	@JSONField(serialize=false)
	private List<PsInvDetail> psInvDetails;

	//bi-directional many-to-one association to PutStockDetail
	@OneToMany(mappedBy="drugWarehouse")
	@JSONField(serialize=false)
	private List<PutStockDetail> putStockDetails;

	public DrugWarehouse() {
	}

	public String getPckcId() {
		return this.pckcId;
	}

	public void setPckcId(String pckcId) {
		this.pckcId = pckcId;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public BigDecimal getNowNumber() {
		return this.nowNumber;
	}

	public void setNowNumber(BigDecimal nowNumber) {
		this.nowNumber = nowNumber;
	}

	public Date getProduceDate() {
		return this.produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public BigDecimal getPutNumber() {
		return this.putNumber;
	}

	public void setPutNumber(BigDecimal putNumber) {
		this.putNumber = putNumber;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<DrugScrapDetail> getDrugScrapDetails() {
		return this.drugScrapDetails;
	}

	public void setDrugScrapDetails(List<DrugScrapDetail> drugScrapDetails) {
		this.drugScrapDetails = drugScrapDetails;
	}

	public DrugScrapDetail addDrugScrapDetail(DrugScrapDetail drugScrapDetail) {
		getDrugScrapDetails().add(drugScrapDetail);
		drugScrapDetail.setDrugWarehouse(this);

		return drugScrapDetail;
	}

	public DrugScrapDetail removeDrugScrapDetail(DrugScrapDetail drugScrapDetail) {
		getDrugScrapDetails().remove(drugScrapDetail);
		drugScrapDetail.setDrugWarehouse(null);

		return drugScrapDetail;
	}

	public DrugInformation getDrugInformation() {
		return this.drugInformation;
	}

	public void setDrugInformation(DrugInformation drugInformation) {
		this.drugInformation = drugInformation;
	}

	public List<Medicine> getMedicines() {
		return this.medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Medicine addMedicine(Medicine medicine) {
		getMedicines().add(medicine);
		medicine.setDrugWarehouse(this);

		return medicine;
	}

	public Medicine removeMedicine(Medicine medicine) {
		getMedicines().remove(medicine);
		medicine.setDrugWarehouse(null);

		return medicine;
	}

	public List<OutstockDetail> getOutstockDetails() {
		return this.outstockDetails;
	}

	public void setOutstockDetails(List<OutstockDetail> outstockDetails) {
		this.outstockDetails = outstockDetails;
	}

	public OutstockDetail addOutstockDetail(OutstockDetail outstockDetail) {
		getOutstockDetails().add(outstockDetail);
		outstockDetail.setDrugWarehouse(this);

		return outstockDetail;
	}

	public OutstockDetail removeOutstockDetail(OutstockDetail outstockDetail) {
		getOutstockDetails().remove(outstockDetail);
		outstockDetail.setDrugWarehouse(null);

		return outstockDetail;
	}

	public List<PsBackDetail> getPsBackDetails() {
		return this.psBackDetails;
	}

	public void setPsBackDetails(List<PsBackDetail> psBackDetails) {
		this.psBackDetails = psBackDetails;
	}

	public PsBackDetail addPsBackDetail(PsBackDetail psBackDetail) {
		getPsBackDetails().add(psBackDetail);
		psBackDetail.setDrugWarehouse(this);

		return psBackDetail;
	}

	public PsBackDetail removePsBackDetail(PsBackDetail psBackDetail) {
		getPsBackDetails().remove(psBackDetail);
		psBackDetail.setDrugWarehouse(null);

		return psBackDetail;
	}

	public List<PsInvDetail> getPsInvDetails() {
		return this.psInvDetails;
	}

	public void setPsInvDetails(List<PsInvDetail> psInvDetails) {
		this.psInvDetails = psInvDetails;
	}

	public PsInvDetail addPsInvDetail(PsInvDetail psInvDetail) {
		getPsInvDetails().add(psInvDetail);
		psInvDetail.setDrugWarehouse(this);

		return psInvDetail;
	}

	public PsInvDetail removePsInvDetail(PsInvDetail psInvDetail) {
		getPsInvDetails().remove(psInvDetail);
		psInvDetail.setDrugWarehouse(null);

		return psInvDetail;
	}

	public List<PutStockDetail> getPutStockDetails() {
		return this.putStockDetails;
	}

	public void setPutStockDetails(List<PutStockDetail> putStockDetails) {
		this.putStockDetails = putStockDetails;
	}

	public PutStockDetail addPutStockDetail(PutStockDetail putStockDetail) {
		getPutStockDetails().add(putStockDetail);
		putStockDetail.setDrugWarehouse(this);

		return putStockDetail;
	}

	public PutStockDetail removePutStockDetail(PutStockDetail putStockDetail) {
		getPutStockDetails().remove(putStockDetail);
		putStockDetail.setDrugWarehouse(null);

		return putStockDetail;
	}

}