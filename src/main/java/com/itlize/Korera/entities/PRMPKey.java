package com.itlize.Korera.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Weiduo
 * @date 2020/1/9 - 5:06 PM
 */
@Embeddable
public class PRMPKey implements Serializable {

    private Integer project_id;

    private Integer resource_id;

    public PRMPKey() {

    }

    public PRMPKey(Integer project_id, Integer resource_id) {
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
        PRMPKey prmpKey = (PRMPKey) o;
        return Objects.equals(project_id, prmpKey.project_id) &&
                Objects.equals(resource_id, prmpKey.resource_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(project_id, resource_id);
    }

    @Override
    public String toString() {
        return "PRMPKey{" +
                "project_id=" + project_id +
                ", resource_id=" + resource_id +
                '}';
    }
}
