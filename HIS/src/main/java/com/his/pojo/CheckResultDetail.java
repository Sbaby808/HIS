package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CHECK_RESULT_DETAILS database table.
 * 
 */
@Entity
@Table(name="CHECK_RESULT_DETAILS")
@NamedQuery(name="CheckResultDetail.findAll", query="SELECT c FROM CheckResultDetail c")
public class CheckResultDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CheckResultDetailPK id;

	@Column(name="CHECK_ITEM_VAL")
	private String checkItemVal;

	//bi-directional many-to-one association to CheckItem
	@ManyToOne
	@JoinColumn(name="CHECK_ITEM_ID", insertable=false, updatable=false)
	private CheckItem checkItem;

	//bi-directional many-to-one association to CheckResultForm
	@ManyToOne
	@JoinColumn(name="CHECK_RESULT_ID", insertable=false, updatable=false)
	private CheckResultForm checkResultForm;

	public CheckResultDetail() {
	}

	public CheckResultDetailPK getId() {
		return this.id;
	}

	public void setId(CheckResultDetailPK id) {
		this.id = id;
	}

	public String getCheckItemVal() {
		return this.checkItemVal;
	}

	public void setCheckItemVal(String checkItemVal) {
		this.checkItemVal = checkItemVal;
	}

	public CheckItem getCheckItem() {
		return this.checkItem;
	}

	public void setCheckItem(CheckItem checkItem) {
		this.checkItem = checkItem;
	}

	public CheckResultForm getCheckResultForm() {
		return this.checkResultForm;
	}

	public void setCheckResultForm(CheckResultForm checkResultForm) {
		this.checkResultForm = checkResultForm;
	}

}