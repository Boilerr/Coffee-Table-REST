package com.boilerr.coffeetablerest.repository;

import com.boilerr.coffeetablerest.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long>{
}
