package com.itlize.Korera.entities;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

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

    @Override
    public String toString() {
        return "User{" +
                "user_Id=" + user_Id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", icon=" + icon +
                ", join_date=" + join_date +
                '}';
    }
}
