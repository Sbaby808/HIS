package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	//bi-directional one-to-one association to History
	@OneToOne
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

	public History getHistory() {
		return this.history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

}