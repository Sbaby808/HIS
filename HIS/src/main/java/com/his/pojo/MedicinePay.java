package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the MEDICINE_PAY database table.
 * 
 */
@Entity
@Table(name="MEDICINE_PAY")
@NamedQuery(name="MedicinePay.findAll", query="SELECT m FROM MedicinePay m")
public class MedicinePay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MEDICINE_PAY_ID")
	private String medicinePayId;

	@Column(name="MEDICINE_PAY_DESC")
	private String medicinePayDesc;

	@Column(name="MEDICINE_PAY_NAME")
	private String medicinePayName;

	@Column(name="MEDICINE_PAY_PRICE")
	private BigDecimal medicinePayPrice;

	//bi-directional many-to-one association to DrugInformation
	@OneToMany(mappedBy="medicinePay")
	private List<DrugInformation> drugInformations;

	//bi-directional many-to-one association to DrugInformation
	@ManyToOne
	@JoinColumn(name="YP_ID")
	private DrugInformation drugInformation;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	public MedicinePay() {
	}

	public String getMedicinePayId() {
		return this.medicinePayId;
	}

	public void setMedicinePayId(String medicinePayId) {
		this.medicinePayId = medicinePayId;
	}

	public String getMedicinePayDesc() {
		return this.medicinePayDesc;
	}

	public void setMedicinePayDesc(String medicinePayDesc) {
		this.medicinePayDesc = medicinePayDesc;
	}

	public String getMedicinePayName() {
		return this.medicinePayName;
	}

	public void setMedicinePayName(String medicinePayName) {
		this.medicinePayName = medicinePayName;
	}

	public BigDecimal getMedicinePayPrice() {
		return this.medicinePayPrice;
	}

	public void setMedicinePayPrice(BigDecimal medicinePayPrice) {
		this.medicinePayPrice = medicinePayPrice;
	}

	public List<DrugInformation> getDrugInformations() {
		return this.drugInformations;
	}

	public void setDrugInformations(List<DrugInformation> drugInformations) {
		this.drugInformations = drugInformations;
	}

	public DrugInformation addDrugInformation(DrugInformation drugInformation) {
		getDrugInformations().add(drugInformation);
		drugInformation.setMedicinePay(this);

		return drugInformation;
	}

	public DrugInformation removeDrugInformation(DrugInformation drugInformation) {
		getDrugInformations().remove(drugInformation);
		drugInformation.setMedicinePay(null);

		return drugInformation;
	}

	public DrugInformation getDrugInformation() {
		return this.drugInformation;
	}

	public void setDrugInformation(DrugInformation drugInformation) {
		this.drugInformation = drugInformation;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}