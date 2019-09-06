package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the OUTSTOCK_DETAILS database table.
 * 
 */
@Entity
@Table(name="OUTSTOCK_DETAILS")
@NamedQuery(name="OutstockDetail.findAll", query="SELECT o FROM OutstockDetail o")
public class OutstockDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OutstockDetailPK id;

	@Column(name="YKOUT_NUM")
	private BigDecimal ykoutNum;
	
	@Column(name="STATE")
	private String state;

	//bi-directional many-to-one association to DrugWarehouse
	@ManyToOne
	@JoinColumn(name="PCKC_ID", insertable=false, updatable=false)
	private DrugWarehouse drugWarehouse;

	//bi-directional many-to-one association to Outstock
	@ManyToOne
	@JoinColumn(name="CK_ID", insertable=false, updatable=false)
	private Outstock outstock;

	public OutstockDetail() {
	}

	public OutstockDetailPK getId() {
		return this.id;
	}

	public void setId(OutstockDetailPK id) {
		this.id = id;
	}

	public BigDecimal getYkoutNum() {
		return this.ykoutNum;
	}

	public void setYkoutNum(BigDecimal ykoutNum) {
		this.ykoutNum = ykoutNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public DrugWarehouse getDrugWarehouse() {
		return this.drugWarehouse;
	}

	public void setDrugWarehouse(DrugWarehouse drugWarehouse) {
		this.drugWarehouse = drugWarehouse;
	}

	public Outstock getOutstock() {
		return this.outstock;
	}

	public void setOutstock(Outstock outstock) {
		this.outstock = outstock;
	}

}