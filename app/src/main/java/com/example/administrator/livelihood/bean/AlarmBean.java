package com.example.administrator.livelihood.bean;

import io.realm.RealmObject;

/**
 * 闹钟Bean
 */
public class AlarmBean extends RealmObject{
    private int repeat;
    private String bell;
    private int shake;
    private String mark;

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getBell() {
        return bell;
    }

    public void setBell(String bell) {
        this.bell = bell;
    }

    public int getShake() {
        return shake;
    }

    public void setShake(int shake) {
        this.shake = shake;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
