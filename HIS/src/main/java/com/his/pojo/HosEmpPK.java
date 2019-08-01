package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HOS_EMP database table.
 * 
 */
@Embeddable
public class HosEmpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="HOSP_ID", insertable=false, updatable=false)
	private String hospId;

	@Column(insertable=false, updatable=false)
	private String ygxh;

	public HosEmpPK() {
	}
	public String getHospId() {
		return this.hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
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
		if (!(other instanceof HosEmpPK)) {
			return false;
		}
		HosEmpPK castOther = (HosEmpPK)other;
		return 
			this.hospId.equals(castOther.hospId)
			&& this.ygxh.equals(castOther.ygxh);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.hospId.hashCode();
		hash = hash * prime + this.ygxh.hashCode();
		
		return hash;
	}
}