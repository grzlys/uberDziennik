package org.lysygang.adapter.out.persistence.repository;

import org.lysygang.adapter.out.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
