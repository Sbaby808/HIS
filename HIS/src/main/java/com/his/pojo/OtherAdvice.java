package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OTHER_ADVICE database table.
 * 
 */
@Entity
@Table(name="OTHER_ADVICE")
@NamedQuery(name="OtherAdvice.findAll", query="SELECT o FROM OtherAdvice o")
public class OtherAdvice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OTH_ADV_ID")
	private String othAdvId;

	@Column(name="OTH_ADV_CONTENT")
	private String othAdvContent;

	//bi-directional many-to-one association to SolveScheme
	@ManyToOne
	@JoinColumn(name="SCHE_ID")
	private SolveScheme solveScheme;

	public OtherAdvice() {
	}

	public String getOthAdvId() {
		return this.othAdvId;
	}

	public void setOthAdvId(String othAdvId) {
		this.othAdvId = othAdvId;
	}

	public String getOthAdvContent() {
		return this.othAdvContent;
	}

	public void setOthAdvContent(String othAdvContent) {
		this.othAdvContent = othAdvContent;
	}

	public SolveScheme getSolveScheme() {
		return this.solveScheme;
	}

	public void setSolveScheme(SolveScheme solveScheme) {
		this.solveScheme = solveScheme;
	}

}