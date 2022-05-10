package components.homework;

import java.io.Serializable;

public class Homework implements Serializable {
    private int id;
    private String title;
    private String content;
    private String deadline;
    private int classid;
    private String createdat;
    public Homework(){

    }

    public Homework(int id, String title, String content, String deadline, int classid, String createdat) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.deadline = deadline;
        this.classid = classid;
        this.createdat = createdat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }
}