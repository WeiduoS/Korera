package com.itlize.Korera.entities;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DynamicUpdate(value =true)
@Table(schema = "mydb", name="Resource")
public class Resource implements Serializable{
    @Id
    @Column(name="resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resourceId;

    @Column(name="resource_code")
    private String resourceCode;

    @Column(name="resource_name")
    private String resourceName;

//    @ManyToOne
//    @JoinColumn(name = "project_id")
//    private Project project;

//        @ManyToOne
//    @JoinColumn(name = "categoryId")
//    private Category category;

//    @Column(name = "category_id")
//    private Integer categoryId;

    public Resource() {
    }

    public Resource(String resourceCode){

    }

    public Resource(String resourceCode, String resourceName){
        this.resourceName = resourceName;
        this.resourceCode = resourceCode;
//        this.project = project;
//        this.categoryId = categoryId;
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

    @Override
    public String toString() {
        return "Resource{" +
                "resource_id=" + "" +
                ", resource_name='" + resourceName+ '\'' +
                ", resource_code=" + resourceCode +
                '}';
    }
//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }
}
