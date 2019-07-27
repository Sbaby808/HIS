package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ILLNESS database table.
 * 
 */
@Entity
@NamedQuery(name="Illness.findAll", query="SELECT i FROM Illness i")
public class Illness implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ILL_ID")
	private String illId;

	@Column(name="DEPUTY_CODE")
	private String deputyCode;

	@Column(name="ILL_CODE")
	private String illCode;

	@Column(name="ILL_DESC")
	private String illDesc;

	@Column(name="ILL_NAME")
	private String illName;

	@Column(name="ILL_VOCODE")
	private String illVocode;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="illness")
	private List<History> histories;

	public Illness() {
	}

	public String getIllId() {
		return this.illId;
	}

	public void setIllId(String illId) {
		this.illId = illId;
	}

	public String getDeputyCode() {
		return this.deputyCode;
	}

	public void setDeputyCode(String deputyCode) {
		this.deputyCode = deputyCode;
	}

	public String getIllCode() {
		return this.illCode;
	}

	public void setIllCode(String illCode) {
		this.illCode = illCode;
	}

	public String getIllDesc() {
		return this.illDesc;
	}

	public void setIllDesc(String illDesc) {
		this.illDesc = illDesc;
	}

	public String getIllName() {
		return this.illName;
	}

	public void setIllName(String illName) {
		this.illName = illName;
	}

	public String getIllVocode() {
		return this.illVocode;
	}

	public void setIllVocode(String illVocode) {
		this.illVocode = illVocode;
	}

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setIllness(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setIllness(null);

		return history;
	}

}