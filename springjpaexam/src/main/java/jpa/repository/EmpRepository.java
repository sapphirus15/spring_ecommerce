package jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa.domain.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
}