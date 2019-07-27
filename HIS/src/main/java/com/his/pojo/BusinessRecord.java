package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BUSINESS_RECORD database table.
 * 
 */
@Entity
@Table(name="BUSINESS_RECORD")
@NamedQuery(name="BusinessRecord.findAll", query="SELECT b FROM BusinessRecord b")
public class BusinessRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BUSINESS_ID")
	private String businessId;

	@Temporal(TemporalType.DATE)
	@Column(name="BUS_EDATE")
	private Date busEdate;

	@Temporal(TemporalType.DATE)
	@Column(name="BUS_SDATE")
	private Date busSdate;

	@Column(name="OUT_DAYS")
	private BigDecimal outDays;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	public BusinessRecord() {
	}

	public String getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Date getBusEdate() {
		return this.busEdate;
	}

	public void setBusEdate(Date busEdate) {
		this.busEdate = busEdate;
	}

	public Date getBusSdate() {
		return this.busSdate;
	}

	public void setBusSdate(Date busSdate) {
		this.busSdate = busSdate;
	}

	public BigDecimal getOutDays() {
		return this.outDays;
	}

	public void setOutDays(BigDecimal outDays) {
		this.outDays = outDays;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}