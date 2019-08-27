package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PUT_STOCK database table.
 * 
 */
@Entity
@Table(name="PUT_STOCK")
@NamedQuery(name="PutStock.findAll", query="SELECT p FROM PutStock p")
public class PutStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RK_ID")
	private String rkId;

	@Temporal(TemporalType.DATE)
	@Column(name="RK_TIME")
	private Date rkTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to PutStockDetail
	
	@OneToMany(mappedBy="putStock")
	@JSONField(serialize=false)
	private List<PutStockDetail> putStockDetails;

	public PutStock() {
	}

	public String getRkId() {
		return this.rkId;
	}

	public void setRkId(String rkId) {
		this.rkId = rkId;
	}

	public Date getRkTime() {
		return this.rkTime;
	}

	public void setRkTime(Date rkTime) {
		this.rkTime = rkTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<PutStockDetail> getPutStockDetails() {
		return this.putStockDetails;
	}

	public void setPutStockDetails(List<PutStockDetail> putStockDetails) {
		this.putStockDetails = putStockDetails;
	}

	public PutStockDetail addPutStockDetail(PutStockDetail putStockDetail) {
		getPutStockDetails().add(putStockDetail);
		putStockDetail.setPutStock(this);

		return putStockDetail;
	}

	public PutStockDetail removePutStockDetail(PutStockDetail putStockDetail) {
		getPutStockDetails().remove(putStockDetail);
		putStockDetail.setPutStock(null);

		return putStockDetail;
	}

}