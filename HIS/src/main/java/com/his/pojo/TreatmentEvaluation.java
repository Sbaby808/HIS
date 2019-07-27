package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TREATMENT_EVALUATION database table.
 * 
 */
@Entity
@Table(name="TREATMENT_EVALUATION")
@NamedQuery(name="TreatmentEvaluation.findAll", query="SELECT t FROM TreatmentEvaluation t")
public class TreatmentEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OUTEVA_ID")
	private String outevaId;

	@Column(name="OUTEVA_DESC")
	private String outevaDesc;

	@Column(name="OUTEVA_RANK")
	private String outevaRank;

	@Temporal(TemporalType.DATE)
	@Column(name="OUTEVA_TIME")
	private Date outevaTime;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="treatmentEvaluation")
	private List<History> histories;

	//bi-directional many-to-one association to History
	@ManyToOne
	@JoinColumn(name="HISTORY_ID")
	private History history;

	public TreatmentEvaluation() {
	}

	public String getOutevaId() {
		return this.outevaId;
	}

	public void setOutevaId(String outevaId) {
		this.outevaId = outevaId;
	}

	public String getOutevaDesc() {
		return this.outevaDesc;
	}

	public void setOutevaDesc(String outevaDesc) {
		this.outevaDesc = outevaDesc;
	}

	public String getOutevaRank() {
		return this.outevaRank;
	}

	public void setOutevaRank(String outevaRank) {
		this.outevaRank = outevaRank;
	}

	public Date getOutevaTime() {
		return this.outevaTime;
	}

	public void setOutevaTime(Date outevaTime) {
		this.outevaTime = outevaTime;
	}

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setTreatmentEvaluation(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setTreatmentEvaluation(null);

		return history;
	}

	public History getHistory() {
		return this.history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

}