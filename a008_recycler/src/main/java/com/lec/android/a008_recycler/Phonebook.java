package com.lec.android.a008_recycler;

import java.io.Serializable;

//전화번호부 데이터를 담을 클래스 // Intent 하기 위한 Serializable
public class Phonebook implements Serializable {
    int photo;// 사진
    String name;// 이름
    String phone;// 전화번호
    String email;// 이메일

    // 기본생성자
    public Phonebook() {
    }
    //매개변수받는 생성자
    public Phonebook(int photo, String name, String phone, String email) {
        this.photo = photo;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    // Getter and Setter
    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
