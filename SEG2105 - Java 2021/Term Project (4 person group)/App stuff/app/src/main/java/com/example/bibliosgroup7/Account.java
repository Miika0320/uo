package com.example.bibliosgroup7;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Account {
    private String username = "";
    private String role = "";
    private String password = "";
    private String id = "";
    private String AccountAddress = "";
    private String number = "";
    private String monOpen = "";
    private String monClose = "";
    private String tuesOpen = "";
    private String tuesClose = "";
    private String wedOpen = "";
    private String wedClose = "";
    private String thursOpen = "";
    private String thursClose = "";
    private String friOpen = "";
    private String friClose = "";
    private String satOpen = "";
    private String satClose = "";
    private String sunOpen = "";
    private String sunClose = "";
    private ArrayList<String> ratings = new ArrayList<String>();
    private ArrayList<Service> active = new ArrayList<Service>();
    public Account(){}
    public Account(String s){}

    public Account(String id, String username, String type, String password){
        this.id = id;
        this.role = type;
        this.username = username;
        this.password = password;
    }
    public Account(String username, String type, String password){
        this.role = type;
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getAccountId(){
        return id;
    }
    public void setAccountId(String id){this.id = id;}
    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Service> getActive() {
        return active;
    }
    public void addActive (Service service) {this.active.add(service); }

    public void setRatings(ArrayList<String> ratings) { this.ratings = ratings; }
    public void addRating(String rating){this.ratings.add(rating); }
    public void delRating(String rating){this.ratings.remove(rating); }
    public ArrayList<String> getRatings() {return ratings;}

    public void setActive (ArrayList<Service> active) { this.active = active; }

    public void delActive (Service service) {this.active.remove(service);}

    public boolean equals(Object e){
        Account two = (Account) e;
        return this.getAccountId().equals(two.getAccountId());
    }

    public String getMonOpen(){
        return monOpen;
    }
    public String getMonClose(){
        return monClose;
    }
    public String getTuesOpen(){
        return tuesOpen;
    }
    public String getTuesClose(){
        return tuesClose;
    }
    public String getWedOpen(){
        return wedOpen;
    }
    public String getWedClose(){
        return wedClose;
    }
    public String getThursOpen(){
        return thursOpen;
    }
    public String getThursClose(){
        return thursClose;
    }
    public String getFriOpen(){
        return friOpen;
    }
    public String getFriClose(){
        return friClose;
    }
    public String getSatOpen(){
        return satOpen;
    }
    public String getSatClose(){
        return satClose;
    }
    public String getSunOpen(){
        return sunOpen;
    }
    public String getSunClose(){
        return sunClose;
    }

    public void setWorkingHours (String monOpen, String monClose, String tuesOpen, String tuesClose, String wedOpen, String wedClose, String thursOpen, String thursClose, String friOpen, String friClose, String satOpen, String satClose, String sunOpen, String sunClose)
    {
        this.monOpen = monOpen;
        this.monClose = monClose;
        this.tuesOpen = tuesOpen;
        this.tuesClose = tuesClose;
        this.wedOpen = wedOpen;
        this.wedClose = wedClose;
        this.thursOpen = thursOpen;
        this.thursClose = thursClose;
        this.friOpen = friOpen;
        this.friClose = friClose;
        this.satOpen = satOpen;
        this.satClose = satClose;
        this.sunOpen = sunOpen;
        this.sunClose = sunClose;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountAddress() {
        return AccountAddress;
    }

    public void setAccountAddress(String address) {
        this.AccountAddress = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
