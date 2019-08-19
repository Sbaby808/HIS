package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * The persistent class for the HOS_OTHER_COST database table.
 * 
 */
@Entity
@Table(name="HOS_OTHER_COST")
@NamedQuery(name="HosOtherCost.findAll", query="SELECT h FROM HosOtherCost h")
public class HosOtherCost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_OID")
	private String hosOid;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name="HOS_OTIME")
	private Date hosOtime;

	//bi-directional many-to-one association to MedicalRecord
	@ManyToOne
	@JoinColumn(name="MED_RID")
	private MedicalRecord medicalRecord;

	//bi-directional many-to-one association to OtherProject
	@ManyToOne
	@JoinColumn(name="PROJECT_ID")
	private OtherProject otherProject;

	public HosOtherCost() {
	}

	public String getHosOid() {
		return this.hosOid;
	}

	public void setHosOid(String hosOid) {
		this.hosOid = hosOid;
	}

	public Date getHosOtime() {
		return this.hosOtime;
	}

	public void setHosOtime(Date hosOtime) {
		this.hosOtime = hosOtime;
	}

	public MedicalRecord getMedicalRecord() {
		return this.medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public OtherProject getOtherProject() {
		return this.otherProject;
	}

	public void setOtherProject(OtherProject otherProject) {
		this.otherProject = otherProject;
	}

}