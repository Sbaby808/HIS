package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the OPER_PAY_RECORD database table.
 * 
 */
@Entity
@Table(name="OPER_PAY_RECORD")
@NamedQuery(name="OperPayRecord.findAll", query="SELECT o FROM OperPayRecord o")
public class OperPayRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OPER_JF_ID")
	private String operJfId;

	@Temporal(TemporalType.DATE)
	@Column(name="OPER_JF_TIME")
	private Date operJfTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to MedicalCard
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private MedicalCard medicalCard;

	//bi-directional many-to-one association to OperationPay
	@ManyToOne
	@JoinColumn(name="OPER_PAY_ID")
	private OperationPay operationPay;

	public OperPayRecord() {
	}

	public String getOperJfId() {
		return this.operJfId;
	}

	public void setOperJfId(String operJfId) {
		this.operJfId = operJfId;
	}

	public Date getOperJfTime() {
		return this.operJfTime;
	}

	public void setOperJfTime(Date operJfTime) {
		this.operJfTime = operJfTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public MedicalCard getMedicalCard() {
		return this.medicalCard;
	}

	public void setMedicalCard(MedicalCard medicalCard) {
		this.medicalCard = medicalCard;
	}

	public OperationPay getOperationPay() {
		return this.operationPay;
	}

	public void setOperationPay(OperationPay operationPay) {
		this.operationPay = operationPay;
	}

}