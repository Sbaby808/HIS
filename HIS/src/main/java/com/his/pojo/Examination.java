package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the EXAMINATION database table.
 * 
 */
@Entity
@NamedQuery(name="Examination.findAll", query="SELECT e FROM Examination e")
public class Examination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EXAM_ID")
	private String examId;

	@Temporal(TemporalType.DATE)
	@Column(name="EXAM_TIME")
	private Date examTime;

	private BigDecimal heat;

	private BigDecimal pressure;

	private BigDecimal sphygmus;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to OutpatientRegistration
	@ManyToOne
	@JoinColumn(name="REG_ID")
	private OutpatientRegistration outpatientRegistration;

	//bi-directional many-to-one association to OutpatientRegistration
	@OneToMany(mappedBy="examination")
	private List<OutpatientRegistration> outpatientRegistrations;

	public Examination() {
	}

	public String getExamId() {
		return this.examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public Date getExamTime() {
		return this.examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	public BigDecimal getHeat() {
		return this.heat;
	}

	public void setHeat(BigDecimal heat) {
		this.heat = heat;
	}

	public BigDecimal getPressure() {
		return this.pressure;
	}

	public void setPressure(BigDecimal pressure) {
		this.pressure = pressure;
	}

	public BigDecimal getSphygmus() {
		return this.sphygmus;
	}

	public void setSphygmus(BigDecimal sphygmus) {
		this.sphygmus = sphygmus;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public OutpatientRegistration getOutpatientRegistration() {
		return this.outpatientRegistration;
	}

	public void setOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		this.outpatientRegistration = outpatientRegistration;
	}

	public List<OutpatientRegistration> getOutpatientRegistrations() {
		return this.outpatientRegistrations;
	}

	public void setOutpatientRegistrations(List<OutpatientRegistration> outpatientRegistrations) {
		this.outpatientRegistrations = outpatientRegistrations;
	}

	public OutpatientRegistration addOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().add(outpatientRegistration);
		outpatientRegistration.setExamination(this);

		return outpatientRegistration;
	}

	public OutpatientRegistration removeOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().remove(outpatientRegistration);
		outpatientRegistration.setExamination(null);

		return outpatientRegistration;
	}

}