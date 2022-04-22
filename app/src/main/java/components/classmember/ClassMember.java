package components.classmember;

import java.io.Serializable;
import components.classes.Class;
import components.member.Member;

public class ClassMember implements Serializable {
    private Member member;
    private Class lop;
    private int isOwner; // 1 = True , 0 = False

    public ClassMember(int anInt, int cursorInt, int i) {
    }

    public ClassMember(Member member, components.classes.Class aClass, int isOwner) {
        this.member = member;
        this.lop = aClass;
        this.isOwner = isOwner;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Class getLop() {
        return lop;
    }

    public void setLop(Class lop) {
        this.lop = lop;
    }

    public int getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(int owner) {
        isOwner = owner;
    }

}
