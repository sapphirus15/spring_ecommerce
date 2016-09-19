package jpa.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jpa.domain.Emp;
import jpa.repository.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpRepository empRepository;
	
	public Emp save(Emp e) {
		return empRepository.save(e);
	}
	
	public void delete(Integer empno) {
		empRepository.delete(empno);
	}
	
	public Emp getEmp(Integer empno) {
		return empRepository.findOne(empno);
	}
	
	public List<Emp> getAllEmp() {
		return empRepository.findAll();
	}
}