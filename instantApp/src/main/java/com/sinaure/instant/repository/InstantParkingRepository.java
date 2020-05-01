package com.sinaure.instant.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sinaure.instant.common.model.InstantParking;
import com.vividsolutions.jts.geom.Polygon;

@Repository
public interface InstantParkingRepository extends JpaRepository<InstantParking, Long> {

	@Query("SELECT p FROM InstantParking AS p WHERE within(p.location, :polygon) = TRUE")
	List<InstantParking> findWithin(@Param("polygon") Polygon polygon);
	
	@Query("SELECT p FROM InstantParking AS p WHERE name = :name")
	List<InstantParking> findByName(@Param("name") String name);
	
	@Query("SELECT p FROM InstantParking AS p WHERE name = :name and observedAt < :end and observedAt > :start")
	List<InstantParking> findByNameBetween(@Param("name") String name, @Param("start") Timestamp start, @Param("end") Timestamp end);
	
	
}