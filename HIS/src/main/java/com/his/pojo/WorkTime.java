package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the WORK_TIME database table.
 * 
 */
@Entity
@Table(name="WORK_TIME")
@NamedQuery(name="WorkTime.findAll", query="SELECT w FROM WorkTime w")
public class WorkTime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PB_ID")
	private String pbId;

	@Temporal(TemporalType.DATE)
	@Column(name="PB_DATE")
	private Date pbDate;

	@Column(name="PB_TYPE")
	private String pbType;

	//bi-directional many-to-one association to WktimeEmp
	@OneToMany(mappedBy="workTime")
	@JSONField(serialize = false)
	private List<WktimeEmp> wktimeEmps;

	public WorkTime() {
	}

	public String getPbId() {
		return this.pbId;
	}

	public void setPbId(String pbId) {
		this.pbId = pbId;
	}

	public Date getPbDate() {
		return this.pbDate;
	}

	public void setPbDate(Date pbDate) {
		this.pbDate = pbDate;
	}

	public String getPbType() {
		return this.pbType;
	}

	public void setPbType(String pbType) {
		this.pbType = pbType;
	}

	public List<WktimeEmp> getWktimeEmps() {
		return this.wktimeEmps;
	}

	public void setWktimeEmps(List<WktimeEmp> wktimeEmps) {
		this.wktimeEmps = wktimeEmps;
	}

	public WktimeEmp addWktimeEmp(WktimeEmp wktimeEmp) {
		getWktimeEmps().add(wktimeEmp);
		wktimeEmp.setWorkTime(this);

		return wktimeEmp;
	}

	public WktimeEmp removeWktimeEmp(WktimeEmp wktimeEmp) {
		getWktimeEmps().remove(wktimeEmp);
		wktimeEmp.setWorkTime(null);

		return wktimeEmp;
	}

}