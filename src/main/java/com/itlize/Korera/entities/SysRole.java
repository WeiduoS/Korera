package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Weiduo
 * @date 2020/1/16 - 11:51 AM
 */

@Entity
@Table(schema = "KoreraDB", name="sys_role")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "sysRole_id")
public class SysRole implements Serializable, GrantedAuthority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sysRole_id;


    @Column(name = "ROLE_NAME")
    private String ROLE_NAME;

    @Column(name = "ROLE_DESC")
    private String ROLE_DESC;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public SysRole() {
    }

    public SysRole(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME;
    }

    public SysRole(String ROLE_NAME, String ROLE_DESC) {
        this.ROLE_NAME = ROLE_NAME;
        this.ROLE_DESC = ROLE_DESC;
    }

    public SysRole(Integer sysRole_id, String ROLE_NAME, String ROLE_DESC) {
        this.sysRole_id = sysRole_id;
        this.ROLE_NAME = ROLE_NAME;
        this.ROLE_DESC = ROLE_DESC;
    }

    public SysRole(String ROLE_NAME, String ROLE_DESC, Set<User> users) {
        this.ROLE_NAME = ROLE_NAME;
        this.ROLE_DESC = ROLE_DESC;
        this.users = users;
    }

    public SysRole(Integer sysRole_id, String ROLE_NAME, String ROLE_DESC, Set<User> users) {
        this.sysRole_id = sysRole_id;
        this.ROLE_NAME = ROLE_NAME;
        this.ROLE_DESC = ROLE_DESC;
        this.users = users;
    }


    public Integer getSysRole_id() {
        return sysRole_id;
    }

    public void setSysRole_id(Integer sysRole_id) {
        this.sysRole_id = sysRole_id;
    }

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME;
    }

    public String getROLE_DESC() {
        return ROLE_DESC;
    }

    public void setROLE_DESC(String ROLE_DESC) {
        this.ROLE_DESC = ROLE_DESC;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "sysRole_id=" + sysRole_id +
                ", ROLE_NAME='" + ROLE_NAME + '\'' +
                ", ROLE_DESC='" + ROLE_DESC + '\'' +
                '}';
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return ROLE_NAME;
    }
}
