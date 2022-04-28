package components.comment;

import java.io.Serializable;

import components.member.Member;
import components.post.Post;

public class Comment implements Serializable {
    private int id;
    private int memberid;
    private int postid;
    private String created_at;
    private String content;

    public Comment() {
    }

    public Comment(int id, int memberid, int postid, String created_at, String content) {
        this.id = id;
        this.memberid = memberid;
        this.postid = postid;
        this.created_at = created_at;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
