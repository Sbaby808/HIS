package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the OUTPATIENT_PAY database table.
 * 
 */
@Entity
@Table(name="OUTPATIENT_PAY")
@NamedQuery(name="OutpatientPay.findAll", query="SELECT o FROM OutpatientPay o")
public class OutpatientPay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PAY_ID")
	private String payId;

	@Column(name="ACT_STATUS")
	private String actStatus;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name="OUT_PAY_TIME")
	private Date outPayTime;

	@Column(name="PAY_AMOUNT")
	private BigDecimal payAmount;

	@Column(name="PAY_TYPE")
	private String payType;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional one-to-one association to OutpatientRegistration
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="REG_ID")
	@JSONField(serialize = false)
	private OutpatientRegistration outpatientRegistration;

	public OutpatientPay() {
	}

	public String getPayId() {
		return this.payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public Date getOutPayTime() {
		return this.outPayTime;
	}

	public void setOutPayTime(Date outPayTime) {
		this.outPayTime = outPayTime;
	}

	public BigDecimal getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public OutpatientRegistration getOutpatientRegistration() {
		return this.outpatientRegistration;
	}

	public void setOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		this.outpatientRegistration = outpatientRegistration;
	}

}