package com.sinaure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sinaure.model.MutationsLocView;
import com.sinaure.model.MutationsParcelView;

@Repository
public interface MutationsParcelRepository extends JpaRepository<MutationsParcelView, Long> {

//	@Query("SELECT p FROM InstantParking AS p WHERE within(p.location, :polygon) = TRUE")
//	List<MutationsAllView> findWithin(@Param("polygon") Polygon polygon);
	
	@Query("SELECT m FROM MutationsParcelView AS m WHERE codtypbien in (20,21,221,222,223,229,2311,2312,2313,2319,232,233,239) AND codinsee = :codinsee")
	List<MutationsParcelView> findTerrainsByCodinsee(@Param("codinsee") String codinsee);
	
	@Query("SELECT m FROM MutationsParcelView AS m WHERE codtypbien in (101,102,110,1110,1111,1112,1113)  AND codinsee = :codinsee")
	List<MutationsParcelView> findMaisonsByCodinsee(@Param("codinsee") String codinsee);

	@Query("SELECT m FROM MutationsParcelView AS m WHERE codtypbien in (120,1210,12110,12111, 12112,12113,12115,12120,12121,12122,12123,12124,12125,12130,12131,12132,12133,12134,12135,1214,1220,1221,1222,1223,1224,1229,123) AND codinsee = :codinsee")
	List<MutationsParcelView> findAptByCodinsee(@Param("codinsee") String codinsee);
	
	
}