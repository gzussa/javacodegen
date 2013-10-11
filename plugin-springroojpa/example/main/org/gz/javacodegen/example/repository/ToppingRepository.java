package org.gz.javacodegen.args.example.repository;

import org.gz.javacodegen.args.example.domain.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long>, JpaSpecificationExecutor<Topping> {
}