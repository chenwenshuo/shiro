package com.example.demo1.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author wenshuo.chen
 * @data 2019/8/2214:02
 */
@Entity
public class User {
    private int id;
    private String userUuid;
    private String username;
    private String password;
  /*  private String email;*/
    private String telephone;
    private Integer role;
    private String image;
    private String lastIp;
    private String lastTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_uuid")
    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  /*  @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
*/
    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "last_ip")
    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    @Basic
    @Column(name = "last_time")
    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(userUuid, user.userUuid) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                //Objects.equals(email, user.email) &&
                Objects.equals(telephone, user.telephone) &&
                Objects.equals(role, user.role) &&
                Objects.equals(image, user.image) &&
                Objects.equals(lastIp, user.lastIp) &&
                Objects.equals(lastTime, user.lastTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userUuid, username, password,  telephone, role, image, lastIp, lastTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userUuid='" + userUuid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", role=" + role +
                ", image='" + image + '\'' +
                ", lastIp='" + lastIp + '\'' +
                ", lastTime='" + lastTime + '\'' +
                '}';
    }
}
