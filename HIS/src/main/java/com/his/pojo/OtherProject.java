package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OTHER_PROJECT database table.
 * 
 */
@Entity
@Table(name="OTHER_PROJECT")
@NamedQuery(name="OtherProject.findAll", query="SELECT o FROM OtherProject o")
public class OtherProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PROJECT_ID")
	private String projectId;

	@Column(name="PROJECT_DESC")
	private String projectDesc;

	@Column(name="PROJECT_NAME")
	private String projectName;

	@Column(name="PROJECT_PRICE")
	private BigDecimal projectPrice;

	@Temporal(TemporalType.DATE)
	@Column(name="PROJECT_TIME")
	private Date projectTime;

	//bi-directional many-to-one association to AmbulanceRecord
	@OneToMany(mappedBy="otherProject")
	@JSONField(serialize = false)
	private List<AmbulanceRecord> ambulanceRecords;

	//bi-directional many-to-one association to HosBed
	@OneToMany(mappedBy="otherProject")
	@JSONField(serialize=false)
	private List<HosBed> hosBeds;

	//bi-directional many-to-one association to HosOtherCost
	@OneToMany(mappedBy="otherProject")
	@JSONField(serialize=false)
	private List<HosOtherCost> hosOtherCosts;

	//bi-directional many-to-one association to ObservationBed
	@OneToMany(mappedBy="otherProject")
	@JSONField(serialize = false)
	private List<ObservationBed> observationBeds;

	public OtherProject() {
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectDesc() {
		return this.projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BigDecimal getProjectPrice() {
		return this.projectPrice;
	}

	public void setProjectPrice(BigDecimal projectPrice) {
		this.projectPrice = projectPrice;
	}

	public Date getProjectTime() {
		return this.projectTime;
	}

	public void setProjectTime(Date projectTime) {
		this.projectTime = projectTime;
	}

	public List<AmbulanceRecord> getAmbulanceRecords() {
		return this.ambulanceRecords;
	}

	public void setAmbulanceRecords(List<AmbulanceRecord> ambulanceRecords) {
		this.ambulanceRecords = ambulanceRecords;
	}

	public AmbulanceRecord addAmbulanceRecord(AmbulanceRecord ambulanceRecord) {
		getAmbulanceRecords().add(ambulanceRecord);
		ambulanceRecord.setOtherProject(this);

		return ambulanceRecord;
	}

	public AmbulanceRecord removeAmbulanceRecord(AmbulanceRecord ambulanceRecord) {
		getAmbulanceRecords().remove(ambulanceRecord);
		ambulanceRecord.setOtherProject(null);

		return ambulanceRecord;
	}

	public List<HosBed> getHosBeds() {
		return this.hosBeds;
	}

	public void setHosBeds(List<HosBed> hosBeds) {
		this.hosBeds = hosBeds;
	}

	public HosBed addHosBed(HosBed hosBed) {
		getHosBeds().add(hosBed);
		hosBed.setOtherProject(this);

		return hosBed;
	}

	public HosBed removeHosBed(HosBed hosBed) {
		getHosBeds().remove(hosBed);
		hosBed.setOtherProject(null);

		return hosBed;
	}

	public List<HosOtherCost> getHosOtherCosts() {
		return this.hosOtherCosts;
	}

	public void setHosOtherCosts(List<HosOtherCost> hosOtherCosts) {
		this.hosOtherCosts = hosOtherCosts;
	}

	public HosOtherCost addHosOtherCost(HosOtherCost hosOtherCost) {
		getHosOtherCosts().add(hosOtherCost);
		hosOtherCost.setOtherProject(this);

		return hosOtherCost;
	}

	public HosOtherCost removeHosOtherCost(HosOtherCost hosOtherCost) {
		getHosOtherCosts().remove(hosOtherCost);
		hosOtherCost.setOtherProject(null);

		return hosOtherCost;
	}

	public List<ObservationBed> getObservationBeds() {
		return this.observationBeds;
	}

	public void setObservationBeds(List<ObservationBed> observationBeds) {
		this.observationBeds = observationBeds;
	}

	public ObservationBed addObservationBed(ObservationBed observationBed) {
		getObservationBeds().add(observationBed);
		observationBed.setOtherProject(this);

		return observationBed;
	}

	public ObservationBed removeObservationBed(ObservationBed observationBed) {
		getObservationBeds().remove(observationBed);
		observationBed.setOtherProject(null);

		return observationBed;
	}

}