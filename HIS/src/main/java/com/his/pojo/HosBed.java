package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;


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
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name="HOS_BID")
	private String hosBid;

	@Column(name="HOS_BNAME")
	private String hosBname;

	@Column(name="HOS_BSTATE")
	private String hosBstate;

	//bi-directional many-to-one association to OtherProject
	@ManyToOne
	@JoinColumn(name="PROJECT_ID")
	private OtherProject otherProject;

	//bi-directional many-to-one association to WardRoom
	@ManyToOne
	@JoinColumn(name="WROOM_ID")
	private WardRoom wardRoom;

	//bi-directional one-to-one association to HospitalizedPatient
	@OneToOne
	@JoinColumn(name="HOSP_ID")
	@JSONField(serialize=false)
	private HospitalizedPatient hospitalizedPatient;

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

	public HospitalizedPatient getHospitalizedPatient() {
		return this.hospitalizedPatient;
	}

	public void setHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		this.hospitalizedPatient = hospitalizedPatient;
	}

}