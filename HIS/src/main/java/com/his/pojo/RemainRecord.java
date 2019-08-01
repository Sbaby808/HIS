package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the REMAIN_RECORD database table.
 * 
 */
@Entity
@Table(name="REMAIN_RECORD")
@NamedQuery(name="RemainRecord.findAll", query="SELECT r FROM RemainRecord r")
public class RemainRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REMAIN_ID")
	private String remainId;

	@Column(name="JZCW_ID")
	private String jzcwId;

	@Column(name="REMAIN_STATE")
	private String remainState;

	@Temporal(TemporalType.DATE)
	@Column(name="REMAIN_TIME")
	private Date remainTime;

	//bi-directional many-to-one association to MedicalCard
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private MedicalCard medicalCard;

	//bi-directional one-to-one association to ObservationBed
	@OneToOne(mappedBy="remainRecord")
	private ObservationBed observationBed;

	public RemainRecord() {
	}

	public String getRemainId() {
		return this.remainId;
	}

	public void setRemainId(String remainId) {
		this.remainId = remainId;
	}

	public String getJzcwId() {
		return this.jzcwId;
	}

	public void setJzcwId(String jzcwId) {
		this.jzcwId = jzcwId;
	}

	public String getRemainState() {
		return this.remainState;
	}

	public void setRemainState(String remainState) {
		this.remainState = remainState;
	}

	public Date getRemainTime() {
		return this.remainTime;
	}

	public void setRemainTime(Date remainTime) {
		this.remainTime = remainTime;
	}

	public MedicalCard getMedicalCard() {
		return this.medicalCard;
	}

	public void setMedicalCard(MedicalCard medicalCard) {
		this.medicalCard = medicalCard;
	}

	public ObservationBed getObservationBed() {
		return this.observationBed;
	}

	public void setObservationBed(ObservationBed observationBed) {
		this.observationBed = observationBed;
	}

}