package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CHECK_ITEM database table.
 * 
 */
@Entity
@Table(name="CHECK_ITEM")
@NamedQuery(name="CheckItem.findAll", query="SELECT c FROM CheckItem c")
public class CheckItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CHECK_ITEM_ID")
	private String checkItemId;

	@Column(name="CHECK_ITEM_STD")
	private String checkItemStd;
	
	@Column(name="ITEM_NAME")
	private String itemName;

	//bi-directional many-to-one association to CheckResultDetail
	@OneToMany(mappedBy="checkItem")
	private List<CheckResultDetail> checkResultDetails;

	public CheckItem() {
	}

	public String getCheckItemId() {
		return this.checkItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setCheckItemId(String checkItemId) {
		this.checkItemId = checkItemId;
	}

	public String getCheckItemStd() {
		return this.checkItemStd;
	}

	public void setCheckItemStd(String checkItemStd) {
		this.checkItemStd = checkItemStd;
	}

	public List<CheckResultDetail> getCheckResultDetails() {
		return this.checkResultDetails;
	}

	public void setCheckResultDetails(List<CheckResultDetail> checkResultDetails) {
		this.checkResultDetails = checkResultDetails;
	}

	public CheckResultDetail addCheckResultDetail(CheckResultDetail checkResultDetail) {
		getCheckResultDetails().add(checkResultDetail);
		checkResultDetail.setCheckItem(this);

		return checkResultDetail;
	}

	public CheckResultDetail removeCheckResultDetail(CheckResultDetail checkResultDetail) {
		getCheckResultDetails().remove(checkResultDetail);
		checkResultDetail.setCheckItem(null);

		return checkResultDetail;
	}

}