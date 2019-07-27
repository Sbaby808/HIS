package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PURCHASE database table.
 * 
 */
@Entity
@NamedQuery(name="Purchase.findAll", query="SELECT p FROM Purchase p")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CG_ID")
	private String cgId;

	@Temporal(TemporalType.DATE)
	@Column(name="CG_TIME")
	private Date cgTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to PurchaseDetail
	@OneToMany(mappedBy="purchase")
	private List<PurchaseDetail> purchaseDetails;

	public Purchase() {
	}

	public String getCgId() {
		return this.cgId;
	}

	public void setCgId(String cgId) {
		this.cgId = cgId;
	}

	public Date getCgTime() {
		return this.cgTime;
	}

	public void setCgTime(Date cgTime) {
		this.cgTime = cgTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<PurchaseDetail> getPurchaseDetails() {
		return this.purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public PurchaseDetail addPurchaseDetail(PurchaseDetail purchaseDetail) {
		getPurchaseDetails().add(purchaseDetail);
		purchaseDetail.setPurchase(this);

		return purchaseDetail;
	}

	public PurchaseDetail removePurchaseDetail(PurchaseDetail purchaseDetail) {
		getPurchaseDetails().remove(purchaseDetail);
		purchaseDetail.setPurchase(null);

		return purchaseDetail;
	}

}