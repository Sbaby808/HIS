package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the WKTIME_EMP database table.
 * 
 */
@Embeddable
public class WktimeEmpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PB_ID", insertable=false, updatable=false)
	private String pbId;

	@Column(insertable=false, updatable=false)
	private String ygxh;

	public WktimeEmpPK() {
	}
	public String getPbId() {
		return this.pbId;
	}
	public void setPbId(String pbId) {
		this.pbId = pbId;
	}
	public String getYgxh() {
		return this.ygxh;
	}
	public void setYgxh(String ygxh) {
		this.ygxh = ygxh;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WktimeEmpPK)) {
			return false;
		}
		WktimeEmpPK castOther = (WktimeEmpPK)other;
		return 
			this.pbId.equals(castOther.pbId)
			&& this.ygxh.equals(castOther.ygxh);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pbId.hashCode();
		hash = hash * prime + this.ygxh.hashCode();
		
		return hash;
	}
}