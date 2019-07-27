package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the INJECTION_DETAIL database table.
 * 
 */
@Embeddable
public class InjectionDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="INJ_ID", insertable=false, updatable=false)
	private String injId;

	@Column(name="MEDICINE_ID", insertable=false, updatable=false)
	private String medicineId;

	public InjectionDetailPK() {
	}
	public String getInjId() {
		return this.injId;
	}
	public void setInjId(String injId) {
		this.injId = injId;
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
		if (!(other instanceof InjectionDetailPK)) {
			return false;
		}
		InjectionDetailPK castOther = (InjectionDetailPK)other;
		return 
			this.injId.equals(castOther.injId)
			&& this.medicineId.equals(castOther.medicineId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.injId.hashCode();
		hash = hash * prime + this.medicineId.hashCode();
		
		return hash;
	}
}