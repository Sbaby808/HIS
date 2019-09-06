package com.his.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * The persistent class for the OUTSTOCK database table.
 * 
 */
@Entity
@NamedQuery(name="Outstock.findAll", query="SELECT o FROM Outstock o")
public class Outstock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CK_ID")
	private String ckId;

	@Temporal(TemporalType.DATE)
	@Column(name="CK_TIME")
	private Date ckTime;
	
	@Column(name="REQ_ID")
	private String req_id;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to OutstockDetail
	@JSONField(serialize=false)
	@OneToMany(mappedBy="outstock")
	private List<OutstockDetail> outstockDetails;

	public Outstock() {
	}

	public String getCkId() {
		return this.ckId;
	}

	public void setCkId(String ckId) {
		this.ckId = ckId;
	}

	public Date getCkTime() {
		return this.ckTime;
	}

	public void setCkTime(Date ckTime) {
		this.ckTime = ckTime;
	}

	public String getReq_id() {
		return req_id;
	}

	public void setReq_id(String req_id) {
		this.req_id = req_id;
	}
	
	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<OutstockDetail> getOutstockDetails() {
		return this.outstockDetails;
	}

	public void setOutstockDetails(List<OutstockDetail> outstockDetails) {
		this.outstockDetails = outstockDetails;
	}

	public OutstockDetail addOutstockDetail(OutstockDetail outstockDetail) {
		getOutstockDetails().add(outstockDetail);
		outstockDetail.setOutstock(this);

		return outstockDetail;
	}

	public OutstockDetail removeOutstockDetail(OutstockDetail outstockDetail) {
		getOutstockDetails().remove(outstockDetail);
		outstockDetail.setOutstock(null);

		return outstockDetail;
	}

}