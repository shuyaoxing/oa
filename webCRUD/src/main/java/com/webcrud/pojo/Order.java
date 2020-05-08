package com.webcrud.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Order {
    private Integer id;
    private Double price;
    private String approver;
    private Date date;

    public Order(Integer id, Double price, String approver) {
        this.id = id;
        this.price = price;
        this.approver = approver;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getApprover() {
        return approver;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }
}
