package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CHANGE_ROLE database table.
 * 
 */
@Embeddable
public class ChangeRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ROLE_ID", insertable=false, updatable=false)
	private String roleId;

	@Column(name="ZZ_ID", insertable=false, updatable=false)
	private String zzId;

	public ChangeRolePK() {
	}
	public String getRoleId() {
		return this.roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getZzId() {
		return this.zzId;
	}
	public void setZzId(String zzId) {
		this.zzId = zzId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ChangeRolePK)) {
			return false;
		}
		ChangeRolePK castOther = (ChangeRolePK)other;
		return 
			this.roleId.equals(castOther.roleId)
			&& this.zzId.equals(castOther.zzId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId.hashCode();
		hash = hash * prime + this.zzId.hashCode();
		
		return hash;
	}
}