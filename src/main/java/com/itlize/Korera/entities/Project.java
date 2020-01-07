package com.itlize.Korera.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/30 - 3:54 PM
 */
@Entity
@Table(schema = "mydb_new", name="Project")
public class Project implements Serializable {
    @Id
    @Column(name="project_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer project_id;

    @Column(name="project_name")
    private String project_name;

//    @Column(name="user_id")
//    private Integer user_id;

    @OneToMany(mappedBy = "project")
    private List<Resource> resource = new ArrayList<>();

    public Project() {

    }

    public Project(String project_name){
        this.project_name = project_name;
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

//    public Integer getUser_id() {
//        return user_id;
//    }

//    public void setUser_id(Integer user_id) {
//        this.user_id = user_id;
//    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
//                ", user_id=" + user_id +
                '}';
    }

    public List<Resource> getResource() {
        return resource;
    }

    public void setResource(List<Resource> resource) {
        this.resource = resource;
    }
}
