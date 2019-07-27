package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CHECK_RESULT_FORM database table.
 * 
 */
@Entity
@Table(name="CHECK_RESULT_FORM")
@NamedQuery(name="CheckResultForm.findAll", query="SELECT c FROM CheckResultForm c")
public class CheckResultForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CHECK_RESULT_ID")
	private String checkResultId;

	@Column(name="CHECK_COMMENT")
	private String checkComment;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_TIME")
	private Date checkTime;

	//bi-directional many-to-one association to CheckResultDetail
	@OneToMany(mappedBy="checkResultForm")
	private List<CheckResultDetail> checkResultDetails;

	//bi-directional many-to-one association to EmpInformation
	@ManyToOne
	@JoinColumn(name="YGXH")
	private EmpInformation empInformation;

	public CheckResultForm() {
	}

	public String getCheckResultId() {
		return this.checkResultId;
	}

	public void setCheckResultId(String checkResultId) {
		this.checkResultId = checkResultId;
	}

	public String getCheckComment() {
		return this.checkComment;
	}

	public void setCheckComment(String checkComment) {
		this.checkComment = checkComment;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public List<CheckResultDetail> getCheckResultDetails() {
		return this.checkResultDetails;
	}

	public void setCheckResultDetails(List<CheckResultDetail> checkResultDetails) {
		this.checkResultDetails = checkResultDetails;
	}

	public CheckResultDetail addCheckResultDetail(CheckResultDetail checkResultDetail) {
		getCheckResultDetails().add(checkResultDetail);
		checkResultDetail.setCheckResultForm(this);

		return checkResultDetail;
	}

	public CheckResultDetail removeCheckResultDetail(CheckResultDetail checkResultDetail) {
		getCheckResultDetails().remove(checkResultDetail);
		checkResultDetail.setCheckResultForm(null);

		return checkResultDetail;
	}

	public EmpInformation getEmpInformation() {
		return this.empInformation;
	}

	public void setEmpInformation(EmpInformation empInformation) {
		this.empInformation = empInformation;
	}

}