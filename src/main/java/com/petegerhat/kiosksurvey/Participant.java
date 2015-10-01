package com.petegerhat.kiosksurvey;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name="Participants")
public class Participant implements Serializable {

    private Set<Record> records;

    @Id
    @GeneratedValue
    private String username;
    private String country;
    private int age;
    private String gender;
    private String comment;
    private String service;
    private String worker_id;
    private String campaign_id;
    private Timestamp reg_date;
    private boolean active;


    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public void setReg_date(Timestamp reg_date) {
        this.reg_date = reg_date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Participant(String username, String country, int age, String gender, String comment, String service, String worker_id, String campaign_id, Timestamp reg_date, boolean active) {
        this.username = username;
        this.country = country;
        this.age = age;
        this.gender = gender;
        this.comment = comment;
        this.service = service;
        this.worker_id = worker_id;
        this.campaign_id = campaign_id;
        this.reg_date = reg_date;

        this.active = active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Participant() {

    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public String getDuration() {
        long time = getLastRecord().getTime() - reg_date.getTime();
        if (time > 2400000L) {
            time = 2400000L;
        }
        long second = (time / 1000) % 60;
        long minute = (time / (1000 * 60)) % 60;
        long hour = (time / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public String getDuration(String expression) {
        long time = 0;
        try {
            time = getDurationLong(expression);
        } catch (Exception e) {
            return "*invalid*";
        }
        long second = (time / 1000) % 60;
        long minute = (time / (1000 * 60)) % 60;
        long hour = (time / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public long getDurationLong(String expression) throws Exception {
        long time = 0L;
        long currentTime = reg_date.getTime();
        long newTime;
        List<Record> recordsSorted = new ArrayList(records);
        Collections.sort(recordsSorted, new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return (r1.getEntry_date().getTime() < r2.getEntry_date().getTime() ? -1 : (r1.getEntry_date().getTime() == r2.getEntry_date().getTime() ? 0 : 1));
            }
        });
        int expressionCounter = 0;
        for (Record record : recordsSorted) {
            newTime = record.getEntry_date().getTime();
            if (record.getAv_expression().equals(expression)) {
                time = time + newTime - currentTime;
                expressionCounter++;
            }
            currentTime = newTime;
        }
        if (expressionCounter!=8) {
            throw new Exception("User did not finish survey.");
        }
        if (time > 2400000L) {
            time = 2400000L;
        }
        return time;
    }

    private Timestamp getLastRecord() {
        Timestamp ret = reg_date;
        for (Record record : records) {
            if (record.getEntry_date().after(ret)) {
                ret = record.getEntry_date();
            }
        }
        return ret;
    }

    public boolean isValid(String expression) {
        try {
            getDurationLong(expression);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Column (name = "username", updatable = false)
    public String getUsername() {
        return username;
    }

    @Column (name = "country", updatable = false)
    public String getCountry() {
        return country;
    }

    @Column (name = "age", updatable = false)
    public int getAge() {
        return age;
    }

    @Column (name = "gender", updatable = false)
    public String getGender() {
        return gender;
    }

    @Column (name = "comment", updatable = false)
    public String getComment() {
        return comment;
    }

    @Column (name = "service", updatable = false)
    public String getService() {
        return service;
    }

    @Column (name = "worker_id", updatable = false)
    public String getWorker_id() {
        return worker_id;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    @Column (name = "campaign_id", updatable = false)
    public String getCampaign_id() {
        return campaign_id;

    }

    @Column (name = "red_date", updatable = false)
    public Timestamp getReg_date() {
        return reg_date;
    }

    @Column (name = "active", updatable = true)
    public boolean isActive() {
        return active;
    }

    public int getRecordCount(String expression) {
        int ret = 0;
        for (Record record : records) {
            if (record.getAv_expression().equals(expression)) {
                ret++;
            }
        }
        return ret;
    }

    public Timestamp getLastTime() {
        Timestamp ret = reg_date;
        for (Record record: records) {
            if (record.getEntry_date().after(ret)) {
                ret = record.getEntry_date();
            }
        }
        return ret;
    }

    public boolean hasExpression(String expression) {
        for (Record record : records) {
            if (record.getAv_expression().equals(expression)) {
                return true;
            }
        }
        return false;
    }
}
