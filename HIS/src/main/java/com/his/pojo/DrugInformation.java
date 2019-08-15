package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the DRUG_INFORMATION database table.
 * 
 */
@Entity
@Table(name="DRUG_INFORMATION")
@NamedQuery(name="DrugInformation.findAll", query="SELECT d FROM DrugInformation d")
public class DrugInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="YP_ID")
	private String ypId;

	@Column(name="MEDICINE_PAY_ID")
	private String medicinePayId;

	@Column(name="YP_GUIGE")
	private String ypGuige;

	@Column(name="YP_NAME")
	private String ypName;
	
	@Column(name="VOCODE")
	private String vocode;

	@Column(name="YP_PRICE")
	private BigDecimal ypPrice;

	@Column(name="YP_SHELFLIFE")
	private String ypShelflife;

	@Column(name="YP_TYPE")
	private String ypType;

	//bi-directional many-to-one association to DrugSubclass
	@ManyToOne
	@JoinColumn(name="SUBCLASS_ID")
	private DrugSubclass drugSubclass;

	//bi-directional many-to-one association to Supplier
	@ManyToOne
	@JoinColumn(name="GYS_ID")
	private Supplier supplier;

	//bi-directional many-to-one association to DrugWarehouse
	@OneToMany(mappedBy="drugInformation")
	@JSONField(serialize=false)
	private List<DrugWarehouse> drugWarehouses;

	//bi-directional many-to-one association to HosPrescriptionDetail
	@OneToMany(mappedBy="drugInformation")
	@JSONField(serialize=false)
	private List<HosPrescriptionDetail> hosPrescriptionDetails;

	//bi-directional many-to-one association to OutPreItem
	@OneToMany(mappedBy="drugInformation")
	@JSONField(serialize=false)
	private List<OutPreItem> outPreItems;

	//bi-directional many-to-one association to PurchaseDetail
	@OneToMany(mappedBy="drugInformation")
	@JSONField(serialize=false)
	private List<PurchaseDetail> purchaseDetails;

	//bi-directional many-to-one association to PurCheDetail
	@OneToMany(mappedBy="drugInformation")
	@JSONField(serialize=false)
	private List<PurCheDetail> purCheDetails;

	//bi-directional many-to-one association to ReqDetail
	@OneToMany(mappedBy="drugInformation")
	@JSONField(serialize=false)
	private List<ReqDetail> reqDetails;
	
	//bi-directional many-to-one association to HosDrugDetail
	@OneToMany(mappedBy="drugInformation")
	@JSONField(serialize=false)
	private List<HosDrugDetail> hosDrugDetails;

	//bi-directional one-to-one association to MedicinePay
	@OneToOne(mappedBy="drugInformation")
	private MedicinePay medicinePay;

	public DrugInformation() {
	}

	public String getVocode() {
		return vocode;
	}

	public void setVocode(String vocode) {
		this.vocode = vocode;
	}

	public String getYpId() {
		return this.ypId;
	}

	public void setYpId(String ypId) {
		this.ypId = ypId;
	}

	public String getMedicinePayId() {
		return this.medicinePayId;
	}

	public void setMedicinePayId(String medicinePayId) {
		this.medicinePayId = medicinePayId;
	}

	public String getYpGuige() {
		return this.ypGuige;
	}

	public void setYpGuige(String ypGuige) {
		this.ypGuige = ypGuige;
	}

	public String getYpName() {
		return this.ypName;
	}

	public void setYpName(String ypName) {
		this.ypName = ypName;
	}

	public BigDecimal getYpPrice() {
		return this.ypPrice;
	}

	public void setYpPrice(BigDecimal ypPrice) {
		this.ypPrice = ypPrice;
	}

	public String getYpShelflife() {
		return this.ypShelflife;
	}

	public void setYpShelflife(String ypShelflife) {
		this.ypShelflife = ypShelflife;
	}

	public String getYpType() {
		return this.ypType;
	}

	public void setYpType(String ypType) {
		this.ypType = ypType;
	}

	public DrugSubclass getDrugSubclass() {
		return this.drugSubclass;
	}

	public void setDrugSubclass(DrugSubclass drugSubclass) {
		this.drugSubclass = drugSubclass;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<DrugWarehouse> getDrugWarehouses() {
		return this.drugWarehouses;
	}

	public void setDrugWarehouses(List<DrugWarehouse> drugWarehouses) {
		this.drugWarehouses = drugWarehouses;
	}

	public DrugWarehouse addDrugWarehous(DrugWarehouse drugWarehous) {
		getDrugWarehouses().add(drugWarehous);
		drugWarehous.setDrugInformation(this);

		return drugWarehous;
	}

	public DrugWarehouse removeDrugWarehous(DrugWarehouse drugWarehous) {
		getDrugWarehouses().remove(drugWarehous);
		drugWarehous.setDrugInformation(null);

		return drugWarehous;
	}

	public List<HosPrescriptionDetail> getHosPrescriptionDetails() {
		return this.hosPrescriptionDetails;
	}

	public void setHosPrescriptionDetails(List<HosPrescriptionDetail> hosPrescriptionDetails) {
		this.hosPrescriptionDetails = hosPrescriptionDetails;
	}

	public HosPrescriptionDetail addHosPrescriptionDetail(HosPrescriptionDetail hosPrescriptionDetail) {
		getHosPrescriptionDetails().add(hosPrescriptionDetail);
		hosPrescriptionDetail.setDrugInformation(this);

		return hosPrescriptionDetail;
	}

	public HosPrescriptionDetail removeHosPrescriptionDetail(HosPrescriptionDetail hosPrescriptionDetail) {
		getHosPrescriptionDetails().remove(hosPrescriptionDetail);
		hosPrescriptionDetail.setDrugInformation(null);

		return hosPrescriptionDetail;
	}

	public List<OutPreItem> getOutPreItems() {
		return this.outPreItems;
	}

	public void setOutPreItems(List<OutPreItem> outPreItems) {
		this.outPreItems = outPreItems;
	}

	public OutPreItem addOutPreItem(OutPreItem outPreItem) {
		getOutPreItems().add(outPreItem);
		outPreItem.setDrugInformation(this);

		return outPreItem;
	}

	public OutPreItem removeOutPreItem(OutPreItem outPreItem) {
		getOutPreItems().remove(outPreItem);
		outPreItem.setDrugInformation(null);

		return outPreItem;
	}

	public List<PurchaseDetail> getPurchaseDetails() {
		return this.purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public PurchaseDetail addPurchaseDetail(PurchaseDetail purchaseDetail) {
		getPurchaseDetails().add(purchaseDetail);
		purchaseDetail.setDrugInformation(this);

		return purchaseDetail;
	}

	public PurchaseDetail removePurchaseDetail(PurchaseDetail purchaseDetail) {
		getPurchaseDetails().remove(purchaseDetail);
		purchaseDetail.setDrugInformation(null);

		return purchaseDetail;
	}

	public List<PurCheDetail> getPurCheDetails() {
		return this.purCheDetails;
	}

	public void setPurCheDetails(List<PurCheDetail> purCheDetails) {
		this.purCheDetails = purCheDetails;
	}

	public PurCheDetail addPurCheDetail(PurCheDetail purCheDetail) {
		getPurCheDetails().add(purCheDetail);
		purCheDetail.setDrugInformation(this);

		return purCheDetail;
	}

	public PurCheDetail removePurCheDetail(PurCheDetail purCheDetail) {
		getPurCheDetails().remove(purCheDetail);
		purCheDetail.setDrugInformation(null);

		return purCheDetail;
	}

	public List<ReqDetail> getReqDetails() {
		return this.reqDetails;
	}

	public void setReqDetails(List<ReqDetail> reqDetails) {
		this.reqDetails = reqDetails;
	}

	public ReqDetail addReqDetail(ReqDetail reqDetail) {
		getReqDetails().add(reqDetail);
		reqDetail.setDrugInformation(this);

		return reqDetail;
	}

	public ReqDetail removeReqDetail(ReqDetail reqDetail) {
		getReqDetails().remove(reqDetail);
		reqDetail.setDrugInformation(null);

		return reqDetail;
	}
	

	public List<HosDrugDetail> getHosDrugDetails() {
		return hosDrugDetails;
	}

	public void setHosDrugDetails(List<HosDrugDetail> hosDrugDetails) {
		this.hosDrugDetails = hosDrugDetails;
	}

	public HosDrugDetail addHosDrugDetail(HosDrugDetail hosDrugDetail) {
		getHosDrugDetails().add(hosDrugDetail);
		hosDrugDetail.setDrugInformation(this);

		return hosDrugDetail;
	}

	public HosDrugDetail removeHosDrugDetail(HosDrugDetail hosDrugDetail) {
		getHosDrugDetails().add(hosDrugDetail);
		hosDrugDetail.setDrugInformation(null);

		return hosDrugDetail;
	}
	
	public MedicinePay getMedicinePay() {
		return this.medicinePay;
	}

	public void setMedicinePay(MedicinePay medicinePay) {
		this.medicinePay = medicinePay;
	}
	
	

}