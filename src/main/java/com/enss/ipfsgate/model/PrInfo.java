package com.enss.ipfsgate.model;

import java.util.Date;

public class PrInfo {
    private Integer prId;
    private String member_name;
    private String repo_name;
    private String apply_describe;
    private String apply_state;
    private String notes;
    private Date apply_time;

    public PrInfo(Integer prId, String member_name, String repo_name, String apply_describe, String apply_state, String notes, Date apply_time) {
        this.prId = prId;
        this.member_name = member_name;
        this.repo_name = repo_name;
        this.apply_describe = apply_describe;
        this.apply_state = apply_state;
        this.notes = notes;
        this.apply_time = apply_time;
    }

    public PrInfo(String member_name, String repo_name, String apply_describe, String apply_state, String notes) {
        this.member_name = member_name;
        this.repo_name = repo_name;
        this.apply_describe = apply_describe;
        this.apply_state = apply_state;
        this.notes = notes;
    }

    public PrInfo() {
        super();
    }


    public String getRepo_name() {
        return repo_name;
    }

    public void setRepo_name(String repo_name) {
        this.repo_name = repo_name;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    public String getApply_describe() {
        return apply_describe;
    }

    public void setApply_describe(String apply_describe) {
        this.apply_describe = apply_describe;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }
}
