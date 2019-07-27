package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CHECK_PAY_RECORD database table.
 * 
 */
@Entity
@Table(name="CHECK_PAY_RECORD")
@NamedQuery(name="CheckPayRecord.findAll", query="SELECT c FROM CheckPayRecord c")
public class CheckPayRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CHECK_JF_ID")
	private String checkJfId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_JF_TIME")
	private Date checkJfTime;

	//bi-directional many-to-one association to CheckPay
	@ManyToOne
	@JoinColumn(name="CHECK_ID")
	private CheckPay checkPay;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to MedicalCard
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private MedicalCard medicalCard;

	public CheckPayRecord() {
	}

	public String getCheckJfId() {
		return this.checkJfId;
	}

	public void setCheckJfId(String checkJfId) {
		this.checkJfId = checkJfId;
	}

	public Date getCheckJfTime() {
		return this.checkJfTime;
	}

	public void setCheckJfTime(Date checkJfTime) {
		this.checkJfTime = checkJfTime;
	}

	public CheckPay getCheckPay() {
		return this.checkPay;
	}

	public void setCheckPay(CheckPay checkPay) {
		this.checkPay = checkPay;
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

}