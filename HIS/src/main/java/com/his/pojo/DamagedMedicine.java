package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the DAMAGED_MEDICINE database table.
 * 
 */
@Entity
@Table(name="DAMAGED_MEDICINE")
@NamedQuery(name="DamagedMedicine.findAll", query="SELECT d FROM DamagedMedicine d")
public class DamagedMedicine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DAMAGED_ID")
	private String damagedId;

	@Column(name="DAMAGED_DESC")
	private String damagedDesc;

	@Temporal(TemporalType.DATE)
	@Column(name="DAMAGED_TIME")
	private Date damagedTime;

	@Column(name="DAMAGED_TYPE")
	private String damagedType;
	
	@Column(name="STATE")
	private String state;

	//bi-directional many-to-one association to DamagedDrugDetail
	@JSONField(serialize = false)
	@OneToMany(mappedBy="damagedMedicine",cascade = CascadeType.REMOVE)
	private List<DamagedDrugDetail> damagedDrugDetails;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	public DamagedMedicine() {
	}

	public String getDamagedId() {
		return this.damagedId;
	}

	public void setDamagedId(String damagedId) {
		this.damagedId = damagedId;
	}

	public String getDamagedDesc() {
		return this.damagedDesc;
	}

	public void setDamagedDesc(String damagedDesc) {
		this.damagedDesc = damagedDesc;
	}

	public Date getDamagedTime() {
		return this.damagedTime;
	}

	public void setDamagedTime(Date damagedTime) {
		this.damagedTime = damagedTime;
	}

	public String getDamagedType() {
		return this.damagedType;
	}

	public void setDamagedType(String damagedType) {
		this.damagedType = damagedType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<DamagedDrugDetail> getDamagedDrugDetails() {
		return this.damagedDrugDetails;
	}

	public void setDamagedDrugDetails(List<DamagedDrugDetail> damagedDrugDetails) {
		this.damagedDrugDetails = damagedDrugDetails;
	}

	public DamagedDrugDetail addDamagedDrugDetail(DamagedDrugDetail damagedDrugDetail) {
		getDamagedDrugDetails().add(damagedDrugDetail);
		damagedDrugDetail.setDamagedMedicine(this);

		return damagedDrugDetail;
	}

	public DamagedDrugDetail removeDamagedDrugDetail(DamagedDrugDetail damagedDrugDetail) {
		getDamagedDrugDetails().remove(damagedDrugDetail);
		damagedDrugDetail.setDamagedMedicine(null);

		return damagedDrugDetail;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}