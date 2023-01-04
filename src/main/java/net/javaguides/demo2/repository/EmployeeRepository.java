package net.javaguides.demo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.demo2.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
  