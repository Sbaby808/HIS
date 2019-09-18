package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the HOS_PAY_RECORD database table.
 * 
 */
@Entity
@Table(name="HOS_PAY_RECORD")
@NamedQuery(name="HosPayRecord.findAll", query="SELECT h FROM HosPayRecord h")
public class HosPayRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_PRID")
	private String hosPrid;

	@Column(name="HOS_PRMONEY")
	private BigDecimal hosPrmoney;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name="HOS_PRTIME")
	private Date hosPrtime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to HospitalizedPatient
	@ManyToOne
	@JoinColumn(name="HOSP_ID")
	private HospitalizedPatient hospitalizedPatient;

	public HosPayRecord() {
	}

	public String getHosPrid() {
		return this.hosPrid;
	}

	public void setHosPrid(String hosPrid) {
		this.hosPrid = hosPrid;
	}

	public BigDecimal getHosPrmoney() {
		return this.hosPrmoney;
	}

	public void setHosPrmoney(BigDecimal hosPrmoney) {
		this.hosPrmoney = hosPrmoney;
	}

	public Date getHosPrtime() {
		return this.hosPrtime;
	}

	public void setHosPrtime(Date hosPrtime) {
		this.hosPrtime = hosPrtime;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

	public HospitalizedPatient getHospitalizedPatient() {
		return this.hospitalizedPatient;
	}

	public void setHospitalizedPatient(HospitalizedPatient hospitalizedPatient) {
		this.hospitalizedPatient = hospitalizedPatient;
	}

}