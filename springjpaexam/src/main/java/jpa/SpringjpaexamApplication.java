package jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jpa.domain.Emp;
import jpa.service.EmpService;

@SpringBootApplication
public class SpringjpaexamApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringjpaexamApplication.class, args);
	}
	
	@Autowired
	EmpService empService;
	// 초기 데이터 생성
	public void run(String...args) {
		empService.save(new Emp("가길동"));
		empService.save(new Emp("나길동"));
		empService.save(new Emp("다길동"));
	}
}
