package com.itlize.Korera.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(schema = "mydb_new", name="Resource")
public class Resource implements Serializable{

    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resourceId;

    @Column(name = "resource_code")
    private String resourceCode;

    @Column(name = "resource_name")
    private String resourceName;

    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "category_id")
    private Category category_id;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "resources")
    public Set<Project> projects;

    @Transient
    private List<Cols> cols;

    public Resource() {

    }

    public Resource(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Resource(String resourceCode, String resourceName, Category category) {
        this.resourceCode = resourceCode;
        this.resourceName = resourceName;
        this.category_id = category;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public List<Cols> getCols() {
        return cols;
    }

    public void setCols(List<Cols> cols) {
        this.cols = cols;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", resourceCode='" + resourceCode + '\'' +
                ", resourceName='" + resourceName + '\'' +
                '}';
    }
}
