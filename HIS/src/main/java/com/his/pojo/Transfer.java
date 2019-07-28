package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TRANSFER database table.
 * 
 */
@Entity
@NamedQuery(name="Transfer.findAll", query="SELECT t FROM Transfer t")
public class Transfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ZZ_ID")
	private String zzId;

	@Column(name="ZZ_REASON")
	private String zzReason;

	@Temporal(TemporalType.DATE)
	@Column(name="ZZ_TIME")
	private Date zzTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to ChangeRole
	@OneToMany(mappedBy="transfer")
	private List<ChangeRole> changeRoles;

	public Transfer() {
	}

	public String getZzId() {
		return this.zzId;
	}

	public void setZzId(String zzId) {
		this.zzId = zzId;
	}

	public String getZzReason() {
		return this.zzReason;
	}

	public void setZzReason(String zzReason) {
		this.zzReason = zzReason;
	}

	public Date getZzTime() {
		return this.zzTime;
	}

	public void setZzTime(Date zzTime) {
		this.zzTime = zzTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<ChangeRole> getChangeRoles() {
		return this.changeRoles;
	}

	public void setChangeRoles(List<ChangeRole> changeRoles) {
		this.changeRoles = changeRoles;
	}

	public ChangeRole addChangeRole(ChangeRole changeRole) {
		getChangeRoles().add(changeRole);
		changeRole.setTransfer(this);

		return changeRole;
	}

	public ChangeRole removeChangeRole(ChangeRole changeRole) {
		getChangeRoles().remove(changeRole);
		changeRole.setTransfer(null);

		return changeRole;
	}

}