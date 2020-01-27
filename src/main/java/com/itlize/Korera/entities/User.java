package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.*;

@Entity
@Table(schema = "KoreraDB", name="user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "user_id")
public class User implements Serializable, UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "password")
    private String password;

    @Column(name = "icon")
    private Blob icon;

    @Column(name = "join_date")
    private Date join_date;

    @Transient
    private String remember_me;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "UID", nullable = true, insertable = true, updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "RID", nullable = true, insertable = true, updatable = true)})
    private Set<SysRole> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();

    public User() {

    }

    public User(Integer user_id) {
        this.user_id = user_id;
    }

    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public User(Integer user_id, String user_name, String password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getRemember_me() {
        return remember_me;
    }

    public void setRemember_me(String remember_me) {
        this.remember_me = remember_me;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user_name;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", icon=" + icon +
                ", join_date=" + join_date +
                ", roles=" + roles +
                ", projects=" + projects +
                '}';
    }
}
