package com.example.plink;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
    private int id;
    private String name;
    private String role;
    private Date dob;
    private String phone;
    private String passwd;
    private String email;

    public Member() {
    }

    public Member(int id, String name, String role, Date dob, String phone, String passwd, String email) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.dob = dob;
        this.phone = phone;
        this.passwd = passwd;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
