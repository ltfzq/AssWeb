/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author User
 */
public class Fa {
    private int faid;
    private String progcode;
    private int mqa02id;
    private int appid;
    private int irvid;
    private String status;

    public Fa() {
    }

    public Fa(String progcode, int mqa02id, int appid, int irvid, String status) {
        this.progcode = progcode;
        this.mqa02id = mqa02id;
        this.appid = appid;
        this.irvid = irvid;
        this.status = status;
    }

    public Fa(int faid, String progcode, int mqa02id, int appid, int irvid, String status) {
        this.faid = faid;
        this.progcode = progcode;
        this.mqa02id = mqa02id;
        this.appid = appid;
        this.irvid = irvid;
        this.status = status;
    }

    public int getFaid() {
        return faid;
    }

    public void setFaid(int faid) {
        this.faid = faid;
    }

    public String getProgcode() {
        return progcode;
    }

    public void setProgcode(String progcode) {
        this.progcode = progcode;
    }

    public int getMqa02id() {
        return mqa02id;
    }

    public void setMqa02id(int mqa02id) {
        this.mqa02id = mqa02id;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public int getIrvid() {
        return irvid;
    }

    public void setIrvid(int irvid) {
        this.irvid = irvid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}