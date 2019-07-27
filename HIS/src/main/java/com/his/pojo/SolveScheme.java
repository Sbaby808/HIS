package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
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

	@Temporal(TemporalType.DATE)
	@Column(name="SCHE_END_TIME")
	private Date scheEndTime;

	@Temporal(TemporalType.DATE)
	@Column(name="SCHE_START_TIME")
	private Date scheStartTime;

	@Temporal(TemporalType.DATE)
	@Column(name="SCHE_TIME")
	private Date scheTime;

	//bi-directional many-to-one association to CheckNoticeForm
	@OneToMany(mappedBy="solveScheme")
	private List<CheckNoticeForm> checkNoticeForms;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="solveScheme")
	private List<History> histories;

	//bi-directional many-to-one association to HospitalNotice
	@OneToMany(mappedBy="solveScheme")
	private List<HospitalNotice> hospitalNotices;

	//bi-directional many-to-one association to ObservationNotice
	@OneToMany(mappedBy="solveScheme")
	private List<ObservationNotice> observationNotices;

	//bi-directional many-to-one association to OpeNotice
	@OneToMany(mappedBy="solveScheme")
	private List<OpeNotice> opeNotices;

	//bi-directional many-to-one association to OtherAdvice
	@OneToMany(mappedBy="solveScheme")
	private List<OtherAdvice> otherAdvices;

	//bi-directional many-to-one association to History
	@ManyToOne
	@JoinColumn(name="HISTORY_ID")
	private History history;

	//bi-directional many-to-one association to HospitalNotice
	@ManyToOne
	@JoinColumn(name="RYTZ_ID")
	private HospitalNotice hospitalNotice;

	//bi-directional many-to-one association to ObservationNotice
	@ManyToOne
	@JoinColumn(name="OBSERVA_ID")
	private ObservationNotice observationNotice;

	public SolveScheme() {
	}

	public String getScheId() {
		return this.scheId;
	}

	public void setScheId(String scheId) {
		this.scheId = scheId;
	}

	public Date getScheEndTime() {
		return this.scheEndTime;
	}

	public void setScheEndTime(Date scheEndTime) {
		this.scheEndTime = scheEndTime;
	}

	public Date getScheStartTime() {
		return this.scheStartTime;
	}

	public void setScheStartTime(Date scheStartTime) {
		this.scheStartTime = scheStartTime;
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

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setSolveScheme(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setSolveScheme(null);

		return history;
	}

	public List<HospitalNotice> getHospitalNotices() {
		return this.hospitalNotices;
	}

	public void setHospitalNotices(List<HospitalNotice> hospitalNotices) {
		this.hospitalNotices = hospitalNotices;
	}

	public HospitalNotice addHospitalNotice(HospitalNotice hospitalNotice) {
		getHospitalNotices().add(hospitalNotice);
		hospitalNotice.setSolveScheme(this);

		return hospitalNotice;
	}

	public HospitalNotice removeHospitalNotice(HospitalNotice hospitalNotice) {
		getHospitalNotices().remove(hospitalNotice);
		hospitalNotice.setSolveScheme(null);

		return hospitalNotice;
	}

	public List<ObservationNotice> getObservationNotices() {
		return this.observationNotices;
	}

	public void setObservationNotices(List<ObservationNotice> observationNotices) {
		this.observationNotices = observationNotices;
	}

	public ObservationNotice addObservationNotice(ObservationNotice observationNotice) {
		getObservationNotices().add(observationNotice);
		observationNotice.setSolveScheme(this);

		return observationNotice;
	}

	public ObservationNotice removeObservationNotice(ObservationNotice observationNotice) {
		getObservationNotices().remove(observationNotice);
		observationNotice.setSolveScheme(null);

		return observationNotice;
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

	public History getHistory() {
		return this.history;
	}

	public void setHistory(History history) {
		this.history = history;
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

}