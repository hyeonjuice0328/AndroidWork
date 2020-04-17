package com.lec.android.a007_activity;

import java.io.Serializable;

public class Person implements Serializable {
    String name;
    int age;

    // 안드로이드 스튜디오에서 Alt + Insert 로 자동생성하기
    // Intent 에 담아 보내는 객체는 반.드.시 Serializable 되어 있어야 한다.

    // 기본생성자
    public Person() {
    }

    // 매개변수 있는 생성자
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
