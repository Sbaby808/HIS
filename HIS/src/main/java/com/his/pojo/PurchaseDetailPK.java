package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PURCHASE_DETAILS database table.
 * 
 */
@Embeddable
public class PurchaseDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CG_ID", insertable=false, updatable=false)
	private String cgId;

	@Column(name="YP_ID", insertable=false, updatable=false)
	private String ypId;

	public PurchaseDetailPK() {
	}
	public String getCgId() {
		return this.cgId;
	}
	public void setCgId(String cgId) {
		this.cgId = cgId;
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
		if (!(other instanceof PurchaseDetailPK)) {
			return false;
		}
		PurchaseDetailPK castOther = (PurchaseDetailPK)other;
		return 
			this.cgId.equals(castOther.cgId)
			&& this.ypId.equals(castOther.ypId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cgId.hashCode();
		hash = hash * prime + this.ypId.hashCode();
		
		return hash;
	}
}