package com.workshop.employee.repository;

import com.workshop.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByIdIn(List<Long> ids);
    List<Employee> findByNameIn(List<String> names);
}
