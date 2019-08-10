package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CHECK_PAY database table.
 * 
 */
@Entity
@Table(name="CHECK_PAY")
@NamedQuery(name="CheckPay.findAll", query="SELECT c FROM CheckPay c")
public class CheckPay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CHECK_ID")
	private String checkId;

	@Column(name="CHECK_FORM_PATH")
	private String checkFormPath;

	@Column(name="CHECK_PAY_DESC")
	private String checkPayDesc;

	@Column(name="CHECK_PAY_MONEY")
	private BigDecimal checkPayMoney;

	@Column(name="CHECK_PAY_NAME")
	private String checkPayName;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_PAY_TIME")
	private Date checkPayTime;

	//bi-directional many-to-one association to CheckNoticeForm
	@OneToMany(mappedBy="checkPay")
	@JSONField(serialize=false)
	private List<CheckNoticeForm> checkNoticeForms;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to CheckPayRecord
	@OneToMany(mappedBy="checkPay")
	@JSONField(serialize=false)
	private List<CheckPayRecord> checkPayRecords;

	public CheckPay() {
	}

	public String getCheckId() {
		return this.checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getCheckFormPath() {
		return this.checkFormPath;
	}

	public void setCheckFormPath(String checkFormPath) {
		this.checkFormPath = checkFormPath;
	}

	public String getCheckPayDesc() {
		return this.checkPayDesc;
	}

	public void setCheckPayDesc(String checkPayDesc) {
		this.checkPayDesc = checkPayDesc;
	}

	public BigDecimal getCheckPayMoney() {
		return this.checkPayMoney;
	}

	public void setCheckPayMoney(BigDecimal checkPayMoney) {
		this.checkPayMoney = checkPayMoney;
	}

	public String getCheckPayName() {
		return this.checkPayName;
	}

	public void setCheckPayName(String checkPayName) {
		this.checkPayName = checkPayName;
	}

	public Date getCheckPayTime() {
		return this.checkPayTime;
	}

	public void setCheckPayTime(Date checkPayTime) {
		this.checkPayTime = checkPayTime;
	}

	public List<CheckNoticeForm> getCheckNoticeForms() {
		return this.checkNoticeForms;
	}

	public void setCheckNoticeForms(List<CheckNoticeForm> checkNoticeForms) {
		this.checkNoticeForms = checkNoticeForms;
	}

	public CheckNoticeForm addCheckNoticeForm(CheckNoticeForm checkNoticeForm) {
		getCheckNoticeForms().add(checkNoticeForm);
		checkNoticeForm.setCheckPay(this);

		return checkNoticeForm;
	}

	public CheckNoticeForm removeCheckNoticeForm(CheckNoticeForm checkNoticeForm) {
		getCheckNoticeForms().remove(checkNoticeForm);
		checkNoticeForm.setCheckPay(null);

		return checkNoticeForm;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<CheckPayRecord> getCheckPayRecords() {
		return this.checkPayRecords;
	}

	public void setCheckPayRecords(List<CheckPayRecord> checkPayRecords) {
		this.checkPayRecords = checkPayRecords;
	}

	public CheckPayRecord addCheckPayRecord(CheckPayRecord checkPayRecord) {
		getCheckPayRecords().add(checkPayRecord);
		checkPayRecord.setCheckPay(this);

		return checkPayRecord;
	}

	public CheckPayRecord removeCheckPayRecord(CheckPayRecord checkPayRecord) {
		getCheckPayRecords().remove(checkPayRecord);
		checkPayRecord.setCheckPay(null);

		return checkPayRecord;
	}

}