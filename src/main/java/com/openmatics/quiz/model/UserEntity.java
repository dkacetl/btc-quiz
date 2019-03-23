package com.openmatics.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {

    @Id
    private String email;

    @Column
    private String nickname;

    @Column
    private String phone;

    @Column
    private String technologies;

    @Column
    private String accomplishments;

    @Column
    private String age;

    @Column
    private String wallet;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getAccomplishments() {
        return accomplishments;
    }

    public void setAccomplishments(String accomplishments) {
        this.accomplishments = accomplishments;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
}
