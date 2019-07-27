package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ALLOCATION_OUTBOUND database table.
 * 
 */
@Entity
@Table(name="ALLOCATION_OUTBOUND")
@NamedQuery(name="AllocationOutbound.findAll", query="SELECT a FROM AllocationOutbound a")
public class AllocationOutbound implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ALLO_ID")
	private String alloId;

	@Column(name="ALLO_STATUS")
	private String alloStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="ALLO_TIME")
	private Date alloTime;

	@Column(name="IN_YF")
	private String inYf;

	@Column(name="OUT_YF")
	private String outYf;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to ChangeDrugDetail
	@OneToMany(mappedBy="allocationOutbound")
	private List<ChangeDrugDetail> changeDrugDetails;

	public AllocationOutbound() {
	}

	public String getAlloId() {
		return this.alloId;
	}

	public void setAlloId(String alloId) {
		this.alloId = alloId;
	}

	public String getAlloStatus() {
		return this.alloStatus;
	}

	public void setAlloStatus(String alloStatus) {
		this.alloStatus = alloStatus;
	}

	public Date getAlloTime() {
		return this.alloTime;
	}

	public void setAlloTime(Date alloTime) {
		this.alloTime = alloTime;
	}

	public String getInYf() {
		return this.inYf;
	}

	public void setInYf(String inYf) {
		this.inYf = inYf;
	}

	public String getOutYf() {
		return this.outYf;
	}

	public void setOutYf(String outYf) {
		this.outYf = outYf;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<ChangeDrugDetail> getChangeDrugDetails() {
		return this.changeDrugDetails;
	}

	public void setChangeDrugDetails(List<ChangeDrugDetail> changeDrugDetails) {
		this.changeDrugDetails = changeDrugDetails;
	}

	public ChangeDrugDetail addChangeDrugDetail(ChangeDrugDetail changeDrugDetail) {
		getChangeDrugDetails().add(changeDrugDetail);
		changeDrugDetail.setAllocationOutbound(this);

		return changeDrugDetail;
	}

	public ChangeDrugDetail removeChangeDrugDetail(ChangeDrugDetail changeDrugDetail) {
		getChangeDrugDetails().remove(changeDrugDetail);
		changeDrugDetail.setAllocationOutbound(null);

		return changeDrugDetail;
	}

}