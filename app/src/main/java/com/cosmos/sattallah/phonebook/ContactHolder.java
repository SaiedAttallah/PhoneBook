package com.cosmos.sattallah.phonebook;

/**
 * Created by sattallah on 10/27/2016.
 */

public class ContactHolder {
    protected int id;
    protected String name, jobDescription, phone;

    public ContactHolder(int id, String name, String jobDescription, String phone){
        this.id = id;
        this.name = name;
        this.jobDescription = jobDescription;
        this.phone = phone;

    }

    public ContactHolder(String name, String jobDescription, String phone){
        this.name = name;
        this.jobDescription = jobDescription;
        this.phone = phone;
    }
}
