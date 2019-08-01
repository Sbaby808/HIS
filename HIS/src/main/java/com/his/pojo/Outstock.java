package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OUTSTOCK database table.
 * 
 */
@Entity
@NamedQuery(name="Outstock.findAll", query="SELECT o FROM Outstock o")
public class Outstock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CK_ID")
	private String ckId;

	@Temporal(TemporalType.DATE)
	@Column(name="CK_TIME")
	private Date ckTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to OutstockDetail
	@OneToMany(mappedBy="outstock")
	private List<OutstockDetail> outstockDetails;

	public Outstock() {
	}

	public String getCkId() {
		return this.ckId;
	}

	public void setCkId(String ckId) {
		this.ckId = ckId;
	}

	public Date getCkTime() {
		return this.ckTime;
	}

	public void setCkTime(Date ckTime) {
		this.ckTime = ckTime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public List<OutstockDetail> getOutstockDetails() {
		return this.outstockDetails;
	}

	public void setOutstockDetails(List<OutstockDetail> outstockDetails) {
		this.outstockDetails = outstockDetails;
	}

	public OutstockDetail addOutstockDetail(OutstockDetail outstockDetail) {
		getOutstockDetails().add(outstockDetail);
		outstockDetail.setOutstock(this);

		return outstockDetail;
	}

	public OutstockDetail removeOutstockDetail(OutstockDetail outstockDetail) {
		getOutstockDetails().remove(outstockDetail);
		outstockDetail.setOutstock(null);

		return outstockDetail;
	}

}