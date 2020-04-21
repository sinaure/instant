package com.sinaure.repository;

import com.sinaure.model.MutationsByYear;

import java.util.List;

public interface MutationsByYearRepositoryCustom {
    public List<MutationsByYear> getMutationsByYear(String codinsee);
}
