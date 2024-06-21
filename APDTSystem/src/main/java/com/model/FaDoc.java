/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author User
 */
public class FaDoc {
    private int docid;
    private String progcode;
    private String docname;
    private byte[] docfile;
    private String date;

    public FaDoc() {}

    public FaDoc(String progcode, String docname, byte[] docfile, String date) {
        super();
        this.progcode = progcode;
        this.docname = docname;
        this.docfile = docfile;
        this.date = date;
    }

    public FaDoc(int docid, String progcode, String docname, byte[] docfile, String date) {
        this.docid = docid;
        this.progcode = progcode;
        this.docname = docname;
        this.docfile = docfile;
        this.date = date;
    }

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }

    public String getProgcode() {
        return progcode;
    }

    public void setProgcode(String progcode) {
        this.progcode = progcode;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public byte[] getDocfile() {
        return docfile;
    }

    public void setDocfile(byte[] docfile) {
        this.docfile = docfile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
   
    
}