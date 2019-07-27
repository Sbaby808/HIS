package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CHANGE_DRUG_DETAILS database table.
 * 
 */
@Embeddable
public class ChangeDrugDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ALLO_ID", insertable=false, updatable=false)
	private String alloId;

	@Column(name="MEDICINE_ID", insertable=false, updatable=false)
	private String medicineId;

	public ChangeDrugDetailPK() {
	}
	public String getAlloId() {
		return this.alloId;
	}
	public void setAlloId(String alloId) {
		this.alloId = alloId;
	}
	public String getMedicineId() {
		return this.medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ChangeDrugDetailPK)) {
			return false;
		}
		ChangeDrugDetailPK castOther = (ChangeDrugDetailPK)other;
		return 
			this.alloId.equals(castOther.alloId)
			&& this.medicineId.equals(castOther.medicineId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.alloId.hashCode();
		hash = hash * prime + this.medicineId.hashCode();
		
		return hash;
	}
}