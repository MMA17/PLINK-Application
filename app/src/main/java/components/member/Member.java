package components.member;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String password;
    private String role;
    private String DOB;

    public Member() {
    }

    public Member(int id, String name, String phone, String password, String role, String DOB) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.DOB = DOB;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
