package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DRUG_SCRAP_DETAILS database table.
 * 
 */
@Embeddable
public class DrugScrapDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="BF_ID", insertable=false, updatable=false)
	private String bfId;

	@Column(name="PCKC_ID", insertable=false, updatable=false)
	private String pckcId;

	public DrugScrapDetailPK() {
	}
	public String getBfId() {
		return this.bfId;
	}
	public void setBfId(String bfId) {
		this.bfId = bfId;
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
		if (!(other instanceof DrugScrapDetailPK)) {
			return false;
		}
		DrugScrapDetailPK castOther = (DrugScrapDetailPK)other;
		return 
			this.bfId.equals(castOther.bfId)
			&& this.pckcId.equals(castOther.pckcId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.bfId.hashCode();
		hash = hash * prime + this.pckcId.hashCode();
		
		return hash;
	}
}