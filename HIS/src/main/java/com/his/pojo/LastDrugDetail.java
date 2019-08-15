package com.his.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LastDrugDetail {
	
	private BigDecimal hosPreDnum;
	
	private String hosPreDunit;
	
	private DrugInformation drugInformation;

	private HosPrescription hosPrescription;

	

	public LastDrugDetail(BigDecimal hosPreDnum, String hosPreDunit, DrugInformation drugInformation,
			HosPrescription hosPrescription) {
		super();
		this.hosPreDnum = hosPreDnum;
		this.hosPreDunit = hosPreDunit;
		this.drugInformation = drugInformation;
		this.hosPrescription = hosPrescription;
	}

	public BigDecimal getHosPreDnum() {
		return hosPreDnum;
	}

	public void setHosPreDnum(BigDecimal hosPreDnum) {
		this.hosPreDnum = hosPreDnum;
	}

	public String getHosPreDunit() {
		return hosPreDunit;
	}

	public void setHosPreDunit(String hosPreDunit) {
		this.hosPreDunit = hosPreDunit;
	}

	public DrugInformation getDrugInformation() {
		return drugInformation;
	}

	public void setDrugInformation(DrugInformation drugInformation) {
		this.drugInformation = drugInformation;
	}

	public HosPrescription getHosPrescription() {
		return hosPrescription;
	}

	public void setHosPrescription(HosPrescription hosPrescription) {
		this.hosPrescription = hosPrescription;
	}

	

}
