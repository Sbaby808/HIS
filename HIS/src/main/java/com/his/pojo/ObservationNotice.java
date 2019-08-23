package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * The persistent class for the OBSERVATION_NOTICE database table.
 * 
 */
@Entity
@Table(name="OBSERVATION_NOTICE")
@NamedQuery(name="ObservationNotice.findAll", query="SELECT o FROM ObservationNotice o")
public class ObservationNotice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OBSERVA_ID")
	private String observaId;

	@Column(name="LGSCY_ID")
	private String lgscyId;

	@Column(name="OBSER_NOTE")
	private String obserNote;

	@JSONField(format="yyyy-MM-dd")
	@Column(name="OBSER_TIME")
	private Date obserTime;
	
	@JSONField(format="yyyy-MM-dd")
	@Column(name="OBS_NOTE_START_TIME")
	private Date obsNoteStartTime;
	
	@JSONField(format="yyyy-MM-dd")
	@Column(name="OBS_NOTE_END_TIME")
	private Date obsNoteEndTime;

	//bi-directional one-to-one association to SolveScheme
	@OneToOne
	@JoinColumn(name="SCHE_ID")
	private SolveScheme solveScheme;

	//bi-directional one-to-one association to ObservationOut
	@OneToOne(mappedBy="observationNotice")
	private ObservationOut observationOut;

	public ObservationNotice() {
	}

	public Date getObsNoteStartTime() {
		return obsNoteStartTime;
	}

	public void setObsNoteStartTime(Date obsNoteStartTime) {
		this.obsNoteStartTime = obsNoteStartTime;
	}

	public Date getObsNoteEndTime() {
		return obsNoteEndTime;
	}

	public void setObsNoteEndTime(Date obsNoteEndTime) {
		this.obsNoteEndTime = obsNoteEndTime;
	}

	public String getObservaId() {
		return this.observaId;
	}

	public void setObservaId(String observaId) {
		this.observaId = observaId;
	}

	public String getLgscyId() {
		return this.lgscyId;
	}

	public void setLgscyId(String lgscyId) {
		this.lgscyId = lgscyId;
	}

	public String getObserNote() {
		return this.obserNote;
	}

	public void setObserNote(String obserNote) {
		this.obserNote = obserNote;
	}

	public Date getObserTime() {
		return this.obserTime;
	}

	public void setObserTime(Date obserTime) {
		this.obserTime = obserTime;
	}

	public SolveScheme getSolveScheme() {
		return this.solveScheme;
	}

	public void setSolveScheme(SolveScheme solveScheme) {
		this.solveScheme = solveScheme;
	}

	public ObservationOut getObservationOut() {
		return this.observationOut;
	}

	public void setObservationOut(ObservationOut observationOut) {
		this.observationOut = observationOut;
	}

}