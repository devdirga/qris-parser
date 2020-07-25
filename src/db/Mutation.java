/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import util.Link;

/**
 *
 * @author Dirga
 */
public class Mutation {
    
    private String idx;
    private String kodebank;
    private String tanggal;
    private String keterangan;
    private String idoutlet;
    private String dbkr = Link.EMPTY;
    private String nominal;
    private String saldo;
    private String tglsys;
    private String waktusys;
    private String catatan1;
    private String catatan2 = Link.EMPTY;
    
    public Mutation(String keterangan, String kodebank, String nominal, String saldo, String catatan1){
        this.keterangan = keterangan;
        this.kodebank = kodebank;
        this.nominal = nominal;
        this.saldo = saldo;
        this.catatan1 = catatan1;
    }
    
    /**
     * @return the id
     */
    public String getIdx() {
        return idx;
    }

    /**
     * @param idx the id to set
     */
    public void setIdx(String idx) {
        this.idx = idx;
    }

    /**
     * @return the code
     */
    public String getKodebank() {
        return kodebank;
    }

    /**
     * @param kodebank the code to set
     */
    public void setKodebank(String kodebank) {
        this.kodebank = kodebank;
    }

    /**
     * @return the date
     */
    public String getTanggal() {
        return tanggal;
    }

    /**
     * @param tanggal the date to set
     */
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    /**
     * @return the description
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan the description to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @return the outlet
     */
    public String getIdoutlet() {
        return idoutlet;
    }

    /**
     * @param idoutlet the outlet to set
     */
    public void setIdoutlet(String idoutlet) {
        this.idoutlet = idoutlet;
    }

    /**
     * @return the debit / credit
     */
    public String getDbkr() {
        return dbkr;
    }

    /**
     * @param dbkr the debit / credit to set
     */
    public void setDbkr(String dbkr) {
        this.dbkr = dbkr;
    }

    /**
     * @return the nominal
     */
    public String getNominal() {
        return nominal;
    }

    /**
     * @param nominal the nominal to set
     */
    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    /**
     * @return the nominal
     */
    public String getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the nominal to set
     */
    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the system date
     */
    public String getTglsys() {
        return tglsys;
    }

    /**
     * @param tglsys the system date to set
     */
    public void setTglsys(String tglsys) {
        this.tglsys = tglsys;
    }

    /**
     * @return the system time
     */
    public String getWaktusys() {
        return waktusys;
    }

    /**
     * @param waktusys the system time to set
     */
    public void setWaktusys(String waktusys) {
        this.waktusys = waktusys;
    }

    /**
     * @return the note
     */
    public String getCatatan1() {
        return catatan1;
    }

    /**
     * @param catatan1 the note to set
     */
    public void setCatatan1(String catatan1) {
        this.catatan1 = catatan1;
    }

    /**
     * @return the note
     */
    public String getCatatan2() {
        return catatan2;
    }

    /**
     * @param catatan2 the note to set
     */
    public void setCatatan2(String catatan2) {
        this.catatan2 = catatan2;
    }
    
}