package com.example.demo.model;

import java.util.Date;

public class Customer {

    private long id;
    private String name;
    private String number;
    //private Date birthday;


    public Customer(){
    }

    public Customer(String name, String number)
    {
        this.id=id;
        this.name=name;
        this.number=number;
    }

    public void setId(long id)
    {
        this.id=id;
    }
    public long getId()
    {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

