package org.lysygang.adapter.out.persistence.repository;

import org.lysygang.adapter.out.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
