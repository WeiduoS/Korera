package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id", nullable = true)
    private Integer project_id;

    @Column(name = "resource_id", nullable = true)
    private Integer resource_id;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "projectResource", orphanRemoval = true)
    private List<Cols> cols = new LinkedList<>();


    public ProjectResource() {
    }

    public ProjectResource(Integer id) {
        this.id = id;
    }

    public ProjectResource(Integer project_id, Integer resource_id) {
        this.project_id = project_id;
        this.resource_id = resource_id;
    }

    public ProjectResource(Integer id, Integer project_id, Integer resource_id) {
        this.id = id;
        this.project_id = project_id;
        this.resource_id = resource_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(project_id, that.project_id) &&
                Objects.equals(resource_id, that.resource_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, project_id, resource_id);
    }

    @Override
    public String toString() {
        return "ProjectResource{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", resource_id=" + resource_id +
                ", cols=" + cols +
                '}';
    }
}
