package com.imooc.repository;

import com.imooc.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeJpaSpecifictionExecutorRepository extends JpaRepository<Employee,Integer>
                          ,JpaSpecificationExecutor<Employee>{


}
