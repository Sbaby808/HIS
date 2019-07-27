package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROL_ROLE_ID")
	private Role role1;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private Role role2;

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

	public Role getRole1() {
		return this.role1;
	}

	public void setRole1(Role role1) {
		this.role1 = role1;
	}

	public Role getRole2() {
		return this.role2;
	}

	public void setRole2(Role role2) {
		this.role2 = role2;
	}

}