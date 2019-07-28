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

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role")
	private List<UserRole> userRoles;

	//bi-directional many-to-one association to ChangeRole
	@OneToMany(mappedBy="role")
	private List<ChangeRole> changeRoles;

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

	public List<ChangeRole> getChangeRoles() {
		return this.changeRoles;
	}

	public void setChangeRoles(List<ChangeRole> changeRoles) {
		this.changeRoles = changeRoles;
	}

	public ChangeRole addChangeRole(ChangeRole changeRole) {
		getChangeRoles().add(changeRole);
		changeRole.setRole(this);

		return changeRole;
	}

	public ChangeRole removeChangeRole(ChangeRole changeRole) {
		getChangeRoles().remove(changeRole);
		changeRole.setRole(null);

		return changeRole;
	}

}