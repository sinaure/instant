package com.sinaure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sinaure.config.model.InstantParking;
import com.sinaure.config.model.Parking;
import com.vividsolutions.jts.geom.Polygon;

@Repository
public interface InstantParkingRepository extends JpaRepository<InstantParking, Long> {

	@Query("SELECT p FROM InstantParking AS p WHERE within(p.location, :polygon) = TRUE")
	List<Parking> findWithin(@Param("polygon") Polygon polygon);
}