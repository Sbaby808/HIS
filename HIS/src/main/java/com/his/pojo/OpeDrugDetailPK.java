package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OPE_DRUG_DETAILS database table.
 * 
 */
@Embeddable
public class OpeDrugDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="OPE_ID", insertable=false, updatable=false)
	private String opeId;

	@Column(name="MEDICINE_ID", insertable=false, updatable=false)
	private String medicineId;

	public OpeDrugDetailPK() {
	}
	public String getOpeId() {
		return this.opeId;
	}
	public void setOpeId(String opeId) {
		this.opeId = opeId;
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
		if (!(other instanceof OpeDrugDetailPK)) {
			return false;
		}
		OpeDrugDetailPK castOther = (OpeDrugDetailPK)other;
		return 
			this.opeId.equals(castOther.opeId)
			&& this.medicineId.equals(castOther.medicineId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.opeId.hashCode();
		hash = hash * prime + this.medicineId.hashCode();
		
		return hash;
	}
}