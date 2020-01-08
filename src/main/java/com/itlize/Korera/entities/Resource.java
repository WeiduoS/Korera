package com.itlize.Korera.entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "mydb_new", name="Resource")
public class Resource implements Serializable{
    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer resourceId;

    @Column(name = "resource_code")
    private String resourceCode;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "category_id")
    private Integer category_id;

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

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", resourceCode='" + resourceCode + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}
