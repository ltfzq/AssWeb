/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Lenovo
 */
public class App {
    private int appid;
    private String progcode;
    private String appname;
    private String department;
    private String position;

    public App() {
    }

    public App(String progcode, String appname, String department, String position) {
        super();
        this.progcode = progcode;
        this.appname = appname;
        this.department = department;
        this.position = position;
    }

    public App(int appid, String progcode, String appname, String department, String position) {
        this.appid = appid;
        this.progcode = progcode;
        this.appname = appname;
        this.department = department;
        this.position = position;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getProgcode() {
        return progcode;
    }

    public void setProgcode(String progcode) {
        this.progcode = progcode;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    
}
