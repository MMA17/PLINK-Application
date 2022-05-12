package components.membersubmitted;

import java.io.Serializable;

public class MemberSubmitted implements Serializable {
    private int memberid;
    private int excerciseid;
    private String timesubmit;
    private int fileid;

    public MemberSubmitted() {
    }

    public MemberSubmitted(int memberid, int excerciseid, String timesubmit, int fileid) {
        this.memberid = memberid;
        this.excerciseid = excerciseid;
        this.timesubmit = timesubmit;
        this.fileid = fileid;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public int getExcerciseid() {
        return excerciseid;
    }

    public void setExcerciseid(int excerciseid) {
        this.excerciseid = excerciseid;
    }

    public String getTimesubmit() {
        return timesubmit;
    }

    public void setTimesubmit(String timesubmit) {
        this.timesubmit = timesubmit;
    }

    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }
}
