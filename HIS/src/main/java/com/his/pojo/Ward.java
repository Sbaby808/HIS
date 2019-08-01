package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


/**
 * The persistent class for the WARD database table.
 * 
 */
@Entity
@NamedQuery(name="Ward.findAll", query="SELECT w FROM Ward w")
public class Ward implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WARD_ID")
	private String wardId;

	@Column(name="WARD_ADRESS")
	private String wardAdress;

	@Column(name="WARD_NAME")
	private String wardName;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-one association to WardRoom
	@OneToMany(mappedBy="ward")
	@JSONField(serialize=false)
	private List<WardRoom> wardRooms;

	public Ward() {
	}

	public String getWardId() {
		return this.wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public String getWardAdress() {
		return this.wardAdress;
	}

	public void setWardAdress(String wardAdress) {
		this.wardAdress = wardAdress;
	}

	public String getWardName() {
		return this.wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<WardRoom> getWardRooms() {
		return this.wardRooms;
	}

	public void setWardRooms(List<WardRoom> wardRooms) {
		this.wardRooms = wardRooms;
	}

	public WardRoom addWardRoom(WardRoom wardRoom) {
		getWardRooms().add(wardRoom);
		wardRoom.setWard(this);

		return wardRoom;
	}

	public WardRoom removeWardRoom(WardRoom wardRoom) {
		getWardRooms().remove(wardRoom);
		wardRoom.setWard(null);

		return wardRoom;
	}

}