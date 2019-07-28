package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CHANGE_ROLE database table.
 * 
 */
@Entity
@Table(name="CHANGE_ROLE")
@NamedQuery(name="ChangeRole.findAll", query="SELECT c FROM ChangeRole c")
public class ChangeRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ChangeRolePK id;

	@Column(name="CGROLE_FLAG")
	private String cgroleFlag;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROLE_ID", insertable=false, updatable=false)
	private Role role;

	//bi-directional many-to-one association to Transfer
	@ManyToOne
	@JoinColumn(name="ZZ_ID", insertable=false, updatable=false)
	private Transfer transfer;

	public ChangeRole() {
	}

	public ChangeRolePK getId() {
		return this.id;
	}

	public void setId(ChangeRolePK id) {
		this.id = id;
	}

	public String getCgroleFlag() {
		return this.cgroleFlag;
	}

	public void setCgroleFlag(String cgroleFlag) {
		this.cgroleFlag = cgroleFlag;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Transfer getTransfer() {
		return this.transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

}