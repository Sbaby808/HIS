package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PS_INV_DETAILS database table.
 * 
 */
@Embeddable
public class PsInvDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PCKC_ID", insertable=false, updatable=false)
	private String pckcId;

	@Column(name="INVENTORY_TAKING_ID", insertable=false, updatable=false)
	private String inventoryTakingId;

	public PsInvDetailPK() {
	}
	public String getPckcId() {
		return this.pckcId;
	}
	public void setPckcId(String pckcId) {
		this.pckcId = pckcId;
	}
	public String getInventoryTakingId() {
		return this.inventoryTakingId;
	}
	public void setInventoryTakingId(String inventoryTakingId) {
		this.inventoryTakingId = inventoryTakingId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PsInvDetailPK)) {
			return false;
		}
		PsInvDetailPK castOther = (PsInvDetailPK)other;
		return 
			this.pckcId.equals(castOther.pckcId)
			&& this.inventoryTakingId.equals(castOther.inventoryTakingId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pckcId.hashCode();
		hash = hash * prime + this.inventoryTakingId.hashCode();
		
		return hash;
	}
}