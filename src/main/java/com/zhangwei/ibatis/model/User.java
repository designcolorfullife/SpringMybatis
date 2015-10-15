package com.zhangwei.ibatis.model;


import java.util.Date;

public class User {

    private String role_id;
    private Date   gmt_create;
    private Date   gmt_modified;
    private long   school_id;
    private String in_school;
    private String degree;
    private int    state;
    private String source;
    private String educate_date;
    private int    score;


    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public long getSchool_id() {
        return school_id;
    }

    public void setSchool_id(long school_id) {
        this.school_id = school_id;
    }

    public String getIn_school() {
        return in_school;
    }

    public void setIn_school(String in_school) {
        this.in_school = in_school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEducate_date() {
        return educate_date;
    }

    public void setEducate_date(String educate_date) {
        this.educate_date = educate_date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "User [role_id=" + role_id + ", gmt_create=" + gmt_create + ", gmt_modified="
               + gmt_modified + ", school_id=" + school_id + ", in_school=" + in_school
               + ", degree=" + degree + ", state=" + state + ", source=" + source
               + ", educate_date=" + educate_date + ", score=" + score + "]\n";
    }

}
