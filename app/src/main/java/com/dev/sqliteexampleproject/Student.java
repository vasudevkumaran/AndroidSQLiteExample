package com.dev.sqliteexampleproject;

public class Student {

    private String name;
    private String lastName;
    private String rollNum;
    private int gender;
    private boolean math;
    private boolean physics;
    private boolean chemistry;
    private boolean english;
    private boolean tamil;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isMath() {
        return math;
    }

    public void setMath(boolean math) {
        this.math = math;
    }

    public boolean isPhysics() {
        return physics;
    }

    public void setPhysics(boolean physics) {
        this.physics = physics;
    }

    public boolean isChemistry() {
        return chemistry;
    }

    public void setChemistry(boolean chemistry) {
        this.chemistry = chemistry;
    }

    public boolean isEnglish() {
        return english;
    }

    public void setEnglish(boolean english) {
        this.english = english;
    }

    public boolean isTamil() {
        return tamil;
    }

    public void setTamil(boolean tamil) {
        this.tamil = tamil;
    }
}
