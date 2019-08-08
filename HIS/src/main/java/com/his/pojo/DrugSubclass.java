package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


/**
 * The persistent class for the DRUG_SUBCLASS database table.
 * 
 */
@Entity
@Table(name="DRUG_SUBCLASS")
@NamedQuery(name="DrugSubclass.findAll", query="SELECT d FROM DrugSubclass d")
public class DrugSubclass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUBCLASS_ID")
	private String subclassId;

	@Column(name="SUBCLASS_NAME")
	private String subclassName;

	//bi-directional many-to-one association to DrugInformation
	@OneToMany(mappedBy="drugSubclass")
	@JSONField(serialize=false)
	private List<DrugInformation> drugInformations;

	//bi-directional many-to-one association to DrugMinorDefect
	@JSONField(serialize = false)
	@ManyToOne
	@JoinColumn(name="MINOR_DEFECTS_ID")
	private DrugMinorDefect drugMinorDefect;

	public DrugSubclass() {
	}

	public String getSubclassId() {
		return this.subclassId;
	}

	public void setSubclassId(String subclassId) {
		this.subclassId = subclassId;
	}

	public String getSubclassName() {
		return this.subclassName;
	}

	public void setSubclassName(String subclassName) {
		this.subclassName = subclassName;
	}

	public List<DrugInformation> getDrugInformations() {
		return this.drugInformations;
	}

	public void setDrugInformations(List<DrugInformation> drugInformations) {
		this.drugInformations = drugInformations;
	}

	public DrugInformation addDrugInformation(DrugInformation drugInformation) {
		getDrugInformations().add(drugInformation);
		drugInformation.setDrugSubclass(this);

		return drugInformation;
	}

	public DrugInformation removeDrugInformation(DrugInformation drugInformation) {
		getDrugInformations().remove(drugInformation);
		drugInformation.setDrugSubclass(null);

		return drugInformation;
	}

	public DrugMinorDefect getDrugMinorDefect() {
		return this.drugMinorDefect;
	}

	public void setDrugMinorDefect(DrugMinorDefect drugMinorDefect) {
		this.drugMinorDefect = drugMinorDefect;
	}

}