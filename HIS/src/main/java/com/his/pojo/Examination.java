package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;
import java.util.Date;


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

	@JSONField(format = "yyyy-MM-dd")
	@Column(name="EXAM_TIME")
	private Date examTime;

	private BigDecimal heat;

	private BigDecimal pressure;

	private BigDecimal sphygmus;
	
	private BigDecimal pressureH;

	public BigDecimal getPressureH() {
		return pressureH;
	}

	public void setPressureH(BigDecimal pressureH) {
		this.pressureH = pressureH;
	}

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional one-to-one association to OutpatientRegistration
	@OneToOne
	@JSONField(serialize=false)
	@JoinColumn(name="REG_ID")
	private OutpatientRegistration outpatientRegistration;

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

}