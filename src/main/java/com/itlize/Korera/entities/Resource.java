package com.itlize.Korera.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(schema = "KoreraDB", name="resource")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "resource_id")
public class Resource implements Serializable{

    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resource_id;

    @Column(name = "resource_code")
    private String resource_code;

    @Column(name = "resource_name")
    private String resource_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", insertable = true, updatable = false, nullable = true)
    private Category category;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "resources")
    public Set<Project> projects =  new HashSet<>();

    @Transient
    private List<Cols> cols;

    public Resource() {

    }

    public Resource(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public Resource(String resource_code, String resource_name, Category category) {
        this.resource_code = resource_code;
        this.resource_name = resource_name;
        this.category = category;
    }

    public Resource(Integer resourceId, String resource_code, String resource_name, Category category) {
        this.resource_id = resource_id;
        this.resource_code = resource_code;
        this.resource_name = resource_name;
        this.category = category;
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_code() {
        return resource_code;
    }

    public void setResource_code(String resource_code) {
        this.resource_code = resource_code;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory_id(Category category) {
        this.category = category;
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
                "resource_id=" + resource_id +
                ", resource_code='" + resource_code + '\'' +
                ", resource_name='" + resource_name + '\'' +
                '}';
    }
}
