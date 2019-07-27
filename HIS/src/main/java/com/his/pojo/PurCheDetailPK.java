package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PUR_CHE_DETAILS database table.
 * 
 */
@Embeddable
public class PurCheDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="YP_ID", insertable=false, updatable=false)
	private String ypId;

	@Column(name="PUR_CHA_ID", insertable=false, updatable=false)
	private String purChaId;

	public PurCheDetailPK() {
	}
	public String getYpId() {
		return this.ypId;
	}
	public void setYpId(String ypId) {
		this.ypId = ypId;
	}
	public String getPurChaId() {
		return this.purChaId;
	}
	public void setPurChaId(String purChaId) {
		this.purChaId = purChaId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PurCheDetailPK)) {
			return false;
		}
		PurCheDetailPK castOther = (PurCheDetailPK)other;
		return 
			this.ypId.equals(castOther.ypId)
			&& this.purChaId.equals(castOther.purChaId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ypId.hashCode();
		hash = hash * prime + this.purChaId.hashCode();
		
		return hash;
	}
}