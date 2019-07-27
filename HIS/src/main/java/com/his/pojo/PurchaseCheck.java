package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PURCHASE_CHECK database table.
 * 
 */
@Entity
@Table(name="PURCHASE_CHECK")
@NamedQuery(name="PurchaseCheck.findAll", query="SELECT p FROM PurchaseCheck p")
public class PurchaseCheck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PUR_CHA_ID")
	private String purChaId;

	@Temporal(TemporalType.DATE)
	@Column(name="PUR_CHA_TIME")
	private Date purChaTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to PurCheDetail
	@OneToMany(mappedBy="purchaseCheck")
	private List<PurCheDetail> purCheDetails;

	public PurchaseCheck() {
	}

	public String getPurChaId() {
		return this.purChaId;
	}

	public void setPurChaId(String purChaId) {
		this.purChaId = purChaId;
	}

	public Date getPurChaTime() {
		return this.purChaTime;
	}

	public void setPurChaTime(Date purChaTime) {
		this.purChaTime = purChaTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<PurCheDetail> getPurCheDetails() {
		return this.purCheDetails;
	}

	public void setPurCheDetails(List<PurCheDetail> purCheDetails) {
		this.purCheDetails = purCheDetails;
	}

	public PurCheDetail addPurCheDetail(PurCheDetail purCheDetail) {
		getPurCheDetails().add(purCheDetail);
		purCheDetail.setPurchaseCheck(this);

		return purCheDetail;
	}

	public PurCheDetail removePurCheDetail(PurCheDetail purCheDetail) {
		getPurCheDetails().remove(purCheDetail);
		purCheDetail.setPurchaseCheck(null);

		return purCheDetail;
	}

}