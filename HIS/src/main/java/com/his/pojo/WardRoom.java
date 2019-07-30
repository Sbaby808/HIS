package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the WARD_ROOM database table.
 * 
 */
@Entity
@Table(name="WARD_ROOM")
@NamedQuery(name="WardRoom.findAll", query="SELECT w FROM WardRoom w")
public class WardRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WROOM_ID")
	private String wroomId;

	@Column(name="W_NUM")
	private BigDecimal wNum;

	@Column(name="WBED_NUM")
	private BigDecimal wbedNum;

	@Column(name="WROOM_NAME")
	private String wroomName;

	//bi-directional many-to-one association to HospitalizedPatient
	@OneToMany(mappedBy="wardRoom")
	@JSONField(serialize=false)
	private List<HospitalizedPatient> hospitalizedPatients;

	//bi-directional many-to-one association to HosBed
	@OneToMany(mappedBy="wardRoom")
	@JSONField(serialize=false)
	private List<HosBed> hosBeds;

	//bi-directional many-to-one association to Ward
	@ManyToOne
	@JoinColumn(name="WARD_ID")
	private Ward ward;

	public WardRoom() {
	}

	public String getWroomId() {
		return this.wroomId;
	}

	public void setWroomId(String wroomId) {
		this.wroomId = wroomId;
	}

	public BigDecimal getWNum() {
		return this.wNum;
	}

	public void setWNum(BigDecimal wNum) {
		this.wNum = wNum;
	}

	public BigDecimal getWbedNum() {
		return this.wbedNum;
	}

	public void setWbedNum(BigDecimal wbedNum) {
		this.wbedNum = wbedNum;
	}

	public String getWroomName() {
		return this.wroomName;
	}

	public void setWroomName(String wroomName) {
		this.wroomName = wroomName;
	}

	public List<HospitalizedPatient> getHospitalizedPatients() {
		return this.hospitalizedPatients;
	}

	public void setHospitalizedPatients(List<HospitalizedPatient> hospitalizedPatients) {
		this.hospitalizedPatients = hospitalizedPatients;
	}

	public HospitalizedPatient addHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().add(hospitalizedPatient);
		hospitalizedPatient.setWardRoom(this);

		return hospitalizedPatient;
	}

	public HospitalizedPatient removeHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().remove(hospitalizedPatient);
		hospitalizedPatient.setWardRoom(null);

		return hospitalizedPatient;
	}

	public List<HosBed> getHosBeds() {
		return this.hosBeds;
	}

	public void setHosBeds(List<HosBed> hosBeds) {
		this.hosBeds = hosBeds;
	}

	public HosBed addHosBed(HosBed hosBed) {
		getHosBeds().add(hosBed);
		hosBed.setWardRoom(this);

		return hosBed;
	}

	public HosBed removeHosBed(HosBed hosBed) {
		getHosBeds().remove(hosBed);
		hosBed.setWardRoom(null);

		return hosBed;
	}

	public Ward getWard() {
		return this.ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

}