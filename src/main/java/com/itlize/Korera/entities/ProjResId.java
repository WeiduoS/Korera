package com.itlize.Korera.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Weiduo
 * @date 2020/1/28 - 2:43 PM
 */
@Embeddable
public class ProjResId implements Serializable {

    @Column(name = "project_id")
    private Integer project_id;

    @Column(name = "resource_id")
    private Integer resource_id;

    public ProjResId() {
    }

    public ProjResId(Integer project_id, Integer resource_id) {
        this.project_id = project_id;
        this.resource_id = resource_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjResId projResId = (ProjResId) o;
        return Objects.equals(project_id, projResId.project_id) &&
                Objects.equals(resource_id, projResId.resource_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(project_id, resource_id);
    }

    @Override
    public String toString() {
        return "ProjResId{" +
                "project_id=" + project_id +
                ", resource_id=" + resource_id +
                '}';
    }
}
