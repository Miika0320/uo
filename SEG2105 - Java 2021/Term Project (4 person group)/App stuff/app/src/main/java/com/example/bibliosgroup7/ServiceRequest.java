package com.example.bibliosgroup7;

import java.sql.Time;

public class ServiceRequest {

    private String ID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String DOB;
    private String address;
    private String license;
    private String car;
    private String pickup;
    private String returnDate;
    private String maxDistance;
    private String start;
    private String end;
    private String movers;
    private String boxes;
    private String serviceName;
    private String pickupTime;
    private String returnTime;
    private double price;
    private String accountId= "";
    private Boolean approved = false;
    private Account chosenBranch = new Account("-MpALotpbDoNfU6eJDit");
    ServiceRequest(){}
    
    ServiceRequest(String Id){
        this.ID = Id;
    }

    public Boolean getApproved() {
        return approved;
    }
    public String getaccountId(){ return accountId; }
    public void setaccountId(String id){this.accountId = id;}

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
    public Account getChosenBranch(){return this.chosenBranch;}

    public void setChosenBranch(Account chosenBranch) {
        this.chosenBranch = chosenBranch;
    }

    public String getServiceRequestId() {
        return ID;
    }

    public double getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public String getBoxes() {
        return boxes;
    }

    public String getCar() {
        return car;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEnd() {
        return end;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLicense() {
        return license;
    }

    public String getMaxDistance() {
        return maxDistance;
    }

    public String getMovers() {
        return movers;
    }

    public String getPickup() {
        return pickup;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getStart() {
        return start;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getPickupTime() { return pickupTime; }

    public String getReturnTime() { return returnTime; }

    public void setPickupTime(String pickupTime) { this.pickupTime = pickupTime; }

    public void setReturnTime(String returnTime) { this.returnTime = returnTime; }

    public void setCar(String car) {
        this.car = car;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setBoxes(String boxes) {
        this.boxes = boxes;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setServiceRequestId(String ID) {
        this.ID = ID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setMaxDistance(String maxDistance) {
        this.maxDistance = maxDistance;
    }

    public void setMovers(String movers) {
        this.movers = movers;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setStart(String start) {
        this.start = start;
    }

}
