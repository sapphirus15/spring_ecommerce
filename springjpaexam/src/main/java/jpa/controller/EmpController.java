package jpa.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import jpa.domain.Emp;
import jpa.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	EmpService empService;
	
	// http://localhost:8080/emp/get/2
	@RequestMapping(value = "/get/{empno}", method = RequestMethod.GET)
	public Emp get(@PathVariable Integer empno) {
		return empService.getEmp(empno);
	}
	
	// http://localhost:8080/emp/save.do?ename=홍길동
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public Emp add(Emp emp) {
		return empService.save(emp);
	}
	
	// http://localhost:8080/emp/save/새길동
	@RequestMapping(value = "/save/{ename}", method = RequestMethod.GET)
	public Emp add2(@PathVariable String ename) {
		return empService.save(new Emp(ename));
	}
	
	// http://localhost:8080/emp/delete/2
	@RequestMapping(value = "/delete/{empno}", method = RequestMethod.GET)
	public void delete(@PathVariable Integer empno) {
		empService.delete(empno);
	}
	
	// http://localhost:8080/emp/getall.do
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<Emp> getAll() {
		return empService.getAllEmp();
	}
}