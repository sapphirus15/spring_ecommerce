package demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emp")
public class Emp {
	private Long empno;
	private String ename;
	private Dept dept;
	
	public Emp() {}
	public Emp(String ename) {
		this.ename = ename;
	}
	public Emp(String ename, Dept dept) {
		this.ename = ename;
		this.dept = dept;
	}
	
	@Id
	@GeneratedValue
	public Long getEmpno() {
		return empno;
	}
	public void setEmpno(Long empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	@ManyToOne
	@JoinColumn(name="deptno")
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String toString() {
		String s = String.format("EMP[empno = %d, ename = '%s', deptno=%d]%n", empno, ename, getDept().getDeptno());
		return s;
	}
}

