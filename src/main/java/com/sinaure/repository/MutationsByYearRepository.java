package com.sinaure.repository;

import com.sinaure.model.MutationsByYearId;
import com.sinaure.model.MutationsParcel06;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MutationsByYearRepository extends JpaRepository<MutationsParcel06, MutationsByYearId> {
    /*@Query("SELECT p FROM MutationsParcel06 AS p WHERE p.code_com = :code_com")
    List<MutationsParcel06> findByCode(@Param("code_com") String code_com);*/
}
