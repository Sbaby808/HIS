package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the HOS_DRUG_RECORD database table.
 * 
 */
@Entity
@Table(name="HOS_DRUG_RECORD")
@NamedQuery(name="HosDrugRecord.findAll", query="SELECT h FROM HosDrugRecord h")
public class HosDrugRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_DRUG_RID")
	private String hosDrugRid;

	@Column(name="HOS_DRUG_NOTE")
	private String hosDrugNote;

	@Column(name="HOS_DRUG_NUM")
	private BigDecimal hosDrugNum;

	@Temporal(TemporalType.DATE)
	@Column(name="HOS_DRUG_TIME")
	private Date hosDrugTime;

	@Column(name="HOS_DRUG_UNIT")
	private String hosDrugUnit;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to HosPrescription
	@ManyToOne
	@JoinColumn(name="HOS_PRE_ID")
	private HosPrescription hosPrescription;

	public HosDrugRecord() {
	}

	public String getHosDrugRid() {
		return this.hosDrugRid;
	}

	public void setHosDrugRid(String hosDrugRid) {
		this.hosDrugRid = hosDrugRid;
	}

	public String getHosDrugNote() {
		return this.hosDrugNote;
	}

	public void setHosDrugNote(String hosDrugNote) {
		this.hosDrugNote = hosDrugNote;
	}

	public BigDecimal getHosDrugNum() {
		return this.hosDrugNum;
	}

	public void setHosDrugNum(BigDecimal hosDrugNum) {
		this.hosDrugNum = hosDrugNum;
	}

	public Date getHosDrugTime() {
		return this.hosDrugTime;
	}

	public void setHosDrugTime(Date hosDrugTime) {
		this.hosDrugTime = hosDrugTime;
	}

	public String getHosDrugUnit() {
		return this.hosDrugUnit;
	}

	public void setHosDrugUnit(String hosDrugUnit) {
		this.hosDrugUnit = hosDrugUnit;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public HosPrescription getHosPrescription() {
		return this.hosPrescription;
	}

	public void setHosPrescription(HosPrescription hosPrescription) {
		this.hosPrescription = hosPrescription;
	}

}