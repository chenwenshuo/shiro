package com.example.demo1.bean;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author wenshuo.chen
 * @data 2019/8/2216:05
 */
@Entity
public class User1 implements Serializable {

    private static final long serialVersionUID = 8655851615465363473L;
    private int id;
    private String name;
    private String tel;

    public User1(){

    }

    public User1(int id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User1 user1 = (User1) o;
        return id == user1.id &&
                Objects.equals(name, user1.name) &&
                Objects.equals(tel, user1.tel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tel);
    }
}
