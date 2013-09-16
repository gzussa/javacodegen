package org.gz.javacodegen.example.repository;

import org.gz.javacodegen.example.domain.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface PizzaRepository extends JpaRepository<PizzaOrder, Long>, JpaSpecificationExecutor<PizzaOrder> {
}