package com.example.bibliosgroup7;

import static java.lang.Boolean.TRUE;

import android.os.Parcelable;

import java.io.Serializable;

public class Service {

    private Boolean firstName = true;
    private Boolean lastName = true;
    private Boolean emailAddress = true;
    private Boolean DOB = true;
    private Boolean Raddress = true;
    private Boolean license = false;
    private Boolean car = false;
    private Boolean pickup = true;
    private Boolean returnDate = true;
    private Boolean maxDistance = false;
    private Boolean start = false;
    private Boolean end = false;
    private Boolean movers = false;
    private Boolean boxes = false;
    private Boolean pTime = true;
    private Boolean rTime = true;
    private String serviceName = "";
    private double price;


    private String id;

    public Service(String Id, String serviceName, double price) {
        this.id = Id;
        this.serviceName = serviceName;
        this.price = price;
    }

    public Service(String serviceName, double price){
        this.serviceName = serviceName;
        this.price = price;
    }

    public Service(){}
    public void setFirstName(Boolean name){ this.firstName = name; }
    public void setLastName(Boolean name){ this.lastName = name; }
    public void setEmailAddress(Boolean email){ this.emailAddress = email; }
    public void setDOB(Boolean DOB){ this.DOB = DOB; }
    public void setRAddress(Boolean address){ this.Raddress = address; }
    public void setLicense(Boolean license){ this.license = license; }
    public void setCar(Boolean car){ this.car = car;}
    public void setPickup(Boolean pickup){ this.pickup = pickup; }
    public void setReturn(Boolean returnDate){ this.returnDate = returnDate; }
    public void setMaxDistance(Boolean distance){ this.maxDistance = distance; }
    public void setStart(Boolean start){ this.start = start; }
    public void setEnd(Boolean end){ this.end = end; }
    public void setMovers(Boolean movers){ this.movers = movers; }
    public void setBoxes(Boolean boxes){this.boxes = boxes; }
    public void setpTime(Boolean pTime) { this.pTime = pTime; }
    public void setrTime(Boolean rTime) { this.rTime = rTime; }

    public Boolean getpTime() { return pTime; }
    public Boolean getrTime() { return rTime; }
    public Boolean getFirstName(){ return firstName; }
    public Boolean getLastName(){ return lastName; }
    public Boolean getEmailAddress(){ return emailAddress; }
    public Boolean getDOB(){ return DOB; }
    public Boolean getRAddress(){ return Raddress; }
    public Boolean getLicense(){ return license; }
    public Boolean getCar(){ return car; }
    public Boolean getPickup(){ return pickup; }
    public Boolean getReturn(){ return returnDate; }
    public Boolean getMaxDistance(){ return maxDistance; }
    public Boolean getStart(){ return start; }
    public Boolean getEnd(){ return end; }
    public Boolean getMovers(){ return movers; }
    public Boolean getBoxes(){ return boxes; }

    public String getServiceId(){
        return id;
    }


    public void setServiceId(String id){this.id = id;}


    public String getServiceName(){
        return serviceName;
    }
    public void setServiceName(String serviceName){this.serviceName = serviceName;}
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){this.price = price;}

    public boolean equals(Object e){
        Service two = (Service) e;
        return this.getServiceName().equals(two.getServiceName()) && this.getPrice() == two.getPrice();
    }

}

