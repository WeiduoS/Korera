package com.itlize.Korera.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:30 PM
 */

@Entity
@Table(schema = "mydb_new", name="cols")
public class Cols {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "field")
    private String field;

    @Column(name = "type")
    private String type;

    @Column(name = "formula")
    private String formula;

    @Column(name = "value")
    private String value;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "project_id"),
            @JoinColumn(name = "resource_id")
    })
    private Proj_res_mapping prm;

    public Cols() {

    }

    public Cols(Integer id) {
        this.id = id;
    }

    public Cols(Integer id, Proj_res_mapping prm) {
        this.id = id;
        this.prm = prm;
    }

    public Cols(Integer id, Proj_res_mapping prm, String field, String type, String formula, String value) {
        this.id = id;
        this.prm = prm;
        this.field = field;
        this.type = type;
        this.formula = formula;
        this.value = value;
    }

    public Cols(Integer id, String field, String type, String formula, String value) {
        this.id = id;
        this.field = field;
        this.type = type;
        this.formula = formula;
        this.value = value;
    }


    public Cols(Proj_res_mapping prm, String field, String type, String formula, String value) {
        this.prm = prm;
        this.field = field;
        this.type = type;
        this.formula = formula;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Proj_res_mapping getPrm() {
        return prm;
    }

    public void setPrm(Proj_res_mapping prm) {
        this.prm = prm;
    }

    @Override
    public String toString() {
        return "Cols{" +
                "id=" + id +
                ", field='" + field + '\'' +
                ", type='" + type + '\'' +
                ", formula='" + formula + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
