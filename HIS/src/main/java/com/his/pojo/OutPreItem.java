package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;


/**
 * The persistent class for the OUT_PRE_ITEM database table.
 * 
 */
@Entity
@Table(name="OUT_PRE_ITEM")
@NamedQuery(name="OutPreItem.findAll", query="SELECT o FROM OutPreItem o")
public class OutPreItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OutPreItemPK id;

	@Column(name="OUT_ITEM_PAY")
	private BigDecimal outItemPay;

	@Column(name="OUT_MED_NUM")
	private BigDecimal outMedNum;
	
	@Column(name="USED_NUM")
	private int usedNum;

	@Column(name="OUT_PRE_TIME")
	private String outPreTime;

	@Column(name="OUT_PRE_UNIT")
	private String outPreUnit;

	@Column(name="OUT_PRE_WAY")
	private String outPreWay;

	//bi-directional many-to-one association to DrugInformation
	@ManyToOne
	@JoinColumn(name="YP_ID", insertable=false, updatable=false)
	private DrugInformation drugInformation;

	//bi-directional many-to-one association to Prescription
	@ManyToOne(cascade = CascadeType.ALL)
	@JSONField(serialize = false)
	@JoinColumn(name="PRESCRIPTION_ID", insertable=false, updatable=false)
	private Prescription prescription;

	public OutPreItem() {
	}

	public int getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(int usedNum) {
		this.usedNum = usedNum;
	}

	public OutPreItemPK getId() {
		return this.id;
	}

	public void setId(OutPreItemPK id) {
		this.id = id;
	}

	public BigDecimal getOutItemPay() {
		return this.outItemPay;
	}

	public void setOutItemPay(BigDecimal outItemPay) {
		this.outItemPay = outItemPay;
	}

	public BigDecimal getOutMedNum() {
		return this.outMedNum;
	}

	public void setOutMedNum(BigDecimal outMedNum) {
		this.outMedNum = outMedNum;
	}

	public String getOutPreTime() {
		return this.outPreTime;
	}

	public void setOutPreTime(String outPreTime) {
		this.outPreTime = outPreTime;
	}

	public String getOutPreUnit() {
		return this.outPreUnit;
	}

	public void setOutPreUnit(String outPreUnit) {
		this.outPreUnit = outPreUnit;
	}

	public String getOutPreWay() {
		return this.outPreWay;
	}

	public void setOutPreWay(String outPreWay) {
		this.outPreWay = outPreWay;
	}

	public DrugInformation getDrugInformation() {
		return this.drugInformation;
	}

	public void setDrugInformation(DrugInformation drugInformation) {
		this.drugInformation = drugInformation;
	}

	public Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

}