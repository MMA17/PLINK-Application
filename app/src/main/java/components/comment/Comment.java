package components.comment;

import java.io.Serializable;

import components.member.Member;
import components.post.Post;

public class Comment implements Serializable {
    private int id;
    private Member member;
    private Post post;
    private String created_at;
    private String content;

    public Comment() {
    }

    public Comment(int id, Member member, Post post, String created_at, String content) {
        this.id = id;
        this.member = member;
        this.post = post;
        this.created_at = created_at;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
