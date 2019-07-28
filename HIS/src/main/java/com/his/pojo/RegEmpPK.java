package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the REG_EMP database table.
 * 
 */
@Embeddable
public class RegEmpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String ygxh;

	@Column(name="REG_ID", insertable=false, updatable=false)
	private String regId;

	public RegEmpPK() {
	}
	public String getYgxh() {
		return this.ygxh;
	}
	public void setYgxh(String ygxh) {
		this.ygxh = ygxh;
	}
	public String getRegId() {
		return this.regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RegEmpPK)) {
			return false;
		}
		RegEmpPK castOther = (RegEmpPK)other;
		return 
			this.ygxh.equals(castOther.ygxh)
			&& this.regId.equals(castOther.regId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ygxh.hashCode();
		hash = hash * prime + this.regId.hashCode();
		
		return hash;
	}
}