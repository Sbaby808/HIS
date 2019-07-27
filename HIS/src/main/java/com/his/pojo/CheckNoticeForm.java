package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CHECK_NOTICE_FORM database table.
 * 
 */
@Entity
@Table(name="CHECK_NOTICE_FORM")
@NamedQuery(name="CheckNoticeForm.findAll", query="SELECT c FROM CheckNoticeForm c")
public class CheckNoticeForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MCHECK_ID")
	private String mcheckId;

	@Column(name="MCHECK_COMMENT")
	private String mcheckComment;

	@Temporal(TemporalType.DATE)
	@Column(name="MCHECK_TIME")
	private Date mcheckTime;

	//bi-directional many-to-one association to CheckPay
	@ManyToOne
	@JoinColumn(name="CHECK_ID")
	private CheckPay checkPay;

	//bi-directional many-to-one association to SolveScheme
	@ManyToOne
	@JoinColumn(name="SCHE_ID")
	private SolveScheme solveScheme;

	public CheckNoticeForm() {
	}

	public String getMcheckId() {
		return this.mcheckId;
	}

	public void setMcheckId(String mcheckId) {
		this.mcheckId = mcheckId;
	}

	public String getMcheckComment() {
		return this.mcheckComment;
	}

	public void setMcheckComment(String mcheckComment) {
		this.mcheckComment = mcheckComment;
	}

	public Date getMcheckTime() {
		return this.mcheckTime;
	}

	public void setMcheckTime(Date mcheckTime) {
		this.mcheckTime = mcheckTime;
	}

	public CheckPay getCheckPay() {
		return this.checkPay;
	}

	public void setCheckPay(CheckPay checkPay) {
		this.checkPay = checkPay;
	}

	public SolveScheme getSolveScheme() {
		return this.solveScheme;
	}

	public void setSolveScheme(SolveScheme solveScheme) {
		this.solveScheme = solveScheme;
	}

}