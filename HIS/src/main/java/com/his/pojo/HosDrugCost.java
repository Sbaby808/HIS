package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
	@Column(name="HOS_CID")
	private String hosCid;

	@Temporal(TemporalType.DATE)
	@Column(name="HOST_CTIME")
	private Date hostCtime;

	//bi-directional many-to-one association to HosPrescription
	@ManyToOne
	@JoinColumn(name="HOS_PRE_ID")
	private HosPrescription hosPrescription;

	//bi-directional many-to-one association to HosPrescription
	@OneToMany(mappedBy="hosDrugCost")
	private List<HosPrescription> hosPrescriptions;

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

	public List<HosPrescription> getHosPrescriptions() {
		return this.hosPrescriptions;
	}

	public void setHosPrescriptions(List<HosPrescription> hosPrescriptions) {
		this.hosPrescriptions = hosPrescriptions;
	}

	public HosPrescription addHosPrescription(HosPrescription hosPrescription) {
		getHosPrescriptions().add(hosPrescription);
		hosPrescription.setHosDrugCost(this);

		return hosPrescription;
	}

	public HosPrescription removeHosPrescription(HosPrescription hosPrescription) {
		getHosPrescriptions().remove(hosPrescription);
		hosPrescription.setHosDrugCost(null);

		return hosPrescription;
	}

}