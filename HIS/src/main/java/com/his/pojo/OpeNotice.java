package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the OPE_NOTICE database table.
 * 
 */
@Entity
@Table(name="OPE_NOTICE")
@NamedQuery(name="OpeNotice.findAll", query="SELECT o FROM OpeNotice o")
public class OpeNotice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MOPER_ID")
	private String moperId;

	@Column(name="MOPER_COMMENT")
	private String moperComment;

	@Temporal(TemporalType.DATE)
	@Column(name="MOPER_TIME")
	private Date moperTime;

	//bi-directional many-to-one association to OperationPay
	@ManyToOne
	@JoinColumn(name="OPER_PAY_ID")
	private OperationPay operationPay;

	//bi-directional many-to-one association to SolveScheme
	@ManyToOne
	@JoinColumn(name="SCHE_ID")
	private SolveScheme solveScheme;

	public OpeNotice() {
	}

	public String getMoperId() {
		return this.moperId;
	}

	public void setMoperId(String moperId) {
		this.moperId = moperId;
	}

	public String getMoperComment() {
		return this.moperComment;
	}

	public void setMoperComment(String moperComment) {
		this.moperComment = moperComment;
	}

	public Date getMoperTime() {
		return this.moperTime;
	}

	public void setMoperTime(Date moperTime) {
		this.moperTime = moperTime;
	}

	public OperationPay getOperationPay() {
		return this.operationPay;
	}

	public void setOperationPay(OperationPay operationPay) {
		this.operationPay = operationPay;
	}

	public SolveScheme getSolveScheme() {
		return this.solveScheme;
	}

	public void setSolveScheme(SolveScheme solveScheme) {
		this.solveScheme = solveScheme;
	}

}