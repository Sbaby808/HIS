package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PS_BACK_DETAILS database table.
 * 
 */
@Embeddable
public class PsBackDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PCKC_ID", insertable=false, updatable=false)
	private String pckcId;

	@Column(name="PS_BACK_ID_", insertable=false, updatable=false)
	private String psBackId;

	public PsBackDetailPK() {
	}
	public String getPckcId() {
		return this.pckcId;
	}
	public void setPckcId(String pckcId) {
		this.pckcId = pckcId;
	}
	public String getPsBackId() {
		return this.psBackId;
	}
	public void setPsBackId(String psBackId) {
		this.psBackId = psBackId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PsBackDetailPK)) {
			return false;
		}
		PsBackDetailPK castOther = (PsBackDetailPK)other;
		return 
			this.pckcId.equals(castOther.pckcId)
			&& this.psBackId.equals(castOther.psBackId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pckcId.hashCode();
		hash = hash * prime + this.psBackId.hashCode();
		
		return hash;
	}
}