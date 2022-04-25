package components.post;

import java.io.Serializable;

public class Post implements Serializable {
    private int id;
    private String title;
    private String content;
    private String create_at;
    private int author;
    private int classid;

    public Post() {

    }

    public Post(int id, String title, String content, String create_at, int author, int classid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.create_at = create_at;
        this.author = author;
        this.classid = classid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}
