package com.imooc.repository;

import com.imooc.domain.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 描述:测试类
 *
 * @author 闫文强
 * @create 2018-03-06 15:42
 */
public class EmployeeJpaRepositoryTest {
	private ApplicationContext ctx = null;
	private EmployeeJpaRepository employeeJpaRepository = null;

	@Before
	public void setup() {
		ctx = new ClassPathXmlApplicationContext("beans-new.xml");
		employeeJpaRepository = ctx.getBean(EmployeeJpaRepository.class);
		System.out.println("setup");
	}

	@After
	public void tearDown() {
		ctx = null;
		System.out.println("tearDown");
	}

	@Test
	public  void  testFind(){
		Employee employee=employeeJpaRepository.findOne(99);
		System.out.println(employee.toString());
		System.out.println("employee(10):" + employeeJpaRepository.exists(10));
		System.out.println("employee(102):" + employeeJpaRepository.exists(102));
	}

	@Test
	public void testSave(){
		Employee employee=new Employee();
		employee.setId(101);
		employee.setAge(56);
		employee.setName("yan");
		employeeJpaRepository.save(employee);
	}

	@Test
	public void  testDelete(){
		employeeJpaRepository.delete(101);
	}

	@Test
	public void testFindAll(){
		List<Employee> employees=employeeJpaRepository.findAll();
		System.out.println(employees);
	}
}