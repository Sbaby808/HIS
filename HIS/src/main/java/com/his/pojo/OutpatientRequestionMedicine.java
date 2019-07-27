package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OUTPATIENT_REQUESTION_MEDICINE database table.
 * 
 */
@Entity
@Table(name="OUTPATIENT_REQUESTION_MEDICINE")
@NamedQuery(name="OutpatientRequestionMedicine.findAll", query="SELECT o FROM OutpatientRequestionMedicine o")
public class OutpatientRequestionMedicine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REQ_ID")
	private String reqId;

	@Column(name="REQ_STATUS")
	private String reqStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="REQ_TIME")
	private Date reqTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to ReqDetail
	@OneToMany(mappedBy="outpatientRequestionMedicine")
	private List<ReqDetail> reqDetails;

	public OutpatientRequestionMedicine() {
	}

	public String getReqId() {
		return this.reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getReqStatus() {
		return this.reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public Date getReqTime() {
		return this.reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<ReqDetail> getReqDetails() {
		return this.reqDetails;
	}

	public void setReqDetails(List<ReqDetail> reqDetails) {
		this.reqDetails = reqDetails;
	}

	public ReqDetail addReqDetail(ReqDetail reqDetail) {
		getReqDetails().add(reqDetail);
		reqDetail.setOutpatientRequestionMedicine(this);

		return reqDetail;
	}

	public ReqDetail removeReqDetail(ReqDetail reqDetail) {
		getReqDetails().remove(reqDetail);
		reqDetail.setOutpatientRequestionMedicine(null);

		return reqDetail;
	}

}