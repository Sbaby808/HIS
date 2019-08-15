package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HOS_DRUG_DETAIL database table.
 * 
 */
@Embeddable
public class HosDrugDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="HOS_DRUG_RID" ,insertable=false, updatable=false)
	private String hosDrugRid;

	@Column(name="YP_ID" ,insertable=false, updatable=false)
	private String ypId;

	public HosDrugDetailPK() {
	}
	public String getHosDrugRid() {
		return this.hosDrugRid;
	}
	public void setHosDrugRid(String hosDrugRid) {
		this.hosDrugRid = hosDrugRid;
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
		if (!(other instanceof HosDrugDetailPK)) {
			return false;
		}
		HosDrugDetailPK castOther = (HosDrugDetailPK)other;
		return 
			this.hosDrugRid.equals(castOther.hosDrugRid)
			&& this.ypId.equals(castOther.ypId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.hosDrugRid.hashCode();
		hash = hash * prime + this.ypId.hashCode();
		
		return hash;
	}
}