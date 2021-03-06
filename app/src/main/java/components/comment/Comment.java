package components.comment;

import java.io.Serializable;

import components.member.Member;
import components.post.Post;

public class Comment implements Serializable {
    private int id;
    private String content;
    private String created_at;
    private int authorid;
    private int postid;

    public Comment() {
    }

    public Comment(int id, String content, String created_at, int authorid, int postid) {
        this.id = id;
        this.content = content;
        this.created_at = created_at;
        this.authorid = authorid;
        this.postid = postid;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }
}
