package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Weiduo
 * @date 2019/12/30 - 3:54 PM
 */
@Entity
@Table(schema = "KoreraDB", name="project")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "project_id")
public class Project implements Serializable {

    @Id
    @Column(name="project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer project_id;

    @Column(name="project_name")
    private String project_name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = true, updatable = false, nullable = true)
    private User user;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "project_resource",
            joinColumns = {@JoinColumn(name = "project_id", nullable = true, insertable = true, updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", nullable = true, insertable = true, updatable = true)})
    private Set<Resource> resources = new HashSet<>();

    public Project() {

    }

    public Project(Integer project_id) {
        this.project_id = project_id;
    }

    public Project(String project_name, User user) {
        this.project_name = project_name;
        this.user = user;
    }

    public Project(Integer project_id, String project_name) {
        this.project_id = project_id;
        this.project_name = project_name;
    }

    public Project(Integer project_id, String project_name, User user) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.user = user;
    }


    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Resource> getResouces() {
        return resources;
    }

    public void setResouces(Set<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", resources=" + resources +
                '}';
    }
}
