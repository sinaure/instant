package com.sinaure.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MutationsByYearId implements Serializable {
    @Column(name = "anneemut")
    private  Integer anneemut;
    @Column(name = "code_com")
    private Integer code_com;
    @Column(name = "code_dep")
    private Integer code_dep;

    public Integer getCode_dep() {
        return code_dep;
    }

    public void setCode_dep(Integer code_dep) {
        this.code_dep = code_dep;
    }

    public Integer getAnneemut() {
        return anneemut;
    }

    public void setAnneemut(Integer anneemut) {
        this.anneemut = anneemut;
    }

    public Integer getCode_com() {
        return code_com;
    }

    public void setCode_com(Integer code_com) {
        this.code_com = code_com;
    }
}
