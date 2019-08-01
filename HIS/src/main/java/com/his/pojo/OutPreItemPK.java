package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OUT_PRE_ITEM database table.
 * 
 */
@Embeddable
public class OutPreItemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PRESCRIPTION_ID", insertable=false, updatable=false)
	private String prescriptionId;

	@Column(name="YP_ID", insertable=false, updatable=false)
	private String ypId;

	public OutPreItemPK() {
	}
	public String getPrescriptionId() {
		return this.prescriptionId;
	}
	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
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
		if (!(other instanceof OutPreItemPK)) {
			return false;
		}
		OutPreItemPK castOther = (OutPreItemPK)other;
		return 
			this.prescriptionId.equals(castOther.prescriptionId)
			&& this.ypId.equals(castOther.ypId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.prescriptionId.hashCode();
		hash = hash * prime + this.ypId.hashCode();
		
		return hash;
	}
}