package components.member;

import java.util.Date;

public class Member {
    private int id;
    private String name;
    private String role;
    private String phone;
    private String email;
    private String password;
    private Date DOB;

    public Member(int anInt, String string, String cursorString, String s) {
    }

    public Member(int id, String name, String role, String phone, String email, String password, String DOB) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

}
