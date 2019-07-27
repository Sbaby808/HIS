package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the OBSERVATION_OUT database table.
 * 
 */
@Entity
@Table(name="OBSERVATION_OUT")
@NamedQuery(name="ObservationOut.findAll", query="SELECT o FROM ObservationOut o")
public class ObservationOut implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LGSCY_ID")
	private String lgscyId;

	@Column(name="LGSCY_NOTE")
	private String lgscyNote;

	@Column(name="LGSCY_TIME")
	private String lgscyTime;

	//bi-directional many-to-one association to ObservationNotice
	@OneToMany(mappedBy="observationOut")
	private List<ObservationNotice> observationNotices;

	//bi-directional many-to-one association to ObservationNotice
	@ManyToOne
	@JoinColumn(name="OBSERVA_ID")
	private ObservationNotice observationNotice;

	public ObservationOut() {
	}

	public String getLgscyId() {
		return this.lgscyId;
	}

	public void setLgscyId(String lgscyId) {
		this.lgscyId = lgscyId;
	}

	public String getLgscyNote() {
		return this.lgscyNote;
	}

	public void setLgscyNote(String lgscyNote) {
		this.lgscyNote = lgscyNote;
	}

	public String getLgscyTime() {
		return this.lgscyTime;
	}

	public void setLgscyTime(String lgscyTime) {
		this.lgscyTime = lgscyTime;
	}

	public List<ObservationNotice> getObservationNotices() {
		return this.observationNotices;
	}

	public void setObservationNotices(List<ObservationNotice> observationNotices) {
		this.observationNotices = observationNotices;
	}

	public ObservationNotice addObservationNotice(ObservationNotice observationNotice) {
		getObservationNotices().add(observationNotice);
		observationNotice.setObservationOut(this);

		return observationNotice;
	}

	public ObservationNotice removeObservationNotice(ObservationNotice observationNotice) {
		getObservationNotices().remove(observationNotice);
		observationNotice.setObservationOut(null);

		return observationNotice;
	}

	public ObservationNotice getObservationNotice() {
		return this.observationNotice;
	}

	public void setObservationNotice(ObservationNotice observationNotice) {
		this.observationNotice = observationNotice;
	}

}