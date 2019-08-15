package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name="HOS_DRUG_RID")
	private String hosDrugRid;

	@Column(name="HOS_DRUG_NOTE")
	private String hosDrugNote;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name="HOS_DRUG_TIME")
	private Date hosDrugTime;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	//bi-directional many-to-one association to HosPrescription
	@ManyToOne
	@JoinColumn(name="HOS_PRE_ID")
	private HosPrescription hosPrescription;
	
	//bi-directional many-to-one association to HosDrugRecord
	@OneToMany(mappedBy="hosDrugRecord")
	@JSONField(serialize=false)
	private List<HosDrugDetail> hosDrugDetails  ;
	
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

	public Date getHosDrugTime() {
		return this.hosDrugTime;
	}

	public void setHosDrugTime(Date hosDrugTime) {
		this.hosDrugTime = hosDrugTime;
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

	public List<HosDrugDetail> getHosDrugDetails() {
		return hosDrugDetails;
	}

	public void setHosDrugDetails(List<HosDrugDetail> hosDrugDetails) {
		this.hosDrugDetails = hosDrugDetails;
	}
	
	public HosDrugDetail addHosDrugDetail(HosDrugDetail hosDrugDetail) {
		getHosDrugDetails().add(hosDrugDetail);
		hosDrugDetail.setHosDrugRecord(this);

		return hosDrugDetail;
	}

	public HosDrugDetail removeHosDrugDetail(HosDrugDetail hosDrugDetail) {
		getHosDrugDetails().add(hosDrugDetail);
		hosDrugDetail.setHosDrugRecord(null);
		
		return hosDrugDetail;
	}

}