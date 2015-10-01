package com.petegerhat.kiosksurvey;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="Records")
public class Record implements Serializable {

    @Id
    @GeneratedValue
    private String username;
    private int av_ethnicity;
    private int av_gender;
    private int av_maturity;
    private int av_expression;
    private int T1;
    private int T2;
    private int L1;
    private int L2;
    private String av_comment;
    private Timestamp entry_date;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAv_ethnicity(int av_ethnicity) {
        this.av_ethnicity = av_ethnicity;
    }

    public void setAv_gender(int av_gender) {
        this.av_gender = av_gender;
    }

    public void setAv_maturity(int av_maturity) {
        this.av_maturity = av_maturity;
    }

    public void setAv_expression(int av_expression) {
        this.av_expression = av_expression;
    }

    public void setT1(int t1) {
        T1 = t1;
    }

    public void setT2(int t2) {
        T2 = t2;
    }

    public void setL1(int l1) {
        L1 = l1;
    }

    public void setL2(int l2) {
        L2 = l2;
    }

    public void setAv_comment(String av_comment) {
        this.av_comment = av_comment;
    }

    public void setEntry_date(Timestamp entry_date) {
        this.entry_date = entry_date;
    }

    public Record(String username, int av_ethnicity, int av_gender, int av_maturity, int av_expression, int t1, int t2, int l1, int l2, String av_comment, Timestamp entry_date) {
        this.username = username;
        this.av_ethnicity = av_ethnicity;
        this.av_gender = av_gender;

        this.av_maturity = av_maturity;
        this.av_expression = av_expression;
        T1 = t1;
        T2 = t2;
        L1 = l1;
        L2 = l2;
        this.av_comment = av_comment;
        this.entry_date = entry_date;
    }

    public Record() {
    }

    @Column(name = "username", updatable = false)
    public String getUsername() {
        return username;
    }

    @Column (name = "av_ethnicity", updatable = false)
    public int getAv_ethnicity() {
        return av_ethnicity;
    }

    @Column (name = "av_gender", updatable = false)
    public int getAv_gender() {
        return av_gender;
    }

    @Column (name = "av_maturity", updatable = false)
    public int getAv_maturity() {
        return av_maturity;
    }

    @Column (name = "av_expression", updatable = false)
    public String getAv_expression() {
        if (av_expression == 0) {
            return "serious";
        } else if (av_expression == 1) {
            return "smiling";
        }
        return null;
    }

    @Column (name = "T1", updatable = false)
    public int getT1() {
        return T1;
    }

    @Column (name = "T2", updatable = false)
    public int getT2() {
        return T2;
    }

    @Column (name = "L1", updatable = false)
    public int getL1() {
        return L1;
    }

    @Column (name = "L2", updatable = false)
    public int getL2() {
        return L2;
    }

    @Column (name = "av_comment", updatable = false)
    public String getAv_comment() {
        return av_comment;
    }

    @Column (name = "entry_date", updatable = false)
    public Timestamp getEntry_date() {
        return entry_date;
    }
    //Here you need to generate getters and setters

}  
