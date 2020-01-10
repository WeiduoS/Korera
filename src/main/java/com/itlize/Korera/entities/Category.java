package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Weiduo
 * @date 2020/1/8 - 1:48 PM
 */

@Entity
@Table(schema = "mydb", name="Category")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;

    @Column(name = "category_name")
    private String category_name;

    @JsonIgnore
    @OneToMany(targetEntity = Resource.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Resource> resources;


    public Category() {

    }

    public Category(Integer category_id) {
        this.category_id = category_id;
    }

    public Category(String category_name) {
        this.category_name = category_name;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                ", resources=" + resources +
                '}';
    }
}
