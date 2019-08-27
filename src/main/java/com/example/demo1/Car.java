package com.example.demo1;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wenshuo.chen
 * @data 2019/8/2310:39
 */
public class Car {

   //@JSONField(serialize =false)
    private String name;
    @JSONField(format="yyyyMMdd")
    private Date date;
    private int carid;

    public Car(String name, Date date, int carid) {
        this.name = name;
        this.date = date;
        this.carid = carid;
    }

    public Car() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", carid=" + carid +
                '}';
    }
}
