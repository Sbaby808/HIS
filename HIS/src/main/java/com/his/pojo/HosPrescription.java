package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the HOS_PRESCRIPTION database table.
 * 
 */
@Entity
@Table(name="HOS_PRESCRIPTION")
@NamedQuery(name="HosPrescription.findAll", query="SELECT h FROM HosPrescription h")
public class HosPrescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOS_PRE_ID")
	private String hosPreId;

	@Column(name="HOS_CID")
	private String hosCid;

	@Column(name="HOS_PRE_MONEY")
	private BigDecimal hosPreMoney;

	@Temporal(TemporalType.DATE)
	@Column(name="HOS_PRE_TIME")
	private Date hosPreTime;

	//bi-directional many-to-one association to HosDrugRecord
	@OneToMany(mappedBy="hosPrescription")
	private List<HosDrugRecord> hosDrugRecords;

	//bi-directional many-to-one association to HosPrescriptionDetail
	@OneToMany(mappedBy="hosPrescription")
	private List<HosPrescriptionDetail> hosPrescriptionDetails;

	//bi-directional one-to-one association to HosDiagnosticRecord
	@OneToOne
	@JoinColumn(name="HOS_DIAG_ID")
	private HosDiagnosticRecord hosDiagnosticRecord;

	//bi-directional one-to-one association to HosDrugCost
	@OneToOne(mappedBy="hosPrescription")
	private HosDrugCost hosDrugCost;

	public HosPrescription() {
	}

	public String getHosPreId() {
		return this.hosPreId;
	}

	public void setHosPreId(String hosPreId) {
		this.hosPreId = hosPreId;
	}

	public String getHosCid() {
		return this.hosCid;
	}

	public void setHosCid(String hosCid) {
		this.hosCid = hosCid;
	}

	public BigDecimal getHosPreMoney() {
		return this.hosPreMoney;
	}

	public void setHosPreMoney(BigDecimal hosPreMoney) {
		this.hosPreMoney = hosPreMoney;
	}

	public Date getHosPreTime() {
		return this.hosPreTime;
	}

	public void setHosPreTime(Date hosPreTime) {
		this.hosPreTime = hosPreTime;
	}

	public List<HosDrugRecord> getHosDrugRecords() {
		return this.hosDrugRecords;
	}

	public void setHosDrugRecords(List<HosDrugRecord> hosDrugRecords) {
		this.hosDrugRecords = hosDrugRecords;
	}

	public HosDrugRecord addHosDrugRecord(HosDrugRecord hosDrugRecord) {
		getHosDrugRecords().add(hosDrugRecord);
		hosDrugRecord.setHosPrescription(this);

		return hosDrugRecord;
	}

	public HosDrugRecord removeHosDrugRecord(HosDrugRecord hosDrugRecord) {
		getHosDrugRecords().remove(hosDrugRecord);
		hosDrugRecord.setHosPrescription(null);

		return hosDrugRecord;
	}

	public List<HosPrescriptionDetail> getHosPrescriptionDetails() {
		return this.hosPrescriptionDetails;
	}

	public void setHosPrescriptionDetails(List<HosPrescriptionDetail> hosPrescriptionDetails) {
		this.hosPrescriptionDetails = hosPrescriptionDetails;
	}

	public HosPrescriptionDetail addHosPrescriptionDetail(HosPrescriptionDetail hosPrescriptionDetail) {
		getHosPrescriptionDetails().add(hosPrescriptionDetail);
		hosPrescriptionDetail.setHosPrescription(this);

		return hosPrescriptionDetail;
	}

	public HosPrescriptionDetail removeHosPrescriptionDetail(HosPrescriptionDetail hosPrescriptionDetail) {
		getHosPrescriptionDetails().remove(hosPrescriptionDetail);
		hosPrescriptionDetail.setHosPrescription(null);

		return hosPrescriptionDetail;
	}

	public HosDiagnosticRecord getHosDiagnosticRecord() {
		return this.hosDiagnosticRecord;
	}

	public void setHosDiagnosticRecord(HosDiagnosticRecord hosDiagnosticRecord) {
		this.hosDiagnosticRecord = hosDiagnosticRecord;
	}

	public HosDrugCost getHosDrugCost() {
		return this.hosDrugCost;
	}

	public void setHosDrugCost(HosDrugCost hosDrugCost) {
		this.hosDrugCost = hosDrugCost;
	}

}