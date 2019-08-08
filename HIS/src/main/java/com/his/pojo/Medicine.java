package com.his.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


/**
 * The persistent class for the MEDICINE database table.
 * 
 */
@Entity
@NamedQuery(name="Medicine.findAll", query="SELECT m FROM Medicine m")
public class Medicine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MEDICINE_ID")
	private String medicineId;

	@Column(name="MEDICINE_NAME")
	private BigDecimal medicineName;

	//bi-directional many-to-one association to BackDetail
	@OneToMany(mappedBy="medicine")
	@JSONField(serialize=false)
	private List<BackDetail> backDetails;

	//bi-directional many-to-one association to ChangeDrugDetail
	@OneToMany(mappedBy="medicine")
	@JSONField(serialize=false)
	private List<ChangeDrugDetail> changeDrugDetails;

	//bi-directional many-to-one association to DamagedDrugDetail
	@OneToMany(mappedBy="medicine")
	@JSONField(serialize=false)
	private List<DamagedDrugDetail> damagedDrugDetails;

	//bi-directional many-to-one association to InjectionDetail
	@OneToMany(mappedBy="medicine")
	@JSONField(serialize=false)
	private List<InjectionDetail> injectionDetails;

	//bi-directional many-to-one association to Dept
	@ManyToOne
	@JoinColumn(name="DEPT_ID")
	private Dept dept;

	//bi-directional many-to-one association to DrugWarehouse
	@ManyToOne
	@JoinColumn(name="PCKC_ID")
	private DrugWarehouse drugWarehouse;

	//bi-directional many-to-one association to OpeDrugDetail
	@OneToMany(mappedBy="medicine")
	@JSONField(serialize=false)
	private List<OpeDrugDetail> opeDrugDetails;

	//bi-directional many-to-one association to PhaInDetail
	@OneToMany(mappedBy="medicine")
	@JSONField(serialize=false)
	private List<PhaInDetail> phaInDetails;

	public Medicine() {
	}

	public String getMedicineId() {
		return this.medicineId;
	}

	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}



	public BigDecimal getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(BigDecimal medicineName) {
		this.medicineName = medicineName;
	}

	public List<BackDetail> getBackDetails() {
		return this.backDetails;
	}

	public void setBackDetails(List<BackDetail> backDetails) {
		this.backDetails = backDetails;
	}

	public BackDetail addBackDetail(BackDetail backDetail) {
		getBackDetails().add(backDetail);
		backDetail.setMedicine(this);

		return backDetail;
	}

	public BackDetail removeBackDetail(BackDetail backDetail) {
		getBackDetails().remove(backDetail);
		backDetail.setMedicine(null);

		return backDetail;
	}

	public List<ChangeDrugDetail> getChangeDrugDetails() {
		return this.changeDrugDetails;
	}

	public void setChangeDrugDetails(List<ChangeDrugDetail> changeDrugDetails) {
		this.changeDrugDetails = changeDrugDetails;
	}

	public ChangeDrugDetail addChangeDrugDetail(ChangeDrugDetail changeDrugDetail) {
		getChangeDrugDetails().add(changeDrugDetail);
		changeDrugDetail.setMedicine(this);

		return changeDrugDetail;
	}

	public ChangeDrugDetail removeChangeDrugDetail(ChangeDrugDetail changeDrugDetail) {
		getChangeDrugDetails().remove(changeDrugDetail);
		changeDrugDetail.setMedicine(null);

		return changeDrugDetail;
	}

	public List<DamagedDrugDetail> getDamagedDrugDetails() {
		return this.damagedDrugDetails;
	}

	public void setDamagedDrugDetails(List<DamagedDrugDetail> damagedDrugDetails) {
		this.damagedDrugDetails = damagedDrugDetails;
	}

	public DamagedDrugDetail addDamagedDrugDetail(DamagedDrugDetail damagedDrugDetail) {
		getDamagedDrugDetails().add(damagedDrugDetail);
		damagedDrugDetail.setMedicine(this);

		return damagedDrugDetail;
	}

	public DamagedDrugDetail removeDamagedDrugDetail(DamagedDrugDetail damagedDrugDetail) {
		getDamagedDrugDetails().remove(damagedDrugDetail);
		damagedDrugDetail.setMedicine(null);

		return damagedDrugDetail;
	}

	public List<InjectionDetail> getInjectionDetails() {
		return this.injectionDetails;
	}

	public void setInjectionDetails(List<InjectionDetail> injectionDetails) {
		this.injectionDetails = injectionDetails;
	}

	public InjectionDetail addInjectionDetail(InjectionDetail injectionDetail) {
		getInjectionDetails().add(injectionDetail);
		injectionDetail.setMedicine(this);

		return injectionDetail;
	}

	public InjectionDetail removeInjectionDetail(InjectionDetail injectionDetail) {
		getInjectionDetails().remove(injectionDetail);
		injectionDetail.setMedicine(null);

		return injectionDetail;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public DrugWarehouse getDrugWarehouse() {
		return this.drugWarehouse;
	}

	public void setDrugWarehouse(DrugWarehouse drugWarehouse) {
		this.drugWarehouse = drugWarehouse;
	}

	public List<OpeDrugDetail> getOpeDrugDetails() {
		return this.opeDrugDetails;
	}

	public void setOpeDrugDetails(List<OpeDrugDetail> opeDrugDetails) {
		this.opeDrugDetails = opeDrugDetails;
	}

	public OpeDrugDetail addOpeDrugDetail(OpeDrugDetail opeDrugDetail) {
		getOpeDrugDetails().add(opeDrugDetail);
		opeDrugDetail.setMedicine(this);

		return opeDrugDetail;
	}

	public OpeDrugDetail removeOpeDrugDetail(OpeDrugDetail opeDrugDetail) {
		getOpeDrugDetails().remove(opeDrugDetail);
		opeDrugDetail.setMedicine(null);

		return opeDrugDetail;
	}

	public List<PhaInDetail> getPhaInDetails() {
		return this.phaInDetails;
	}

	public void setPhaInDetails(List<PhaInDetail> phaInDetails) {
		this.phaInDetails = phaInDetails;
	}

	public PhaInDetail addPhaInDetail(PhaInDetail phaInDetail) {
		getPhaInDetails().add(phaInDetail);
		phaInDetail.setMedicine(this);

		return phaInDetail;
	}

	public PhaInDetail removePhaInDetail(PhaInDetail phaInDetail) {
		getPhaInDetails().remove(phaInDetail);
		phaInDetail.setMedicine(null);

		return phaInDetail;
	}

}