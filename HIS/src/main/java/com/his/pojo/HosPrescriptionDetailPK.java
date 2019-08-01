package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HOS_PRESCRIPTION_DETAIL database table.
 * 
 */
@Embeddable
public class HosPrescriptionDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="HOS_PRE_ID", insertable=false, updatable=false)
	private String hosPreId;

	@Column(name="YP_ID", insertable=false, updatable=false)
	private String ypId;

	public HosPrescriptionDetailPK() {
	}
	public String getHosPreId() {
		return this.hosPreId;
	}
	public void setHosPreId(String hosPreId) {
		this.hosPreId = hosPreId;
	}
	public String getYpId() {
		return this.ypId;
	}
	public void setYpId(String ypId) {
		this.ypId = ypId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HosPrescriptionDetailPK)) {
			return false;
		}
		HosPrescriptionDetailPK castOther = (HosPrescriptionDetailPK)other;
		return 
			this.hosPreId.equals(castOther.hosPreId)
			&& this.ypId.equals(castOther.ypId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.hosPreId.hashCode();
		hash = hash * prime + this.ypId.hashCode();
		
		return hash;
	}
}