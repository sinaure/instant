package com.sinaure.instant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinaure.instant.common.model.MutationsByYearId;
import com.sinaure.instant.common.model.MutationsParcel06;

public interface MutationsByYearRepository extends JpaRepository<MutationsParcel06, MutationsByYearId> {
    /*@Query("SELECT p FROM MutationsParcel06 AS p WHERE p.code_com = :code_com")
    List<MutationsParcel06> findByCode(@Param("code_com") String code_com);*/
}
