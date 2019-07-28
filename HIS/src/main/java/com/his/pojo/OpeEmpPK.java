package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OPE_EMP database table.
 * 
 */
@Embeddable
public class OpeEmpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String ygxh;

	@Column(name="OPE_ID", insertable=false, updatable=false)
	private String opeId;

	public OpeEmpPK() {
	}
	public String getYgxh() {
		return this.ygxh;
	}
	public void setYgxh(String ygxh) {
		this.ygxh = ygxh;
	}
	public String getOpeId() {
		return this.opeId;
	}
	public void setOpeId(String opeId) {
		this.opeId = opeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OpeEmpPK)) {
			return false;
		}
		OpeEmpPK castOther = (OpeEmpPK)other;
		return 
			this.ygxh.equals(castOther.ygxh)
			&& this.opeId.equals(castOther.opeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ygxh.hashCode();
		hash = hash * prime + this.opeId.hashCode();
		
		return hash;
	}
}