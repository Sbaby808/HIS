package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * The persistent class for the HOS_DRUG_COST database table.
 * 
 */
@Entity
@Table(name="HOS_DRUG_COST")
@NamedQuery(name="HosDrugCost.findAll", query="SELECT h FROM HosDrugCost h")
public class HosDrugCost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name="HOS_CID")
	private String hosCid;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name="HOST_CTIME")
	private Date hostCtime;

	//bi-directional one-to-one association to HosPrescription
	@OneToOne
	@JoinColumn(name="HOS_PRE_ID")
	private HosPrescription hosPrescription;

	public HosDrugCost() {
	}

	public String getHosCid() {
		return this.hosCid;
	}

	public void setHosCid(String hosCid) {
		this.hosCid = hosCid;
	}

	public Date getHostCtime() {
		return this.hostCtime;
	}

	public void setHostCtime(Date hostCtime) {
		this.hostCtime = hostCtime;
	}

	public HosPrescription getHosPrescription() {
		return this.hosPrescription;
	}

	public void setHosPrescription(HosPrescription hosPrescription) {
		this.hosPrescription = hosPrescription;
	}

}