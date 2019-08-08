package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


/**
 * The persistent class for the DRUG_MINOR_DEFECTS database table.
 * 
 */
@Entity
@Table(name="DRUG_MINOR_DEFECTS")
@NamedQuery(name="DrugMinorDefect.findAll", query="SELECT d FROM DrugMinorDefect d")
public class DrugMinorDefect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MINOR_DEFECTS_ID")
	private String minorDefectsId;

	@Column(name="MINOR_DEFECTS_NAME")
	private String minorDefectsName;

	//bi-directional many-to-one association to DrugSubclass
	@OneToMany(mappedBy="drugMinorDefect")
	@JSONField(serialize = false)
	private List<DrugSubclass> drugSubclasses;

	public DrugMinorDefect() {
	}

	public String getMinorDefectsId() {
		return this.minorDefectsId;
	}

	public void setMinorDefectsId(String minorDefectsId) {
		this.minorDefectsId = minorDefectsId;
	}

	public String getMinorDefectsName() {
		return this.minorDefectsName;
	}

	public void setMinorDefectsName(String minorDefectsName) {
		this.minorDefectsName = minorDefectsName;
	}

	public List<DrugSubclass> getDrugSubclasses() {
		return this.drugSubclasses;
	}

	public void setDrugSubclasses(List<DrugSubclass> drugSubclasses) {
		this.drugSubclasses = drugSubclasses;
	}

	public DrugSubclass addDrugSubclass(DrugSubclass drugSubclass) {
		getDrugSubclasses().add(drugSubclass);
		drugSubclass.setDrugMinorDefect(this);

		return drugSubclass;
	}

	public DrugSubclass removeDrugSubclass(DrugSubclass drugSubclass) {
		getDrugSubclasses().remove(drugSubclass);
		drugSubclass.setDrugMinorDefect(null);

		return drugSubclass;
	}

}