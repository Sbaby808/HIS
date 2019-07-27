package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the USER_ROLE database table.
 * 
 */
@Embeddable
public class UserRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String ygxh;

	@Column(name="ROLE_ID", insertable=false, updatable=false)
	private String roleId;

	public UserRolePK() {
	}
	public String getYgxh() {
		return this.ygxh;
	}
	public void setYgxh(String ygxh) {
		this.ygxh = ygxh;
	}
	public String getRoleId() {
		return this.roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserRolePK)) {
			return false;
		}
		UserRolePK castOther = (UserRolePK)other;
		return 
			this.ygxh.equals(castOther.ygxh)
			&& this.roleId.equals(castOther.roleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ygxh.hashCode();
		hash = hash * prime + this.roleId.hashCode();
		
		return hash;
	}
}