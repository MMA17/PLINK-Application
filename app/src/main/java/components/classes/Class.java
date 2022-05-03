package components.classes;

import java.io.Serializable;
import java.util.ArrayList;

import components.classmember.ClassMember;

public class Class implements Serializable {
    private int id;
    private String name;
    private String note;
    ArrayList<ClassMember> classMember;

    public Class() {

    }

    public Class(int id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
