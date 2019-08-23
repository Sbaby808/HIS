package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

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

	@JSONField(format="yyyy-MM-dd")
	@Column(name="MCHECK_TIME")
	private Date mcheckTime;
	
	@JSONField(format="yyyy-MM-dd")
	@Column(name="CHECK_START_TIME")
	private Date checkStartTime;
	
	@JSONField(format="yyyy-MM-dd")
	@Column(name="CHECK_END_TIME")
	private Date checkEndTime;

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

	public Date getCheckStartTime() {
		return checkStartTime;
	}

	public void setCheckStartTime(Date checkStartTime) {
		this.checkStartTime = checkStartTime;
	}

	public Date getCheckEndTime() {
		return checkEndTime;
	}

	public void setCheckEndTime(Date checkEndTime) {
		this.checkEndTime = checkEndTime;
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