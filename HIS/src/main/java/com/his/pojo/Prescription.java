package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
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

	@Temporal(TemporalType.DATE)
	@Column(name="PRES_TIME")
	private Date presTime;

	@Column(name="PRES_WAY")
	private String presWay;

	@Column(name="TOTAL_MONEY")
	private BigDecimal totalMoney;

	//bi-directional many-to-one association to BackMedicine
	@OneToMany(mappedBy="prescription")
	private List<BackMedicine> backMedicines;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="prescription")
	private List<History> histories;

	//bi-directional many-to-one association to OutPreItem
	@OneToMany(mappedBy="prescription")
	private List<OutPreItem> outPreItems;

	//bi-directional many-to-one association to OutPrePay
	@OneToMany(mappedBy="prescription")
	private List<OutPrePay> outPrePays;

	//bi-directional many-to-one association to History
	@ManyToOne
	@JoinColumn(name="HISTORY_ID")
	private History history;

	//bi-directional many-to-one association to OutPrePay
	@ManyToOne
	@JoinColumn(name="OUT_PRE_PAY_ID")
	private OutPrePay outPrePay;

	//bi-directional many-to-one association to UseDrugRecord
	@ManyToOne
	@JoinColumn(name="INJ_ID")
	private UseDrugRecord useDrugRecord;

	//bi-directional many-to-one association to UseDrugRecord
	@OneToMany(mappedBy="prescription")
	private List<UseDrugRecord> useDrugRecords;

	public Prescription() {
	}

	public String getPrescriptionId() {
		return this.prescriptionId;
	}

	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

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

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setPrescription(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setPrescription(null);

		return history;
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

	public List<OutPrePay> getOutPrePays() {
		return this.outPrePays;
	}

	public void setOutPrePays(List<OutPrePay> outPrePays) {
		this.outPrePays = outPrePays;
	}

	public OutPrePay addOutPrePay(OutPrePay outPrePay) {
		getOutPrePays().add(outPrePay);
		outPrePay.setPrescription(this);

		return outPrePay;
	}

	public OutPrePay removeOutPrePay(OutPrePay outPrePay) {
		getOutPrePays().remove(outPrePay);
		outPrePay.setPrescription(null);

		return outPrePay;
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

	public List<UseDrugRecord> getUseDrugRecords() {
		return this.useDrugRecords;
	}

	public void setUseDrugRecords(List<UseDrugRecord> useDrugRecords) {
		this.useDrugRecords = useDrugRecords;
	}

	public UseDrugRecord addUseDrugRecord(UseDrugRecord useDrugRecord) {
		getUseDrugRecords().add(useDrugRecord);
		useDrugRecord.setPrescription(this);

		return useDrugRecord;
	}

	public UseDrugRecord removeUseDrugRecord(UseDrugRecord useDrugRecord) {
		getUseDrugRecords().remove(useDrugRecord);
		useDrugRecord.setPrescription(null);

		return useDrugRecord;
	}

}