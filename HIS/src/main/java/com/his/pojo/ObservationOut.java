package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


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

	//bi-directional one-to-one association to ObservationNotice
	@OneToOne
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

	public ObservationNotice getObservationNotice() {
		return this.observationNotice;
	}

	public void setObservationNotice(ObservationNotice observationNotice) {
		this.observationNotice = observationNotice;
	}

}