package com.his.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DEPT database table.
 * 
 */
@Entity
@NamedQuery(name="Dept.findAll", query="SELECT d FROM Dept d")
public class Dept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="DEPT_NAME")
	private String deptName;

	//bi-directional many-to-one association to Department
	@OneToMany(mappedBy="dept")
	private List<Department> departments;

	//bi-directional many-to-one association to Medicine
	@OneToMany(mappedBy="dept")
	private List<Medicine> medicines;

	public Dept() {
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setDept(this);

		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setDept(null);

		return department;
	}

	public List<Medicine> getMedicines() {
		return this.medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Medicine addMedicine(Medicine medicine) {
		getMedicines().add(medicine);
		medicine.setDept(this);

		return medicine;
	}

	public Medicine removeMedicine(Medicine medicine) {
		getMedicines().remove(medicine);
		medicine.setDept(null);

		return medicine;
	}

}