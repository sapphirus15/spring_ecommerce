package demo;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import demo.model.Dept;
import demo.model.Emp;
import demo.repository.DeptRepository;

@SpringBootApplication
public class ManytooneApplication implements CommandLineRunner {
	private static final Logger logger =LoggerFactory.getLogger(ManytooneApplication.class);
	
	@Autowired
	private DeptRepository deptRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ManytooneApplication.class, args);
	}
		
	@Override
	@Transactional
	public void run(String...strings) throws Exception {
		
		Dept d1 = new Dept("교육팀");
		Emp e1 = new Emp("김교육", d1);
		Emp e2 = new Emp("나교육", d1);
		
		d1.setEmps(new HashSet<Emp>() {
			{
				add(e1);
				add(e2);
			}
		});
		
		//Dept에2번부서가만들어지고Emp의deptno는NULL로입력
		Dept d2 = new Dept("개발팀", new HashSet<Emp>() {
			{
				add(new Emp("김개발"));
				add(new Emp("나개발"));
			}
		});
		
		//Dept, Emp 저장
		deptRepository.save(new HashSet<Dept>() {
			{
				add(d1);
				add(d2);
			}
		});
		
		for(Dept d : deptRepository.findAll()) {
			logger.info(d.toString());
		}
				
		Dept d3 = deptRepository.findOne(1L);
		Set<Emp> emps = d3.getEmps();
		for(Emp e : emps) {
			logger.info(e.toString());
		}
		
		deptRepository.delete(d3);
	}
}
