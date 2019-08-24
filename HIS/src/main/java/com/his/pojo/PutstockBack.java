package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PUTSTOCK_BACK database table.
 * 
 */
@Entity
@Table(name="PUTSTOCK_BACK")
@NamedQuery(name="PutstockBack.findAll", query="SELECT p FROM PutstockBack p")
public class PutstockBack implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PS_BACK_ID_")
	private String psBackId;

	@Column(name="PS_BACK_REASON")
	private String psBackReason;

	@Temporal(TemporalType.DATE)
	@Column(name="PS_BACK_TIME")
	private Date psBackTime;

	//bi-directional many-to-one association to PsBackDetail
	@OneToMany(mappedBy="putstockBack")
	private List<PsBackDetail> psBackDetails;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to Supplier
	@ManyToOne
	@JoinColumn(name="GYS_ID")
	private Supplier supplier1;

	public PutstockBack() {
	}

	public String getPsBackId() {
		return this.psBackId;
	}

	public void setPsBackId(String psBackId) {
		this.psBackId = psBackId;
	}

	public String getPsBackReason() {
		return this.psBackReason;
	}

	public void setPsBackReason(String psBackReason) {
		this.psBackReason = psBackReason;
	}

	public Date getPsBackTime() {
		return this.psBackTime;
	}

	public void setPsBackTime(Date psBackTime) {
		this.psBackTime = psBackTime;
	}

	public List<PsBackDetail> getPsBackDetails() {
		return this.psBackDetails;
	}

	public void setPsBackDetails(List<PsBackDetail> psBackDetails) {
		this.psBackDetails = psBackDetails;
	}

	public PsBackDetail addPsBackDetail(PsBackDetail psBackDetail) {
		getPsBackDetails().add(psBackDetail);
		psBackDetail.setPutstockBack(this);

		return psBackDetail;
	}

	public PsBackDetail removePsBackDetail(PsBackDetail psBackDetail) {
		getPsBackDetails().remove(psBackDetail);
		psBackDetail.setPutstockBack(null);

		return psBackDetail;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public Supplier getSupplier1() {
		return this.supplier1;
	}

	public void setSupplier1(Supplier supplier1) {
		this.supplier1 = supplier1;
	}

}