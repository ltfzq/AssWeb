/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Lenovo
 */
public class Irv {
    private int irvid;
    private String progcode;
    private String date;
    private String status;
    private int docid;
    private String notes;

    public Irv() {
    }

    public Irv(String progcode, String date, String status, int docid, String notes) {
        super();
        this.progcode = progcode;
        this.date = date;
        this.status = status;
        this.docid = docid;
        this.notes = notes;
    }

    public Irv(int irvid, String progcode, String date, String status, int docid, String notes) {
        this.irvid = irvid;
        this.progcode = progcode;
        this.date = date;
        this.status = status;
        this.docid = docid;
        this.notes = notes;
    }

    public int getIrvid() {
        return irvid;
    }

    public void setIrvid(int irvid) {
        this.irvid = irvid;
    }

    public String getProgcode() {
        return progcode;
    }

    public void setProgcode(String progcode) {
        this.progcode = progcode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    
    
    
}
