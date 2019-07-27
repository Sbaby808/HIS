package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DAMAGED_DRUG_DETAILS database table.
 * 
 */
@Embeddable
public class DamagedDrugDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DAMAGED_ID", insertable=false, updatable=false)
	private String damagedId;

	@Column(name="MEDICINE_ID", insertable=false, updatable=false)
	private String medicineId;

	public DamagedDrugDetailPK() {
	}
	public String getDamagedId() {
		return this.damagedId;
	}
	public void setDamagedId(String damagedId) {
		this.damagedId = damagedId;
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
		if (!(other instanceof DamagedDrugDetailPK)) {
			return false;
		}
		DamagedDrugDetailPK castOther = (DamagedDrugDetailPK)other;
		return 
			this.damagedId.equals(castOther.damagedId)
			&& this.medicineId.equals(castOther.medicineId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.damagedId.hashCode();
		hash = hash * prime + this.medicineId.hashCode();
		
		return hash;
	}
}