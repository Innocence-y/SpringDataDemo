package com.imooc.repository;

import com.imooc.domain.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * 描述:测试类
 *
 * @author 闫文强
 * @create 2018-03-06 16:27
 */
public class EmployeeJpaSpecifictionExecutorRepositoryTest {
	private ApplicationContext ctx = null;
	private EmployeeJpaSpecifictionExecutorRepository employeeJpaSpecifictionExecutorRepository= null;

	@Before
	public void setup() {
		ctx = new ClassPathXmlApplicationContext("beans-new.xml");
		employeeJpaSpecifictionExecutorRepository = ctx.getBean(EmployeeJpaSpecifictionExecutorRepository.class);
		System.out.println("setup");
	}

	@After
	public void tearDown() {
		ctx = null;
		System.out.println("tearDown");
	}

	/**
	 * 1)分页
	 * 2）排序
	 * 3）查询条件
	 */

	@Test
	public void testQuery(){
		Sort.Order order=new Sort.Order(Sort.Direction.DESC,"id");
		Sort sort=new Sort(order);
		Pageable pageable=new PageRequest(0,5,sort);
		/**
		 * 匿名内部类
		 */

		Specification<Employee> specification=new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root,
										 CriteriaQuery<?> criteriaQuery,
										 CriteriaBuilder criteriaBuilder) {
				Path<Object> path= root.get("age");
				return criteriaBuilder.gt(path,50);
			}
		};
 		Page<Employee> page=employeeJpaSpecifictionExecutorRepository.findAll(pageable);
		System.out.println("查询的总页数"+ page.getTotalPages());
		System.out.println("查询的总记录数"+ page.getTotalElements());
		System.out.println("查询的当前第几页"+ page.getNumber() + 1);
		System.out.println("查询的当前页面的集合"+ page.getContent());
		System.out.println("查询的当前页面的记录数"+ page.getNumberOfElements());

	}

}