package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the REQ_DETAILS database table.
 * 
 */
@Embeddable
public class ReqDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="REQ_ID", insertable=false, updatable=false)
	private String reqId;

	@Column(name="YP_ID", insertable=false, updatable=false)
	private String ypId;

	public ReqDetailPK() {
	}
	public String getReqId() {
		return this.reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
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
		if (!(other instanceof ReqDetailPK)) {
			return false;
		}
		ReqDetailPK castOther = (ReqDetailPK)other;
		return 
			this.reqId.equals(castOther.reqId)
			&& this.ypId.equals(castOther.ypId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.reqId.hashCode();
		hash = hash * prime + this.ypId.hashCode();
		
		return hash;
	}
}