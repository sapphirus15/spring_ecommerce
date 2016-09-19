package jpa.service;
import java.util.List;
import jpa.domain.Emp;

public interface EmpService {
	Emp save(Emp e);
	void delete(Integer empno);
	Emp getEmp(Integer empno);
	List<Emp> getAllEmp();
}