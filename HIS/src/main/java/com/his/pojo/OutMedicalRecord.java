package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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

	//bi-directional many-to-one association to OutpatientRegistration
	@OneToMany(mappedBy="outMedicalRecord")
	private List<OutpatientRegistration> outpatientRegistrations;

	//bi-directional many-to-one association to OutpatientRegistration
	@ManyToOne
	@JoinColumn(name="REG_ID")
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

	public List<OutpatientRegistration> getOutpatientRegistrations() {
		return this.outpatientRegistrations;
	}

	public void setOutpatientRegistrations(List<OutpatientRegistration> outpatientRegistrations) {
		this.outpatientRegistrations = outpatientRegistrations;
	}

	public OutpatientRegistration addOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().add(outpatientRegistration);
		outpatientRegistration.setOutMedicalRecord(this);

		return outpatientRegistration;
	}

	public OutpatientRegistration removeOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().remove(outpatientRegistration);
		outpatientRegistration.setOutMedicalRecord(null);

		return outpatientRegistration;
	}

	public OutpatientRegistration getOutpatientRegistration() {
		return this.outpatientRegistration;
	}

	public void setOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		this.outpatientRegistration = outpatientRegistration;
	}

}