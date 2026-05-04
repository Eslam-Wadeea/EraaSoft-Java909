package com.workshop.employee.repository;

import com.workshop.employee.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    Optional<Email> findByName(String name);
    List<Email> findByNameIn(List<String> names);
    Optional<Email> findByContent(String content);
}
