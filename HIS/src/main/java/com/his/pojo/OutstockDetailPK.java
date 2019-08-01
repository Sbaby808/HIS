package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OUTSTOCK_DETAILS database table.
 * 
 */
@Embeddable
public class OutstockDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CK_ID", insertable=false, updatable=false)
	private String ckId;

	@Column(name="PCKC_ID", insertable=false, updatable=false)
	private String pckcId;

	public OutstockDetailPK() {
	}
	public String getCkId() {
		return this.ckId;
	}
	public void setCkId(String ckId) {
		this.ckId = ckId;
	}
	public String getPckcId() {
		return this.pckcId;
	}
	public void setPckcId(String pckcId) {
		this.pckcId = pckcId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OutstockDetailPK)) {
			return false;
		}
		OutstockDetailPK castOther = (OutstockDetailPK)other;
		return 
			this.ckId.equals(castOther.ckId)
			&& this.pckcId.equals(castOther.pckcId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ckId.hashCode();
		hash = hash * prime + this.pckcId.hashCode();
		
		return hash;
	}
}