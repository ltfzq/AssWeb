/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author User
 */
public class Mqa02 {
    private int mqa02id;
    private String progcode;
    private int docid;
    private String status;
    private String notes;

    public Mqa02() {
    }

    public Mqa02(int mqa02id, String progcode, int docid, String status, String notes) {
        this.mqa02id = mqa02id;
        this.progcode = progcode;
        this.docid = docid;
        this.status = status;
        this.notes = notes;
    }

    public Mqa02(String progcode, int docid, String status, String notes) {
        super();
        this.progcode = progcode;
        this.docid = docid;
        this.status = status;
        this.notes = notes;
    }

    public int getMqa02id() {
        return mqa02id;
    }

    public void setMqa02id(int mqa02id) {
        this.mqa02id = mqa02id;
    }

    public String getProgcode() {
        return progcode;
    }

    public void setProgcode(String progcode) {
        this.progcode = progcode;
    }

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
}