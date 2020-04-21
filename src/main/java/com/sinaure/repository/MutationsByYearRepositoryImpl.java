package com.sinaure.repository;

import com.sinaure.model.MutationsByYear;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class MutationsByYearRepositoryImpl implements MutationsByYearRepositoryCustom{
    private static String TABLE_PREFIX = "mutations_parcel_";
    @PersistenceContext
    private EntityManager em;

    public List<MutationsByYear> getMutationsByYear(String codinsee) {
        Query q = em.createNativeQuery("SELECT mut.* FROM ? mut");
        q.setParameter(1, TABLE_PREFIX+codinsee);
        List<MutationsByYear> out = new ArrayList<MutationsByYear>();
        q.getResultList().forEach(o->out.add((MutationsByYear)o));
        return out;
    }

}
