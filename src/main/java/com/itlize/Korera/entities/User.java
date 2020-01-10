package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(schema = "mydb", name="User")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_Id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "password")
    private String password;

    @Column(name = "icon")
    private Blob icon;

    @Column(name = "join_date")
    private Date join_date;

    @JsonIgnore
    @OneToMany(targetEntity = Project.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Project> projects;

    public User() {

    }

    public User(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public User(Integer user_Id, String user_name, String password) {
        this.user_Id = user_Id;
        this.user_name = user_name;
        this.password = password;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Blob getIcon() {
        return icon;
    }

    public void setIcon(Blob icon) {
        this.icon = icon;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_Id=" + user_Id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", icon=" + icon +
                ", join_date=" + join_date +
                ", projects=" + projects +
                '}';
    }
}
