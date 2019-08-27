package com.example.demo1.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.Car;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author wenshuo.chen
 * @data 2019/8/2310:45
 */
public class JsonTest {

    private List<Car> listOfPersons = new ArrayList<Car>();

    /* Car car=new Car();
     Car car1=new Car();*/


    @Test
    public void tojson() {
        Car car = new Car("uy", new Date(), 1);
        Car car1 = new Car("uy", new Date(), 1);
        listOfPersons.add(new Car("luhu", new Date(), 8));
        listOfPersons.add(new Car("luhuq", new Date(), 18));
        listOfPersons.add(car);
        listOfPersons.add(car1);
        //javabean 转jsonstring
        String jsonOutput = JSON.toJSONString(listOfPersons);
        System.out.println(jsonOutput);
        System.out.println(JSON.toJSONString(car1));
        //jsonstring---->javabean
       /* Car newCar = JSON.parseObject(jsonOutput, Car.class);
        System.out.println(newCar.getCarid());*/
        //javabean转jsonobject


        JSONObject jsonObject = (JSONObject) JSON.toJSON(car1);
        System.out.println(jsonObject);
        System.out.println(jsonObject.getString("name"));
        //json 转javabean
        Car car2 = jsonObject.toJavaObject(Car.class);
        System.out.println(car2.getName());
        System.out.println("-------------------------");
        Car car3 = JSON.toJavaObject(jsonObject, Car.class);
        System.out.println(car3);
        //javaBean--->JSONArrary
        JSONArray jsonArray= (JSONArray)JSON.toJSON(listOfPersons);
        for (Object json :jsonArray) {
            System.out.println(json);
        }
        //jsonarrary---->javabean
        List<Car> carList=new ArrayList<Car>();
        for (int i=0;i<jsonArray.size();i++){
            Car car4=jsonArray.getJSONObject(i).toJavaObject(Car.class);
            carList.add(car4);
        }
        System.out.println("-------------");
        for (Car c:carList) {
            System.out.println(c.toString());
        }
        //jsonString to jsonObject
        String ca=JSON.toJSONString(car);
        JSONObject car5=JSON.parseObject(ca);
        System.out.println(car5);
        //jsonString to jsonArray
        String calist=JSON.toJSONString(listOfPersons);
        JSONArray jsonArray1=JSON.parseArray(calist);
        System.out.println("------------------------");
        for(Object c:jsonArray1) {
            System.out.println(c);
        }
        for (int i=0;i<jsonArray1.size();i++){
            System.out.println(jsonArray1.getJSONObject(i).getDate("date"));
        }
    }

}
