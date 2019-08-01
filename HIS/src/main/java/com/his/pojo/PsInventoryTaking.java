package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PS_INVENTORY_TAKING database table.
 * 
 */
@Entity
@Table(name="PS_INVENTORY_TAKING")
@NamedQuery(name="PsInventoryTaking.findAll", query="SELECT p FROM PsInventoryTaking p")
public class PsInventoryTaking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INVENTORY_TAKING_ID")
	private String inventoryTakingId;

	@Temporal(TemporalType.DATE)
	@Column(name="INVENTORY_TAKING_TIME")
	private Date inventoryTakingTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to PsInvDetail
	@OneToMany(mappedBy="psInventoryTaking")
	private List<PsInvDetail> psInvDetails;

	public PsInventoryTaking() {
	}

	public String getInventoryTakingId() {
		return this.inventoryTakingId;
	}

	public void setInventoryTakingId(String inventoryTakingId) {
		this.inventoryTakingId = inventoryTakingId;
	}

	public Date getInventoryTakingTime() {
		return this.inventoryTakingTime;
	}

	public void setInventoryTakingTime(Date inventoryTakingTime) {
		this.inventoryTakingTime = inventoryTakingTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<PsInvDetail> getPsInvDetails() {
		return this.psInvDetails;
	}

	public void setPsInvDetails(List<PsInvDetail> psInvDetails) {
		this.psInvDetails = psInvDetails;
	}

	public PsInvDetail addPsInvDetail(PsInvDetail psInvDetail) {
		getPsInvDetails().add(psInvDetail);
		psInvDetail.setPsInventoryTaking(this);

		return psInvDetail;
	}

	public PsInvDetail removePsInvDetail(PsInvDetail psInvDetail) {
		getPsInvDetails().remove(psInvDetail);
		psInvDetail.setPsInventoryTaking(null);

		return psInvDetail;
	}

}