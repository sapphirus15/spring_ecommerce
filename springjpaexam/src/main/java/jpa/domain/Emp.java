package jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Emp {
	@Id
	@GeneratedValue
	private Integer empno;
	private String ename;
	public Emp() {}
	public Emp(String ename) { this.ename = ename; }
	public Integer getEmpno() { return empno; }
	public void setEmpno(Integer empno) { this.empno = empno; }
	public String getEname() { return ename; }
	public void setEname(String ename) { this.ename = ename; }
}
