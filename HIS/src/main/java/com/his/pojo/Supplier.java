package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


/**
 * The persistent class for the SUPPLIER database table.
 * 
 */
@Entity
@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GYS_ID")
	private String gysId;

	@Column(name="GYS_NAME")
	private String gysName;

	@Column(name="GYS_PLACE")
	private String gysPlace;

	@Column(name="GYS_TEL")
	private String gysTel;

	//bi-directional many-to-one association to DrugInformation
	@OneToMany(mappedBy="supplier")
	@JSONField(serialize = false)
	private List<DrugInformation> drugInformations;

	//bi-directional many-to-one association to PutstockBack
	@OneToMany(mappedBy="supplier")
	@JSONField(serialize = false)
	private List<PutstockBack> putstockBacks;

	public Supplier() {
	}

	public String getGysId() {
		return this.gysId;
	}

	public void setGysId(String gysId) {
		this.gysId = gysId;
	}

	public String getGysName() {
		return this.gysName;
	}

	public void setGysName(String gysName) {
		this.gysName = gysName;
	}

	public String getGysPlace() {
		return this.gysPlace;
	}

	public void setGysPlace(String gysPlace) {
		this.gysPlace = gysPlace;
	}

	public String getGysTel() {
		return this.gysTel;
	}

	public void setGysTel(String gysTel) {
		this.gysTel = gysTel;
	}

	public List<DrugInformation> getDrugInformations() {
		return this.drugInformations;
	}

	public void setDrugInformations(List<DrugInformation> drugInformations) {
		this.drugInformations = drugInformations;
	}

	public DrugInformation addDrugInformation(DrugInformation drugInformation) {
		getDrugInformations().add(drugInformation);
		drugInformation.setSupplier(this);

		return drugInformation;
	}

	public DrugInformation removeDrugInformation(DrugInformation drugInformation) {
		getDrugInformations().remove(drugInformation);
		drugInformation.setSupplier(null);

		return drugInformation;
	}

	public List<PutstockBack> getPutstockBacks1() {
		return this.putstockBacks;
	}

	public void setPutstockBacks1(List<PutstockBack> putstockBacks1) {
		this.putstockBacks = putstockBacks1;
	}

	public PutstockBack addPutstockBacks1(PutstockBack putstockBacks1) {
		getPutstockBacks1().add(putstockBacks1);
		putstockBacks1.setSupplier1(this);

		return putstockBacks1;
	}

	public PutstockBack removePutstockBacks1(PutstockBack putstockBacks1) {
		getPutstockBacks1().remove(putstockBacks1);
		putstockBacks1.setSupplier1(null);

		return putstockBacks1;
	}

}