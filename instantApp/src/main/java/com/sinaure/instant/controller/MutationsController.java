package com.sinaure.instant.controller;

import com.sinaure.instant.common.model.MutationsByYearId;
import com.sinaure.instant.common.model.MutationsParcel06;
import com.sinaure.instant.repository.MutationsByYearRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("mutations")
public class MutationsController {
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");

	@Autowired
	private MutationsByYearRepository mutationsByYearRepository;

	@GetMapping("/")
	public ResponseEntity<List<MutationsParcel06>> getMutationsAll() {
		return new ResponseEntity<List<MutationsParcel06>>(mutationsByYearRepository.findAll(), HttpStatus.OK);
	}
	@GetMapping("/com/{code_com}")
	public ResponseEntity<List<MutationsParcel06>> getMutationsByCode(@PathVariable("code_com") Integer code_com) {
		MutationsParcel06 mutationsParcel06 = new MutationsParcel06();
		MutationsByYearId mutationsByYearId = new MutationsByYearId();
		mutationsByYearId.setCode_com(code_com);
		mutationsParcel06.setMutationsByYearId(mutationsByYearId);
		Example<MutationsParcel06> mutExample = Example.of(mutationsParcel06);
		return new ResponseEntity<List<MutationsParcel06>>(mutationsByYearRepository.findAll(mutExample), HttpStatus.OK);
	}
	@GetMapping("/annee/{annee}")
	public ResponseEntity<List<MutationsParcel06>> getMutationsByYear(@PathVariable("annee") Integer annee) {
		MutationsParcel06 mutationsParcel06 = new MutationsParcel06();
		MutationsByYearId mutationsByYearId = new MutationsByYearId();
		mutationsByYearId.setAnneemut(annee);
		mutationsParcel06.setMutationsByYearId(mutationsByYearId);
		Example<MutationsParcel06> mutExample = Example.of(mutationsParcel06);
		return new ResponseEntity<List<MutationsParcel06>>(mutationsByYearRepository.findAll(mutExample), HttpStatus.OK);
	}



}
