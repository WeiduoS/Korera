package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:30 PM
 */

@Entity
@Table(schema = "KoreraDB", name="cols")
public class Cols implements Serializable {

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


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "project_id", referencedColumnName = "project_id"),
            @JoinColumn(name = "resource_id", referencedColumnName = "resource_id")
        }
    )
    @JsonBackReference
    private ProjectResource projectResource;


    public Cols() {
    }

    public Cols(Integer id) {
        this.id = id;
    }

    public Cols(String field, String type, String formula, String value) {
        this.field = field;
        this.type = type;
        this.formula = formula;
        this.value = value;
    }

    public Cols(String field, String type, String formula, String value, ProjectResource projectResource) {
        this.field = field;
        this.type = type;
        this.formula = formula;
        this.value = value;
        this.projectResource = projectResource;
    }


    public Cols(Integer id, String field, String type, String formula, String value) {
        this.id = id;
        this.field = field;
        this.type = type;
        this.formula = formula;
        this.value = value;
    }

    public Cols(Integer id, String field, String type, String formula, String value, ProjectResource projectResource) {
        this.id = id;
        this.field = field;
        this.type = type;
        this.formula = formula;
        this.value = value;
        this.projectResource = projectResource;
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

    public ProjectResource getProjectResource() {
        return projectResource;
    }

    public void setProjectResource(ProjectResource projectResource) {
        this.projectResource = projectResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cols cols = (Cols) o;
        return Objects.equals(id, cols.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
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
