package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WKTIME_EMP database table.
 * 
 */
@Entity
@Table(name="WKTIME_EMP")
@NamedQuery(name="WktimeEmp.findAll", query="SELECT w FROM WktimeEmp w")
public class WktimeEmp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WktimeEmpPK id;

	@Column(name="WKTIME_DUTY")
	private String wktimeDuty;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH", insertable=false, updatable=false)
	private EmpInformation empInformation;

	//bi-directional many-to-one association to WorkTime
	@ManyToOne
	@JoinColumn(name="PB_ID", insertable=false, updatable=false)
	private WorkTime workTime;

	public WktimeEmp() {
	}

	public WktimeEmpPK getId() {
		return this.id;
	}

	public void setId(WktimeEmpPK id) {
		this.id = id;
	}

	public String getWktimeDuty() {
		return this.wktimeDuty;
	}

	public void setWktimeDuty(String wktimeDuty) {
		this.wktimeDuty = wktimeDuty;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public WorkTime getWorkTime() {
		return this.workTime;
	}

	public void setWorkTime(WorkTime workTime) {
		this.workTime = workTime;
	}

}