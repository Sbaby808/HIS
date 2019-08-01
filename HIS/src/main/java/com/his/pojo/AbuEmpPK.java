package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ABU_EMP database table.
 * 
 */
@Embeddable
public class AbuEmpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String ygxh;

	@Column(name="AMB_ID", insertable=false, updatable=false)
	private String ambId;

	public AbuEmpPK() {
	}
	public String getYgxh() {
		return this.ygxh;
	}
	public void setYgxh(String ygxh) {
		this.ygxh = ygxh;
	}
	public String getAmbId() {
		return this.ambId;
	}
	public void setAmbId(String ambId) {
		this.ambId = ambId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AbuEmpPK)) {
			return false;
		}
		AbuEmpPK castOther = (AbuEmpPK)other;
		return 
			this.ygxh.equals(castOther.ygxh)
			&& this.ambId.equals(castOther.ambId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ygxh.hashCode();
		hash = hash * prime + this.ambId.hashCode();
		
		return hash;
	}
}