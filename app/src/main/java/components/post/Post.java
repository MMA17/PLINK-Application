package components.post;

import java.io.Serializable;

import components.classes.Class;
import components.file.File;
import components.member.Member;

public class Post implements Serializable {
    private int id;
    private String title;
    private Member author;
    private String content;
    private String created_at;
    private Class classes;
    private File file;


    public Post() {
    }

    public Post(int id, String title, Member author, String content, String created_at, Class classes, File file) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.created_at = created_at;
        this.classes = classes;
        this.file = file;
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

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Class getClasses() {
        return classes;
    }

    public void setClasses(Class classes) {
        this.classes = classes;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
