package com.sinaure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sinaure.model.MutationsByCodinsee;
import com.sinaure.model.MutationsParcel06155;

@Repository
public interface StatsRepository extends AbstractMutationByYearRepository<MutationsParcel06155> {
	@Query("SELECT s FROM #{#entityName} AS s WHERE codinsee = :codinsee")
	List<MutationsByCodinsee> statsByCodinsee(@Param("codinsee") String codinsee);	
}