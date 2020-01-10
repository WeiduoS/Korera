package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Weiduo
 * @date 2019/12/30 - 3:54 PM
 */
@Entity
@Table(schema = "mydb", name="Project")
public class Project implements Serializable {
    @Id
    @Column(name="project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer project_id;

    @Column(name="project_name")
    private String project_name;

    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "proj_res_mapping",
            joinColumns = {@JoinColumn(name = "project_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", nullable = false, updatable = false)})
    private Set<Resource> resources;

    public Project() {
        this.resources = new HashSet<>();
    }

    public Project(Integer project_id, String project_name, User user) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.user = user;
        this.resources = new HashSet<>();
    }


    public Project(String project_name, User user) {
        this.project_name = project_name;
        this.user = user;
        this.resources = new HashSet<>();
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
                ", resouces=" + resources +
                '}';
    }
}
