package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WAITING_ROOM database table.
 * 
 */
@Entity
@Table(name="WAITING_ROOM")
@NamedQuery(name="WaitingRoom.findAll", query="SELECT w FROM WaitingRoom w")
public class WaitingRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WAITING_ROOM_ID")
	private String waitingRoomId;

	@Column(name="WAITING_ROOM_NAME")
	private String waitingRoomName;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional one-to-one association to EmpInformation
	@OneToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	public WaitingRoom() {
	}

	public String getWaitingRoomId() {
		return this.waitingRoomId;
	}

	public void setWaitingRoomId(String waitingRoomId) {
		this.waitingRoomId = waitingRoomId;
	}

	public String getWaitingRoomName() {
		return this.waitingRoomName;
	}

	public void setWaitingRoomName(String waitingRoomName) {
		this.waitingRoomName = waitingRoomName;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}