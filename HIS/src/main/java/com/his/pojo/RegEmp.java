package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REG_EMP database table.
 * 
 */
@Entity
@Table(name="REG_EMP")
@NamedQuery(name="RegEmp.findAll", query="SELECT r FROM RegEmp r")
public class RegEmp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RegEmpPK id;

	@Column(name="REG_DUTY")
	private String regDuty;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH", insertable=false, updatable=false)
	private EmpInformation empInformation = null;

	//bi-directional many-to-one association to OutpatientRegistration
	@ManyToOne
	@JoinColumn(name="REG_ID", insertable=false, updatable=false)
	private OutpatientRegistration outpatientRegistration = null;

	public RegEmp() {
	}

	public RegEmpPK getId() {
		return this.id;
	}

	public void setId(RegEmpPK id) {
		this.id = id;
	}

	public String getRegDuty() {
		return this.regDuty;
	}

	public void setRegDuty(String regDuty) {
		this.regDuty = regDuty;
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