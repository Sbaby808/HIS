package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	@Column(name="OBSER_NOTE")
	private String obserNote;

	@Temporal(TemporalType.DATE)
	@Column(name="OBSER_TIME")
	private Date obserTime;

	//bi-directional many-to-one association to ObservationOut
	@ManyToOne
	@JoinColumn(name="LGSCY_ID")
	private ObservationOut observationOut;

	//bi-directional many-to-one association to SolveScheme
	@ManyToOne
	@JoinColumn(name="SCHE_ID")
	private SolveScheme solveScheme;

	//bi-directional many-to-one association to ObservationOut
	@OneToMany(mappedBy="observationNotice")
	private List<ObservationOut> observationOuts;

	//bi-directional many-to-one association to SolveScheme
	@OneToMany(mappedBy="observationNotice")
	private List<SolveScheme> solveSchemes;

	public ObservationNotice() {
	}

	public String getObservaId() {
		return this.observaId;
	}

	public void setObservaId(String observaId) {
		this.observaId = observaId;
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

	public ObservationOut getObservationOut() {
		return this.observationOut;
	}

	public void setObservationOut(ObservationOut observationOut) {
		this.observationOut = observationOut;
	}

	public SolveScheme getSolveScheme() {
		return this.solveScheme;
	}

	public void setSolveScheme(SolveScheme solveScheme) {
		this.solveScheme = solveScheme;
	}

	public List<ObservationOut> getObservationOuts() {
		return this.observationOuts;
	}

	public void setObservationOuts(List<ObservationOut> observationOuts) {
		this.observationOuts = observationOuts;
	}

	public ObservationOut addObservationOut(ObservationOut observationOut) {
		getObservationOuts().add(observationOut);
		observationOut.setObservationNotice(this);

		return observationOut;
	}

	public ObservationOut removeObservationOut(ObservationOut observationOut) {
		getObservationOuts().remove(observationOut);
		observationOut.setObservationNotice(null);

		return observationOut;
	}

	public List<SolveScheme> getSolveSchemes() {
		return this.solveSchemes;
	}

	public void setSolveSchemes(List<SolveScheme> solveSchemes) {
		this.solveSchemes = solveSchemes;
	}

	public SolveScheme addSolveScheme(SolveScheme solveScheme) {
		getSolveSchemes().add(solveScheme);
		solveScheme.setObservationNotice(this);

		return solveScheme;
	}

	public SolveScheme removeSolveScheme(SolveScheme solveScheme) {
		getSolveSchemes().remove(solveScheme);
		solveScheme.setObservationNotice(null);

		return solveScheme;
	}

}