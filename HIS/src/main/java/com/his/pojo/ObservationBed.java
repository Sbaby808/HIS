package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OBSERVATION_BED database table.
 * 
 */
@Entity
@Table(name="OBSERVATION_BED")
@NamedQuery(name="ObservationBed.findAll", query="SELECT o FROM ObservationBed o")
public class ObservationBed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JZCW_ID")
	private String jzcwId;

	@Column(name="JZCW_NAME")
	private String jzcwName;

	//bi-directional many-to-one association to OtherProject
	@ManyToOne
	@JoinColumn(name="PROJECT_ID")
	private OtherProject otherProject;

	//bi-directional one-to-one association to RemainRecord
	@OneToOne
	@JoinColumn(name="REMAIN_ID")
	private RemainRecord remainRecord;

	public ObservationBed() {
	}

	public String getJzcwId() {
		return this.jzcwId;
	}

	public void setJzcwId(String jzcwId) {
		this.jzcwId = jzcwId;
	}

	public String getJzcwName() {
		return this.jzcwName;
	}

	public void setJzcwName(String jzcwName) {
		this.jzcwName = jzcwName;
	}

	public OtherProject getOtherProject() {
		return this.otherProject;
	}

	public void setOtherProject(OtherProject otherProject) {
		this.otherProject = otherProject;
	}

	public RemainRecord getRemainRecord() {
		return this.remainRecord;
	}

	public void setRemainRecord(RemainRecord remainRecord) {
		this.remainRecord = remainRecord;
	}

}