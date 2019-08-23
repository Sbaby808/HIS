package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SOLVE_SCHEME database table.
 * 
 */
@Entity
@Table(name="SOLVE_SCHEME")
@NamedQuery(name="SolveScheme.findAll", query="SELECT s FROM SolveScheme s")
public class SolveScheme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SCHE_ID")
	private String scheId;

	@Column(name="OBSERVA_ID")
	private String observaId;

	@Column(name="RYTZ_ID")
	private String rytzId;

	@JSONField(format = "yyyy-MM-dd")
	@Column(name="SCHE_TIME")
	private Date scheTime;

	//bi-directional many-to-one association to CheckNoticeForm
	@OneToMany(mappedBy="solveScheme")
	@JSONField(serialize=false)
	private List<CheckNoticeForm> checkNoticeForms;

	//bi-directional many-to-one association to OpeNotice
	@OneToMany(mappedBy="solveScheme")
	@JSONField(serialize=false)
	private List<OpeNotice> opeNotices;

	//bi-directional many-to-one association to OtherAdvice
	@OneToMany(mappedBy="solveScheme")
	@JSONField(serialize=false)
	private List<OtherAdvice> otherAdvices;

	//bi-directional one-to-one association to HospitalNotice
	@OneToOne(mappedBy="solveScheme")
	@JSONField(serialize=false)
	private HospitalNotice hospitalNotice;

	//bi-directional one-to-one association to ObservationNotice
	@OneToOne(mappedBy="solveScheme")
	private ObservationNotice observationNotice;

	//bi-directional one-to-one association to History
	@ManyToOne
	@JoinColumn(name="HISTORY_ID")
	private History history;

	public SolveScheme() {
	}

	public String getScheId() {
		return this.scheId;
	}

	public void setScheId(String scheId) {
		this.scheId = scheId;
	}

	public String getObservaId() {
		return this.observaId;
	}

	public void setObservaId(String observaId) {
		this.observaId = observaId;
	}

	public String getRytzId() {
		return this.rytzId;
	}

	public void setRytzId(String rytzId) {
		this.rytzId = rytzId;
	}

	public Date getScheTime() {
		return this.scheTime;
	}

	public void setScheTime(Date scheTime) {
		this.scheTime = scheTime;
	}

	public List<CheckNoticeForm> getCheckNoticeForms() {
		return this.checkNoticeForms;
	}

	public void setCheckNoticeForms(List<CheckNoticeForm> checkNoticeForms) {
		this.checkNoticeForms = checkNoticeForms;
	}

	public CheckNoticeForm addCheckNoticeForm(CheckNoticeForm checkNoticeForm) {
		getCheckNoticeForms().add(checkNoticeForm);
		checkNoticeForm.setSolveScheme(this);

		return checkNoticeForm;
	}

	public CheckNoticeForm removeCheckNoticeForm(CheckNoticeForm checkNoticeForm) {
		getCheckNoticeForms().remove(checkNoticeForm);
		checkNoticeForm.setSolveScheme(null);

		return checkNoticeForm;
	}

	public List<OpeNotice> getOpeNotices() {
		return this.opeNotices;
	}

	public void setOpeNotices(List<OpeNotice> opeNotices) {
		this.opeNotices = opeNotices;
	}

	public OpeNotice addOpeNotice(OpeNotice opeNotice) {
		getOpeNotices().add(opeNotice);
		opeNotice.setSolveScheme(this);

		return opeNotice;
	}

	public OpeNotice removeOpeNotice(OpeNotice opeNotice) {
		getOpeNotices().remove(opeNotice);
		opeNotice.setSolveScheme(null);

		return opeNotice;
	}

	public List<OtherAdvice> getOtherAdvices() {
		return this.otherAdvices;
	}

	public void setOtherAdvices(List<OtherAdvice> otherAdvices) {
		this.otherAdvices = otherAdvices;
	}

	public OtherAdvice addOtherAdvice(OtherAdvice otherAdvice) {
		getOtherAdvices().add(otherAdvice);
		otherAdvice.setSolveScheme(this);

		return otherAdvice;
	}

	public OtherAdvice removeOtherAdvice(OtherAdvice otherAdvice) {
		getOtherAdvices().remove(otherAdvice);
		otherAdvice.setSolveScheme(null);

		return otherAdvice;
	}

	public HospitalNotice getHospitalNotice() {
		return this.hospitalNotice;
	}

	public void setHospitalNotice(HospitalNotice hospitalNotice) {
		this.hospitalNotice = hospitalNotice;
	}

	public ObservationNotice getObservationNotice() {
		return this.observationNotice;
	}

	public void setObservationNotice(ObservationNotice observationNotice) {
		this.observationNotice = observationNotice;
	}

	public History getHistory() {
		return this.history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

}