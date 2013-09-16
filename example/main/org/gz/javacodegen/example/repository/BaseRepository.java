package org.gz.javacodegen.example.repository;

import org.gz.javacodegen.example.domain.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface BaseRepository extends JpaRepository<Base, Long>, JpaSpecificationExecutor<Base> {
}