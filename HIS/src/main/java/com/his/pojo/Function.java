package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "FUNCTION" database table.
 * 
 */
@Entity
@Table(name="\"FUNCTION\"")
@NamedQuery(name="Function.findAll", query="SELECT f FROM Function f")
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FUNCTION_ID")
	private String functionId;

	@Column(name="FUNCTION_NAME")
	private String functionName;

	//bi-directional many-to-one association to FunctionClassify
	@ManyToOne
	@JoinColumn(name="FUNC_CLASSIFY_ID")
	private FunctionClassify functionClassify;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="functions")
	private List<Role> roles;

	public Function() {
	}

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public FunctionClassify getFunctionClassify() {
		return this.functionClassify;
	}

	public void setFunctionClassify(FunctionClassify functionClassify) {
		this.functionClassify = functionClassify;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}