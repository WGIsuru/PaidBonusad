package com.example.paidbonusad;

public class Member {
    private String ptname;
    private String ptEmail;
    private String ptMobile;
    private String ptpassword;
    private String pt_cnf_password;

    public Member() {
    }

    public Member(String ptname, String ptEmail, String ptMobile, String ptpassword, String pt_cnf_password) {
        this.ptname = ptname;
        this.ptEmail = ptEmail;
        this.ptMobile = ptMobile;
        this.ptpassword = ptpassword;
        this.pt_cnf_password = pt_cnf_password;
    }

    public String getPtname() {
        return ptname;
    }

    public void setPtname(String ptname) {
        this.ptname = ptname;
    }

    public String getPtEmail() {
        return ptEmail;
    }

    public void setPtEmail(String ptEmail) {
        this.ptEmail = ptEmail;
    }

    public String getPtMobile() {
        return ptMobile;
    }

    public void setPtMobile(String ptMobile) {
        this.ptMobile = ptMobile;
    }

    public String getPtpassword() {
        return ptpassword;
    }

    public void setPtpassword(String ptpassword) {
        this.ptpassword = ptpassword;
    }

    public String getPt_cnf_password() {
        return pt_cnf_password;
    }

    public void setPt_cnf_password(String pt_cnf_password) {
        this.pt_cnf_password = pt_cnf_password;
    }
}