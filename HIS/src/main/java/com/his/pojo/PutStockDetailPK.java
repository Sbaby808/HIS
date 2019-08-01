package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PUT_STOCK_DETAILS database table.
 * 
 */
@Embeddable
public class PutStockDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PCKC_ID", insertable=false, updatable=false)
	private String pckcId;

	@Column(name="RK_ID", insertable=false, updatable=false)
	private String rkId;

	public PutStockDetailPK() {
	}
	public String getPckcId() {
		return this.pckcId;
	}
	public void setPckcId(String pckcId) {
		this.pckcId = pckcId;
	}
	public String getRkId() {
		return this.rkId;
	}
	public void setRkId(String rkId) {
		this.rkId = rkId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PutStockDetailPK)) {
			return false;
		}
		PutStockDetailPK castOther = (PutStockDetailPK)other;
		return 
			this.pckcId.equals(castOther.pckcId)
			&& this.rkId.equals(castOther.rkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pckcId.hashCode();
		hash = hash * prime + this.rkId.hashCode();
		
		return hash;
	}
}