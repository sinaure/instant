package com.sinaure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.sinaure.model.MutationByYear;

@NoRepositoryBean
public interface AbstractMutationByYearRepository<T extends MutationByYear> 
extends CrudRepository<T, Integer> {

  
}