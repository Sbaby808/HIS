package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;

import java.util.List;


/**
 * The persistent class for the TECHNICAL_POST database table.
 * 
 */
@Entity
@Table(name="TECHNICAL_POST")
@NamedQuery(name="TechnicalPost.findAll", query="SELECT t FROM TechnicalPost t")
public class TechnicalPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TP_ID")
	private String tpId;

	@Column(name="TP_NAME")
	private String tpName;

	//bi-directional many-to-one association to EmpInformation
	@JSONField(serialize=false)
	@OneToMany(mappedBy="technicalPost")
	private List<EmpInformation> empInformations;

	//bi-directional many-to-one association to OutpatientRegistration
	@OneToMany(mappedBy="technicalPost")
	@JSONField(serialize = false)
	private List<OutpatientRegistration> outpatientRegistrations;

	public TechnicalPost() {
	}

	public String getTpId() {
		return this.tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public String getTpName() {
		return this.tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public List<EmpInformation> getEmpInformations() {
		return this.empInformations;
	}

	public void setEmpInformations(List<EmpInformation> empInformations) {
		this.empInformations = empInformations;
	}

	public EmpInformation addEmpInformation(EmpInformation empInformation) {
		getEmpInformations().add(empInformation);
		empInformation.setTechnicalPost(this);

		return empInformation;
	}

	public EmpInformation removeEmpInformation(EmpInformation empInformation) {
		getEmpInformations().remove(empInformation);
		empInformation.setTechnicalPost(null);

		return empInformation;
	}

	public List<OutpatientRegistration> getOutpatientRegistrations() {
		return this.outpatientRegistrations;
	}

	public void setOutpatientRegistrations(List<OutpatientRegistration> outpatientRegistrations) {
		this.outpatientRegistrations = outpatientRegistrations;
	}

	public OutpatientRegistration addOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().add(outpatientRegistration);
		outpatientRegistration.setTechnicalPost(this);

		return outpatientRegistration;
	}

	public OutpatientRegistration removeOutpatientRegistration(OutpatientRegistration outpatientRegistration) {
		getOutpatientRegistrations().remove(outpatientRegistration);
		outpatientRegistration.setTechnicalPost(null);

		return outpatientRegistration;
	}

}