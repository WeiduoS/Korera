package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:33 PM
 */

@Entity
@Table(schema = "mydb_new", name="proj_res_mapping")
public class Proj_res_mapping implements Serializable {

    @EmbeddedId
    private PRMPKey prmpKey;

    @JsonIgnore
    @OneToMany(targetEntity = Cols.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "prm")
    private Set<Cols> cols;


    public Proj_res_mapping() {
        this.cols = new HashSet<>();
    }

    public Proj_res_mapping(PRMPKey prmpKey) {
        this.prmpKey = prmpKey;
        this.cols = new HashSet<>();
    }

    public PRMPKey getPrmpKey() {
        return prmpKey;
    }

    public void setPrmpKey(PRMPKey prmpKey) {
        this.prmpKey = prmpKey;
    }

    public Set<Cols> getCols() {
        return cols;
    }

    public void setCols(Set<Cols> cols) {
        this.cols = cols;
    }


    @Override
    public String toString() {
        return "Proj_res_mapping{" +
                "prmpKey=" + prmpKey +
                ", cols=" + cols +
                '}';
    }
}
