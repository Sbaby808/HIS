package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the BACK_DETAILS database table.
 * 
 */
@Embeddable
public class BackDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MEDICINE_ID", insertable=false, updatable=false)
	private String medicineId;

	@Column(name="BACK_ID", insertable=false, updatable=false)
	private String backId;

	public BackDetailPK() {
	}
	public String getMedicineId() {
		return this.medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
	public String getBackId() {
		return this.backId;
	}
	public void setBackId(String backId) {
		this.backId = backId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BackDetailPK)) {
			return false;
		}
		BackDetailPK castOther = (BackDetailPK)other;
		return 
			this.medicineId.equals(castOther.medicineId)
			&& this.backId.equals(castOther.backId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.medicineId.hashCode();
		hash = hash * prime + this.backId.hashCode();
		
		return hash;
	}
}