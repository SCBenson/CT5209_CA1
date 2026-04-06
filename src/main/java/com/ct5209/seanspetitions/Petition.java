package com.ct5209.seanspetitions;

import java.util.ArrayList;
import java.util.List;

public class Petition{



    private int id;
    private String title;
    private String description;
    private List<Signature> signatures = new ArrayList<>();

    public Petition(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Signature> getSignature() {
        return signatures;
    }

    public void addSignature(Signature sig) {
        this.signatures.add(sig);
    }

    public int getSignatureCount(){
        return signatures.size();
    }





}