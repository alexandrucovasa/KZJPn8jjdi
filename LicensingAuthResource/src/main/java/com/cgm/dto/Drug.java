package com.cgm.dto;

/**
 * Created by alexandruiulian.cova on 11/15/2016.
 */

public class Drug {

    private String id;
    private String dosage;
    private String drugName;

    public Drug(){
        super();
    }

    public Drug(String id, String dosage, String drugName) {
        this.id = id;
        this.dosage = dosage;
        this.drugName = drugName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
}
