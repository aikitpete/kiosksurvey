package com.petegerhat.kiosksurvey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by swyna on 7/25/15.
 */
public class Participants {

    private List<Participant> participants;
    private List<Participant> participantsSerious;
    private List<Participant> participantsSmiling;
    private List<Participant> participantsActive;
    private List<Participant> participantsSeriousActive;
    private List<Participant> participantsSmilingActive;
    private List<String> countries;

    {
        countries = new ArrayList<String>();
    }

    public Participants(List<Participant> participants) {
        this.participants = participants;
        this.participantsSmiling = new ArrayList<Participant>();
        this.participantsSerious = new ArrayList<Participant>();
        this.participantsActive = new ArrayList<Participant>();
        this.participantsSmilingActive = new ArrayList<Participant>();
        this.participantsSeriousActive = new ArrayList<Participant>();
        for (Participant participant : participants) {
            if (participant.isActive()) participantsActive.add(participant);
            if (participant.hasExpression("smiling")) {
                participantsSmiling.add(participant);
                if (participant.isActive()) participantsSmilingActive.add(participant);
            }
            if (participant.hasExpression("serious")) {
                participantsSerious.add(participant);
                if (participant.isActive()) participantsSmilingActive.add(participant);
            }
        }
        participantsSmiling.sort(new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                if ((p1.isValid("smiling") && !p2.isValid("smiling"))) {
                    return -1;
                } else if ((!p1.isValid("smiling") && p2.isValid("smiling"))) {
                    return 1;
                }
                if (p1.getLastTime().getTime() > p2.getLastTime().getTime()) {
                    return -1;
                }
                if (p1.getLastTime().getTime() == p2.getLastTime().getTime()) {
                    return 0;
                }
                return 1;
            }
        });
        participantsSerious.sort(new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                if ((p1.isValid("serious") && !p2.isValid("serious"))) {
                    return -1;
                } else if ((!p1.isValid("serious") && p2.isValid("serious"))) {
                    return 1;
                }
                if (p1.getLastTime().getTime() > p2.getLastTime().getTime()) {
                    return -1;
                }
                if (p1.getLastTime().getTime() == p2.getLastTime().getTime()) {
                    return 0;
                }
                return 1;
            }
        });
        for (Participant participant : participants) {
            if (!countries.contains(participant.getCountry())) {
                countries.add(participant.getCountry());
            }
        }
    }

    public  String getName() {
        String ret = "";
        for (String country : countries) {
            if (!ret.equals("")) {
                ret = ret + ", ";
            }
            ret = ret + country;
        }
        return ret;
    }

    public List<Participant> getParticipants(String country, String expression) {
        List<Participant> ret = new ArrayList<Participant>(getParticipantsByExpression(expression));
        Iterator<Participant> iterator = ret.iterator();
        while (iterator.hasNext()) {
            Participant participant = iterator.next();
            if (!participant.getCountry().equals(country)) {
                iterator.remove();
                continue;
            }
            /*if (participant.getRecordCount(expression) == 0) {
                iterator.remove();
                continue;
            }*/
        }

        return ret;
    }

    public List<Participant> getParticipants(String country) {
        List<Participant> ret = new ArrayList<Participant>(participants);
        Iterator<Participant> iterator = ret.iterator();
        while (iterator.hasNext()) {
            Participant participant = iterator.next();
            if (!participant.getCountry().equals(country)) {
                iterator.remove();
                continue;
            }
        }

        return ret;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<String> getCountries() {
        List<String> ret = new ArrayList<String>();
        for (Participant participant : participants) {
            if (!ret.contains(participant.getCountry())) {
                ret.add(participant.getCountry());
            }
        }
        return ret;
    }

    public int getTotal() {
        /*int ret = 0;
        for (Participant participant : participants) {
            if (participant.getCountry().equals(country)) {
                ret++;
            }
        }
        return ret;*/
        return participantsSmiling.size() + participantsSerious.size();
    }

    public int getTotalActive() {
        /*int ret = 0;
        for (Participant participant : participants) {
            if (participant.getCountry().equals(country)) {
                ret++;
            }
        }
        return ret;*/
        return participantsSmilingActive.size() + participantsSeriousActive.size();
    }

    public int getTotalValid() {
        int ret = 0;
        for (Participant participant : participants) {
            if (participant.isValid("smiling")) {
                ret++;
            }
            if (participant.isValid("serious")) {
                ret++;
            }
        }
        return ret;
    }

    public int getTotalValidActive() {
        int ret = 0;
        for (Participant participant : participantsActive) {
            if (participant.isValid("smiling")) {
                ret++;
            }
            if (participant.isValid("serious")) {
                ret++;
            }
        }
        return ret;
    }

    public int getPercentageValid() {
        if (getTotal() == 0) {
            return 0;
        }
        if (getTotal() == 0) {
            return 0;
        }
        return (getTotalValid() / getTotal()) * 100;
    }

    public int getPercentageValidActive() {
        if (getTotal() == 0) {
            return 0;
        }
        if (getTotal() == 0) {
            return 0;
        }
        return (getTotalValidActive() / getTotalActive()) * 100;
    }

    public double getAverageAge() {
        if (getTotal() == 0) {
            return 0;
        }
        int totalAge = 0;
        for (Participant participant : participants) {
            totalAge = totalAge + participant.getAge();
        }
        return totalAge / getTotal();
    }

    public double getAverageAgeValid() {
        if (getTotalValid() == 0) {
            return 0;
        }
        int totalAge = 0;
        for (Participant participant : participants) {
            if (participant.isValid("smiling")) {
                totalAge = totalAge + participant.getAge();
            }
            if (participant.isValid("serious")) {
                totalAge = totalAge + participant.getAge();
            }
        }
        return totalAge / getTotalValid();
    }

    public double getAverageAgeValidActive() {
        if (getTotalValidActive() == 0) {
            return 0;
        }
        int totalAge = 0;
        for (Participant participant : participantsActive) {
            if (participant.isValid("smiling")) {
                totalAge = totalAge + participant.getAge();
            }
            if (participant.isValid("serious")) {
                totalAge = totalAge + participant.getAge();
            }
        }
        return totalAge / getTotalValidActive();
    }

    public int getMaleTotal() {
        int ret = 0;
        for (Participant participant : participants) {
            if (participant.getGender().equals("male") && participant.hasExpression("smiling")) {
                ret++;
            }
            if (participant.getGender().equals("male") && participant.hasExpression("serious")) {
                ret++;
            }
        }
        return ret;
    }

    public int getFemaleTotal() {
        int ret = 0;
        for (Participant participant : participants) {
            if (participant.getGender().equals("female") && participant.hasExpression("smiling")) {
                ret++;
            }
            if (participant.getGender().equals("female") && participant.hasExpression("serious")) {
                ret++;
            }
        }
        return ret;
    }

    public String getAverageDurationValid() {
        if (getTotalValid() == 0) {
            return "none";
        }
        long time = 0L;
        //String ret = "";
        for (Participant participant : participants) {
            try {
                time = time + participant.getDurationLong("smiling");
                //ret = ret + participant.getDurationLong() + " ";
            } catch (Exception e) {

            }
            try {
                time = time + participant.getDurationLong("serious");
                //ret = ret + participant.getDurationLong() + " ";
            } catch (Exception e) {

            }
        }
        time = (long) (time / (long) getTotalValid());
        long second = (time / 1000) % 60;
        long minute = (time / (1000 * 60)) % 60;
        long hour = (time / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public String getAverageDurationValidActive() {
        if (getTotalValidActive() == 0) {
            return "none";
        }
        long time = 0L;
        //String ret = "";
        for (Participant participant : participantsActive) {
            try {
                time = time + participant.getDurationLong("smiling");
                //ret = ret + participant.getDurationLong() + " ";
            } catch (Exception e) {

            }
            try {
                time = time + participant.getDurationLong("serious");
                //ret = ret + participant.getDurationLong() + " ";
            } catch (Exception e) {

            }
        }
        time = (long) (time / (long) getTotalValidActive());
        long second = (time / 1000) % 60;
        long minute = (time / (1000 * 60)) % 60;
        long hour = (time / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public List<Participant> getParticipantsByCountries(List<String> group) {
        List<Participant> ret = new ArrayList<Participant>();
        for (Participant participant : participants) {
            if (group.contains(participant.getCountry())) {
                ret.add(participant);
            }
        }
        return ret;
    }

    public List<Participant> getParticipantsByExpression(String expression) {
        if (expression.equals("smiling")) {
            return participantsSmiling;
        }
        if (expression.equals("serious")) {
            return participantsSerious;
        }
        return null;
        /*List<Participant> ret = new ArrayList<Participant>(participants);
        Iterator<Participant> iterator = ret.iterator();
        while (iterator.hasNext()) {
            Participant participant = iterator.next();
            if (participant.getRecordCount(expression) == 0) {
                iterator.remove();
                continue;
            }
        }
        return ret;*/
    }

    public String getAverageDuration() {
        if (getTotal() == 0) {
            return "none";
        }
        long time = 0;
        for (Participant participant : participants) {
            try {
                time = time + participant.getDurationLong("smiling");
            } catch (Exception e) {

            }
            try {
                time = time + participant.getDurationLong("serious");
            } catch (Exception e) {

            }
        }
        time =  time / getTotalValid();
        long second = (time / 1000) % 60;
        long minute = (time / (1000 * 60)) % 60;
        long hour = (time / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public int getMaleTotalValid() {
        int ret = 0;
        for (Participant participant : participants) {
            if (participant.getGender().equals("male") && participant.isValid("serious")) {
                ret++;
            }
            if (participant.getGender().equals("male") && participant.isValid("smiling")) {
                ret++;
            }
        }
        return ret;
    }

    public int getMaleTotalValidActive() {
        int ret = 0;
        for (Participant participant : participantsActive) {
            if (participant.getGender().equals("male") && participant.isValid("serious")) {
                ret++;
            }
            if (participant.getGender().equals("male") && participant.isValid("smiling")) {
                ret++;
            }
        }
        return ret;
    }

    public int getFemaleTotalValid() {
        int ret = 0;
        for (Participant participant : participants) {
            if (participant.getGender().equals("female") && participant.isValid("serious")) {
                ret++;
            }
            if (participant.getGender().equals("female") && participant.isValid("smiling")) {
                ret++;
            }
        }
        return ret;
    }

    public int getFemaleTotalValidActive() {
        int ret = 0;
        for (Participant participant : participantsActive) {
            if (participant.getGender().equals("female") && participant.isValid("serious")) {
                ret++;
            }
            if (participant.getGender().equals("female") && participant.isValid("smiling")) {
                ret++;
            }
        }
        return ret;
    }
}
