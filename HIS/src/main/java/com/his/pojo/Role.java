package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ROLE" database table.
 * 
 */
@Entity
@Table(name="\"ROLE\"")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_ID")
	private String roleId;

	@Column(name="ROLE_POSITION")
	private String rolePosition;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="KS_ID")
	private Department department;

	//bi-directional many-to-many association to Function
	@ManyToMany
	@JoinTable(
		name="ROLE_FUNC"
		, joinColumns={
			@JoinColumn(name="ROLE_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FUNCTION_ID")
			}
		)
	private List<Function> functions;

	//bi-directional many-to-one association to Transfer
	@OneToMany(mappedBy="role1")
	private List<Transfer> transfers1;

	//bi-directional many-to-one association to Transfer
	@OneToMany(mappedBy="role2")
	private List<Transfer> transfers2;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role")
	private List<UserRole> userRoles;

	public Role() {
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRolePosition() {
		return this.rolePosition;
	}

	public void setRolePosition(String rolePosition) {
		this.rolePosition = rolePosition;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Function> getFunctions() {
		return this.functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public List<Transfer> getTransfers1() {
		return this.transfers1;
	}

	public void setTransfers1(List<Transfer> transfers1) {
		this.transfers1 = transfers1;
	}

	public Transfer addTransfers1(Transfer transfers1) {
		getTransfers1().add(transfers1);
		transfers1.setRole1(this);

		return transfers1;
	}

	public Transfer removeTransfers1(Transfer transfers1) {
		getTransfers1().remove(transfers1);
		transfers1.setRole1(null);

		return transfers1;
	}

	public List<Transfer> getTransfers2() {
		return this.transfers2;
	}

	public void setTransfers2(List<Transfer> transfers2) {
		this.transfers2 = transfers2;
	}

	public Transfer addTransfers2(Transfer transfers2) {
		getTransfers2().add(transfers2);
		transfers2.setRole2(this);

		return transfers2;
	}

	public Transfer removeTransfers2(Transfer transfers2) {
		getTransfers2().remove(transfers2);
		transfers2.setRole2(null);

		return transfers2;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setRole(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setRole(null);

		return userRole;
	}

}