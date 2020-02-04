package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Weiduo
 * @date 2020/1/9 - 3:33 PM
 */

@Entity
@Table(schema = "KoreraDB", name="project_resource")
public class ProjectResource implements Serializable {

    @EmbeddedId
    private ProjResId projResId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "projectResource", orphanRemoval = true)
    @JsonManagedReference
    private List<Cols> cols = new LinkedList<>();


    public ProjectResource() {
    }

    public ProjectResource(ProjResId projResId) {
        this.projResId = projResId;
    }


    public ProjResId getProjResId() {
        return projResId;
    }

    public void setProjResId(ProjResId projResId) {
        this.projResId = projResId;
    }

    public List<Cols> getCols() {
        return cols;
    }

    public void setCols(List<Cols> cols) {
        this.cols = cols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectResource that = (ProjectResource) o;
        return Objects.equals(projResId, that.projResId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(projResId);
    }

    @Override
    public String toString() {
        return "ProjectResource{" +
                "projResId=" + projResId +
                ", cols=" + cols +
                '}';
    }
}
