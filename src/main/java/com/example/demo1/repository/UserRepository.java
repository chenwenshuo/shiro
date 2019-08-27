package com.example.demo1.repository;

import com.example.demo1.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wenshuo.chen
 * @data 2019/8/2214:04
 */
public interface UserRepository extends JpaRepository <User,Integer>{
    public User findUserByUsername(String name);
}
