package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PHA_IN database table.
 * 
 */
@Entity
@Table(name="PHA_IN")
@NamedQuery(name="PhaIn.findAll", query="SELECT p FROM PhaIn p")
public class PhaIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PHA_IN_ID")
	private String phaInId;

	@Temporal(TemporalType.DATE)
	@Column(name="PHA_IN_DATE")
	private Date phaInDate;
	
	@Column(name="REQ_ID")
	private String reqId;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to PhaInDetail
	@JSONField(serialize = false)
	@OneToMany(mappedBy="phaIn")
	private List<PhaInDetail> phaInDetails;

	public PhaIn() {
	}

	public String getPhaInId() {
		return this.phaInId;
	}

	public void setPhaInId(String phaInId) {
		this.phaInId = phaInId;
	}

	public Date getPhaInDate() {
		return this.phaInDate;
	}

	public void setPhaInDate(Date phaInDate) {
		this.phaInDate = phaInDate;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<PhaInDetail> getPhaInDetails() {
		return this.phaInDetails;
	}

	public void setPhaInDetails(List<PhaInDetail> phaInDetails) {
		this.phaInDetails = phaInDetails;
	}

	public PhaInDetail addPhaInDetail(PhaInDetail phaInDetail) {
		getPhaInDetails().add(phaInDetail);
		phaInDetail.setPhaIn(this);

		return phaInDetail;
	}

	public PhaInDetail removePhaInDetail(PhaInDetail phaInDetail) {
		getPhaInDetails().remove(phaInDetail);
		phaInDetail.setPhaIn(null);

		return phaInDetail;
	}

}