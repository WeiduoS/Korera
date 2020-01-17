package com.itlize.Korera.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Weiduo
 * @date 2020/1/16 - 11:53 AM
 */

@Entity
@Table(schema = "KoreraDB", name="sys_user_role")
public class SysUserRole implements Serializable{

    @Embeddable
    private class SysUserRoleId implements Serializable {

        @Column(name = "UID")
        private Integer UID;

        @Column(name = "RID")
        private Integer RID;

        public SysUserRoleId() {
        }

        public SysUserRoleId(Integer UID, Integer RID) {
            this.UID = UID;
            this.RID = RID;
        }

        public Integer getUID() {
            return UID;
        }

        public void setUID(Integer UID) {
            this.UID = UID;
        }

        public Integer getRID() {
            return RID;
        }

        public void setRID(Integer RID) {
            this.RID = RID;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SysUserRoleId that = (SysUserRoleId) o;
            return Objects.equals(UID, that.UID) &&
                    Objects.equals(RID, that.RID);
        }

        @Override
        public int hashCode() {

            return Objects.hash(UID, RID);
        }

        @Override
        public String toString() {
            return "SysUserRoleId{" +
                    "UID=" + UID +
                    ", RID=" + RID +
                    '}';
        }

    }

    @EmbeddedId
    private SysUserRoleId id;


    public SysUserRoleId getId() {
        return id;
    }

    public void setId(SysUserRoleId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                "id=" + id +
                '}';
    }
}
