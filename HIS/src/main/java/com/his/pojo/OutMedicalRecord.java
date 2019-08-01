package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the OUT_MEDICAL_RECORDS database table.
 * 
 */
@Entity
@Table(name="OUT_MEDICAL_RECORDS")
@NamedQuery(name="OutMedicalRecord.findAll", query="SELECT o FROM OutMedicalRecord o")
public class OutMedicalRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OUT_MID")
	private String outMid;

	@Temporal(TemporalType.DATE)
	@Column(name="OUT_MTIME")
	private Date outMtime;

	@Column(name="OUT_TIMES")
	private BigDecimal outTimes;

	@Column(name="REG_ID")
	private String regId;

	//bi-directional one-to-one association to OutpatientRegistration
	@OneToOne
	@JoinColumn(name="OUT_MID", referencedColumnName="CARD_ID")
	private OutpatientRegistration outpatientRegistration;

	public OutMedicalRecord() {
	}

	public String getOutMid() {
		return this.outMid;
	}

	public void setOutMid(String outMid) {
		this.outMid = outMid;
	}

	public Date getOutMtime() {
		return this.outMtime;
	}

	public void setOutMtime(Date outMtime) {
		this.outMtime = outMtime;
	}

	public BigDecimal getOutTimes() {
		return this.outTimes;
	}

	public void setOutTimes(BigDecimal outTimes) {
		this.outTimes = outTimes;
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public OutpatientRegistration getOutpatientRegistration() {
		return this.outpatientRegistration;
	}

	public void setOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		this.outpatientRegistration = outpatientRegistration;
	}

}