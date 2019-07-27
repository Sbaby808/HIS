package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CHECK_RESULT_DETAILS database table.
 * 
 */
@Embeddable
public class CheckResultDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CHECK_RESULT_ID", insertable=false, updatable=false)
	private String checkResultId;

	@Column(name="CHECK_ITEM_ID", insertable=false, updatable=false)
	private String checkItemId;

	public CheckResultDetailPK() {
	}
	public String getCheckResultId() {
		return this.checkResultId;
	}
	public void setCheckResultId(String checkResultId) {
		this.checkResultId = checkResultId;
	}
	public String getCheckItemId() {
		return this.checkItemId;
	}
	public void setCheckItemId(String checkItemId) {
		this.checkItemId = checkItemId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CheckResultDetailPK)) {
			return false;
		}
		CheckResultDetailPK castOther = (CheckResultDetailPK)other;
		return 
			this.checkResultId.equals(castOther.checkResultId)
			&& this.checkItemId.equals(castOther.checkItemId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.checkResultId.hashCode();
		hash = hash * prime + this.checkItemId.hashCode();
		
		return hash;
	}
}