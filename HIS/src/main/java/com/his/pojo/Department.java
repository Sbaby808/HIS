package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the DEPARTMENT database table.
 * 
 */
@Entity
@Table(name = "Department")
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="KS_ID")
	private String ksId;

	@Column(name="KS_NAME")
	private String ksName;

	@Column(name="KS_REMARKS")
	private String ksRemarks;

	@Temporal(TemporalType.DATE)
	@Column(name="SET_TIME")
	private Date setTime;

	//bi-directional many-to-one association to Dept
	@ManyToOne
	@JoinColumn(name="DEPT_ID")
	private Dept dept;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="department")
	@JSONField(serialize=false)
	private List<History> histories;

	//bi-directional many-to-one association to HospitalizedPatient
	@OneToMany(mappedBy="department")
	@JSONField(serialize=false)
	private List<HospitalizedPatient> hospitalizedPatients;

	//bi-directional many-to-one association to HospitalNotice
	@OneToMany(mappedBy="department")
	@JSONField(serialize=false)
	private List<HospitalNotice> hospitalNotices;

	//bi-directional many-to-one association to OutpatientRegistration
	@OneToMany(mappedBy="department")
	@JSONField(serialize=false)
	private List<OutpatientRegistration> outpatientRegistrations;

	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="department")
	@JSONField(serialize=false)
	private List<Role> roles;

	//bi-directional many-to-one association to UseDrugRecord
	@OneToMany(mappedBy="department", cascade = CascadeType.MERGE)
	@JSONField(serialize=false)
	private List<UseDrugRecord> useDrugRecords;

	//bi-directional many-to-one association to WaitingRoom
	@OneToMany(mappedBy="department")
	@JSONField(serialize=false)
	private List<WaitingRoom> waitingRooms;

	//bi-directional many-to-one association to Ward
	@OneToMany(mappedBy="department")
	@JSONField(serialize=false)
	private List<Ward> wards;

	public Department() {
	}

	public String getKsId() {
		return this.ksId;
	}

	public void setKsId(String ksId) {
		this.ksId = ksId;
	}

	public String getKsName() {
		return this.ksName;
	}

	public void setKsName(String ksName) {
		this.ksName = ksName;
	}

	public String getKsRemarks() {
		return this.ksRemarks;
	}

	public void setKsRemarks(String ksRemarks) {
		this.ksRemarks = ksRemarks;
	}

	public Date getSetTime() {
		return this.setTime;
	}

	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setDepartment(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setDepartment(null);

		return history;
	}

	public List<HospitalizedPatient> getHospitalizedPatients() {
		return this.hospitalizedPatients;
	}

	public void setHospitalizedPatients(List<HospitalizedPatient> hospitalizedPatients) {
		this.hospitalizedPatients = hospitalizedPatients;
	}

	public HospitalizedPatient addHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().add(hospitalizedPatient);
		hospitalizedPatient.setDepartment(this);

		return hospitalizedPatient;
	}

	public HospitalizedPatient removeHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		getHospitalizedPatients().remove(hospitalizedPatient);
		hospitalizedPatient.setDepartment(null);

		return hospitalizedPatient;
	}

	public List<HospitalNotice> getHospitalNotices() {
		return this.hospitalNotices;
	}

	public void setHospitalNotices(List<HospitalNotice> hospitalNotices) {
		this.hospitalNotices = hospitalNotices;
	}

	public HospitalNotice addHospitalNotice(HospitalNotice hospitalNotice) {
		getHospitalNotices().add(hospitalNotice);
		hospitalNotice.setDepartment(this);

		return hospitalNotice;
	}

	public HospitalNotice removeHospitalNotice(HospitalNotice hospitalNotice) {
		getHospitalNotices().remove(hospitalNotice);
		hospitalNotice.setDepartment(null);

		return hospitalNotice;
	}

	public List<OutpatientRegistration> getOutpatientRegistrations() {
		return this.outpatientRegistrations;
	}

	public void setOutpatientRegistrations(List<OutpatientRegistration> outpatientRegistrations) {
		this.outpatientRegistrations = outpatientRegistrations;
	}

	public OutpatientRegistration addOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().add(outpatientRegistration);
		outpatientRegistration.setDepartment(this);

		return outpatientRegistration;
	}

	public OutpatientRegistration removeOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().remove(outpatientRegistration);
		outpatientRegistration.setDepartment(null);

		return outpatientRegistration;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setDepartment(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setDepartment(null);

		return role;
	}

	public List<UseDrugRecord> getUseDrugRecords() {
		return this.useDrugRecords;
	}

	public void setUseDrugRecords(List<UseDrugRecord> useDrugRecords) {
		this.useDrugRecords = useDrugRecords;
	}

	public UseDrugRecord addUseDrugRecord(UseDrugRecord useDrugRecord) {
		getUseDrugRecords().add(useDrugRecord);
		useDrugRecord.setDepartment(this);

		return useDrugRecord;
	}

	public UseDrugRecord removeUseDrugRecord(UseDrugRecord useDrugRecord) {
		getUseDrugRecords().remove(useDrugRecord);
		useDrugRecord.setDepartment(null);

		return useDrugRecord;
	}

	public List<WaitingRoom> getWaitingRooms() {
		return this.waitingRooms;
	}

	public void setWaitingRooms(List<WaitingRoom> waitingRooms) {
		this.waitingRooms = waitingRooms;
	}

	public WaitingRoom addWaitingRoom(WaitingRoom waitingRoom) {
		getWaitingRooms().add(waitingRoom);
		waitingRoom.setDepartment(this);

		return waitingRoom;
	}

	public WaitingRoom removeWaitingRoom(WaitingRoom waitingRoom) {
		getWaitingRooms().remove(waitingRoom);
		waitingRoom.setDepartment(null);

		return waitingRoom;
	}

	public List<Ward> getWards() {
		return this.wards;
	}

	public void setWards(List<Ward> wards) {
		this.wards = wards;
	}

	public Ward addWard(Ward ward) {
		getWards().add(ward);
		ward.setDepartment(this);

		return ward;
	}

	public Ward removeWard(Ward ward) {
		getWards().remove(ward);
		ward.setDepartment(null);

		return ward;
	}

}