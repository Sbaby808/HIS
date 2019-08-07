package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OPERATION_PAY database table.
 * 
 */
@Entity
@Table(name="OPERATION_PAY")
@NamedQuery(name="OperationPay.findAll", query="SELECT o FROM OperationPay o")
@JsonIgnoreProperties(value= {"operPayRecords","opeNotices"})
public class OperationPay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OPER_PAY_ID")
	private String operPayId;

	@Column(name="OPER_PAY_DESC")
	private String operPayDesc;

	@Column(name="OPER_PAY_NAME")
	private String operPayName;

	@Column(name="OPER_PAY_PRICE")
	private BigDecimal operPayPrice;

	@Temporal(TemporalType.DATE)
	@Column(name="OPER_PAY_TIME")
	private Date operPayTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to OperPayRecord
	@OneToMany(mappedBy="operationPay")
	@JSONField(serialize=false)
	private List<OperPayRecord> operPayRecords;

	//bi-directional many-to-one association to OpeNotice
	@OneToMany(mappedBy="operationPay")
	@JSONField(serialize=false)
	private List<OpeNotice> opeNotices;

	public OperationPay() {
	}
	/**
	 * 只有手术名和金额的构造方法
	 */
    public OperationPay(String operPayName,BigDecimal operPayPrice) {
    	this.operPayName=operPayName;
    	this.operPayPrice=operPayPrice;
	}
	public String getOperPayId() {
		return this.operPayId;
	}

	public void setOperPayId(String operPayId) {
		this.operPayId = operPayId;
	}

	public String getOperPayDesc() {
		return this.operPayDesc;
	}

	public void setOperPayDesc(String operPayDesc) {
		this.operPayDesc = operPayDesc;
	}

	public String getOperPayName() {
		return this.operPayName;
	}

	public void setOperPayName(String operPayName) {
		this.operPayName = operPayName;
	}

	public BigDecimal getOperPayPrice() {
		return this.operPayPrice;
	}

	public void setOperPayPrice(BigDecimal operPayPrice) {
		this.operPayPrice = operPayPrice;
	}

	public Date getOperPayTime() {
		return this.operPayTime;
	}

	public void setOperPayTime(Date operPayTime) {
		this.operPayTime = operPayTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<OperPayRecord> getOperPayRecords() {
		return this.operPayRecords;
	}

	public void setOperPayRecords(List<OperPayRecord> operPayRecords) {
		this.operPayRecords = operPayRecords;
	}

	public OperPayRecord addOperPayRecord(OperPayRecord operPayRecord) {
		getOperPayRecords().add(operPayRecord);
		operPayRecord.setOperationPay(this);

		return operPayRecord;
	}

	public OperPayRecord removeOperPayRecord(OperPayRecord operPayRecord) {
		getOperPayRecords().remove(operPayRecord);
		operPayRecord.setOperationPay(null);

		return operPayRecord;
	}

	public List<OpeNotice> getOpeNotices() {
		return this.opeNotices;
	}

	public void setOpeNotices(List<OpeNotice> opeNotices) {
		this.opeNotices = opeNotices;
	}

	public OpeNotice addOpeNotice(OpeNotice opeNotice) {
		getOpeNotices().add(opeNotice);
		opeNotice.setOperationPay(this);

		return opeNotice;
	}

	public OpeNotice removeOpeNotice(OpeNotice opeNotice) {
		getOpeNotices().remove(opeNotice);
		opeNotice.setOperationPay(null);

		return opeNotice;
	}

}