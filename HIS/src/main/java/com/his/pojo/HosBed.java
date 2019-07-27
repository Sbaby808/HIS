package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HOS_BEDS database table.
 * 
 */
@Entity
@Table(name="HOS_BEDS")
@NamedQuery(name="HosBed.findAll", query="SELECT h FROM HosBed h")
public class HosBed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_BID")
	private String hosBid;

	@Column(name="HOS_BNAME")
	private String hosBname;

	@Column(name="HOS_BSTATE")
	private String hosBstate;

	//bi-directional many-to-one association to HospitalizedPatient
	@OneToMany(mappedBy="hosBed")
	private List<HospitalizedPatient> hospitalizedPatients;

	//bi-directional many-to-one association to HospitalizedPatient
	@ManyToOne
	@JoinColumn(name="HOSP_ID")
	private HospitalizedPatient hospitalizedPatient;

	//bi-directional many-to-one association to OtherProject
	@ManyToOne
	@JoinColumn(name="PROJECT_ID")
	private OtherProject otherProject;

	//bi-directional many-to-one association to WardRoom
	@ManyToOne
	@JoinColumn(name="WROOM_ID")
	private WardRoom wardRoom;

	public HosBed() {
	}

	public String getHosBid() {
		return this.hosBid;
	}

	public void setHosBid(String hosBid) {
		this.hosBid = hosBid;
	}

	public String getHosBname() {
		return this.hosBname;
	}

	public void setHosBname(String hosBname) {
		this.hosBname = hosBname;
	}

	public String getHosBstate() {
		return this.hosBstate;
	}

	public void setHosBstate(String hosBstate) {
		this.hosBstate = hosBstate;
	}

	public List<HospitalizedPatient> getHospitalizedPatients() {
		return this.hospitalizedPatients;
	}

	public void setHospitalizedPatients(List<HospitalizedPatient> hospitalizedPatients) {
		this.hospitalizedPatients = hospitalizedPatients;
	}

	public HospitalizedPatient addHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().add(hospitalizedPatient);
		hospitalizedPatient.setHosBed(this);

		return hospitalizedPatient;
	}

	public HospitalizedPatient removeHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().remove(hospitalizedPatient);
		hospitalizedPatient.setHosBed(null);

		return hospitalizedPatient;
	}

	public HospitalizedPatient getHospitalizedPatient() {
		return this.hospitalizedPatient;
	}

	public void setHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		this.hospitalizedPatient = hospitalizedPatient;
	}

	public OtherProject getOtherProject() {
		return this.otherProject;
	}

	public void setOtherProject(OtherProject otherProject) {
		this.otherProject = otherProject;
	}

	public WardRoom getWardRoom() {
		return this.wardRoom;
	}

	public void setWardRoom(WardRoom wardRoom) {
		this.wardRoom = wardRoom;
	}

}