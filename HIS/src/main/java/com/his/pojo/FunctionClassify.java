package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FUNCTION_CLASSIFY database table.
 * 
 */
@Entity
@Table(name="FUNCTION_CLASSIFY")
@NamedQuery(name="FunctionClassify.findAll", query="SELECT f FROM FunctionClassify f")
public class FunctionClassify implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FUNC_CLASSIFY_ID")
	private String funcClassifyId;

	@Column(name="FUNC_CLASSIFY_NAME")
	private String funcClassifyName;

	//bi-directional many-to-one association to Function
	@OneToMany(mappedBy="functionClassify")
	private List<Function> functions;

	public FunctionClassify() {
	}

	public String getFuncClassifyId() {
		return this.funcClassifyId;
	}

	public void setFuncClassifyId(String funcClassifyId) {
		this.funcClassifyId = funcClassifyId;
	}

	public String getFuncClassifyName() {
		return this.funcClassifyName;
	}

	public void setFuncClassifyName(String funcClassifyName) {
		this.funcClassifyName = funcClassifyName;
	}

	public List<Function> getFunctions() {
		return this.functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public Function addFunction(Function function) {
		getFunctions().add(function);
		function.setFunctionClassify(this);

		return function;
	}

	public Function removeFunction(Function function) {
		getFunctions().remove(function);
		function.setFunctionClassify(null);

		return function;
	}

}