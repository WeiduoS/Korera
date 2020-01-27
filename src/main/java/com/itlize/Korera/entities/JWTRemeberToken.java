package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author Weiduo
 * @date 2020/1/26 - 4:09 PM
 */

@Entity
@Table(schema = "KoreraDB", name="persistent_logins")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "series")
public class JWTRemeberToken extends PersistentRememberMeToken {

    @Id
    @Column(name = "username")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;

    @Column(name = "series")
    private String series;

    @Column(name = "token")
    private String token;

    @Column(name = "last_used")
    private Timestamp last_used;

    public JWTRemeberToken() {
        super("default name", "default series", "default token", new Timestamp(new Date().getTime()));
    }

    public JWTRemeberToken(String username, String series, String token, Timestamp last_used) {
        super(username, series, token, last_used);
        this.username = username;
        this.series = series;
        this.token = token;
        this.last_used = last_used;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getLast_used() {
        return last_used;
    }

    public void setLast_used(Timestamp last_used) {
        this.last_used = last_used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JWTRemeberToken token1 = (JWTRemeberToken) o;
        return Objects.equals(username, token1.username) &&
                Objects.equals(series, token1.series) &&
                Objects.equals(token, token1.token) &&
                Objects.equals(last_used, token1.last_used);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, series, token, last_used);
    }

    @Override
    public String toString() {
        return "JWTRemeberToken{" +
                "username='" + username + '\'' +
                ", series='" + series + '\'' +
                ", token='" + token + '\'' +
                ", last_used=" + last_used +
                '}';
    }
}
