package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PHA_IN_DETAILS database table.
 * 
 */
@Embeddable
public class PhaInDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MEDICINE_ID", insertable=false, updatable=false)
	private String medicineId;

	@Column(name="PHA_IN_ID", insertable=false, updatable=false)
	private String phaInId;

	public PhaInDetailPK() {
	}
	public String getMedicineId() {
		return this.medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
	public String getPhaInId() {
		return this.phaInId;
	}
	public void setPhaInId(String phaInId) {
		this.phaInId = phaInId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PhaInDetailPK)) {
			return false;
		}
		PhaInDetailPK castOther = (PhaInDetailPK)other;
		return 
			this.medicineId.equals(castOther.medicineId)
			&& this.phaInId.equals(castOther.phaInId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.medicineId.hashCode();
		hash = hash * prime + this.phaInId.hashCode();
		
		return hash;
	}
}