package com.lec.android.a008_practice;

import java.io.Serializable;
// 정보 데이터를 담을 클래스 (Serializable 까지 완료)
public class Information implements Serializable {
    String name; // 이름
    int age;     // 나이
    String address; // 주소

    //기본생성자
    public Information() {}

    //매개변수 받는 생성자
    public Information(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    //Getter and Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
} // end Information
