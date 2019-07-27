package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OUT_PRE_PAY database table.
 * 
 */
@Entity
@Table(name="OUT_PRE_PAY")
@NamedQuery(name="OutPrePay.findAll", query="SELECT o FROM OutPrePay o")
public class OutPrePay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OUT_PRE_PAY_ID")
	private String outPrePayId;

	@Temporal(TemporalType.DATE)
	@Column(name="OUT_PRE_PAY_TIME")
	private Date outPrePayTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to Prescription
	@ManyToOne
	@JoinColumn(name="PRESCRIPTION_ID")
	private Prescription prescription;

	//bi-directional many-to-one association to Prescription
	@OneToMany(mappedBy="outPrePay")
	private List<Prescription> prescriptions;

	public OutPrePay() {
	}

	public String getOutPrePayId() {
		return this.outPrePayId;
	}

	public void setOutPrePayId(String outPrePayId) {
		this.outPrePayId = outPrePayId;
	}

	public Date getOutPrePayTime() {
		return this.outPrePayTime;
	}

	public void setOutPrePayTime(Date outPrePayTime) {
		this.outPrePayTime = outPrePayTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public List<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Prescription addPrescription(Prescription prescription) {
		getPrescriptions().add(prescription);
		prescription.setOutPrePay(this);

		return prescription;
	}

	public Prescription removePrescription(Prescription prescription) {
		getPrescriptions().remove(prescription);
		prescription.setOutPrePay(null);

		return prescription;
	}

}