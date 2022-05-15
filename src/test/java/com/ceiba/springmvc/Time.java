package com.ceiba.springmvc;


public class Time {

    private int hour;
    private int minute;
    private int second;
    private TimeZone timeZone;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    int differenceInHour(Time webinarStartingTime){
        return normalizedHour() - webinarStartingTime.normalizedHour();
    }
    private int normalizedHour(){
        return timeZone.normalizedHour(hour);
    }
}
