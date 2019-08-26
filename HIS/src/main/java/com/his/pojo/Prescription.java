package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PRESCRIPTION database table.
 * 
 */
@Entity
@NamedQuery(name="Prescription.findAll", query="SELECT p FROM Prescription p")
public class Prescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRESCRIPTION_ID")
	private String prescriptionId;

	@Column(name="INJ_ID")
	private String injId;

//	@Column(name="OUT_PRE_PAY_ID")
//	private String outPrePayId;

//	@Temporal(TemporalType.DATE)
	@JSONField(format = "yyyy-MM-dd")
	@Column(name="PRES_TIME")
	private Date presTime;

	@Column(name="PRES_WAY")
	private String presWay;

	@Column(name="TOTAL_MONEY")
	private BigDecimal totalMoney;

	//bi-directional many-to-one association to BackMedicine
	@OneToMany(mappedBy="prescription")
	private List<BackMedicine> backMedicines;

	//bi-directional many-to-one association to OutPreItem
	@OneToMany(mappedBy="prescription")
	private List<OutPreItem> outPreItems;

	//bi-directional one-to-one association to History
	@OneToOne
	@JSONField(serialize = false)
	@JoinColumn(name="HISTORY_ID")
	private History history;

	//bi-directional one-to-one association to OutPrePay
	@OneToOne(mappedBy="prescription")
	@JoinColumn(name="OUT_PRE_PAY_ID")
	private OutPrePay outPrePay;

	//bi-directional one-to-one association to UseDrugRecord
	@OneToOne(mappedBy="prescription")
	private UseDrugRecord useDrugRecord;

	public Prescription() {
	}

	public String getPrescriptionId() {
		return this.prescriptionId;
	}

	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getInjId() {
		return this.injId;
	}

	public void setInjId(String injId) {
		this.injId = injId;
	}

//	public String getOutPrePayId() {
//		return this.outPrePayId;
//	}
//
//	public void setOutPrePayId(String outPrePayId) {
//		this.outPrePayId = outPrePayId;
//	}

	public Date getPresTime() {
		return this.presTime;
	}

	public void setPresTime(Date presTime) {
		this.presTime = presTime;
	}

	public String getPresWay() {
		return this.presWay;
	}

	public void setPresWay(String presWay) {
		this.presWay = presWay;
	}

	public BigDecimal getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public List<BackMedicine> getBackMedicines() {
		return this.backMedicines;
	}

	public void setBackMedicines(List<BackMedicine> backMedicines) {
		this.backMedicines = backMedicines;
	}

	public BackMedicine addBackMedicine(BackMedicine backMedicine) {
		getBackMedicines().add(backMedicine);
		backMedicine.setPrescription(this);

		return backMedicine;
	}

	public BackMedicine removeBackMedicine(BackMedicine backMedicine) {
		getBackMedicines().remove(backMedicine);
		backMedicine.setPrescription(null);

		return backMedicine;
	}

	public List<OutPreItem> getOutPreItems() {
		return this.outPreItems;
	}

	public void setOutPreItems(List<OutPreItem> outPreItems) {
		this.outPreItems = outPreItems;
	}

	public OutPreItem addOutPreItem(OutPreItem outPreItem) {
		getOutPreItems().add(outPreItem);
		outPreItem.setPrescription(this);

		return outPreItem;
	}

	public OutPreItem removeOutPreItem(OutPreItem outPreItem) {
		getOutPreItems().remove(outPreItem);
		outPreItem.setPrescription(null);

		return outPreItem;
	}

	public History getHistory() {
		return this.history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public OutPrePay getOutPrePay() {
		return this.outPrePay;
	}

	public void setOutPrePay(OutPrePay outPrePay) {
		this.outPrePay = outPrePay;
	}

	public UseDrugRecord getUseDrugRecord() {
		return this.useDrugRecord;
	}

	public void setUseDrugRecord(UseDrugRecord useDrugRecord) {
		this.useDrugRecord = useDrugRecord;
	}

}