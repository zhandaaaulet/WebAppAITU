package domain;

import java.util.Date;

public class Order {
    private int id;
    private Date date;
    private int product_id;
    private String username;

    public Order() {

    }

    public Order(int id, Date date, int product_id) {
        this.id = id;
        this.date = date;
        this.product_id = product_id;
    }

    public Order(int id, Date date, int product_id, String username) {
        this.id = id;
        this.date = date;
        this.product_id = product_id;
        this.username = username;
    }

    public Order(Date date, int product_id, String username) {
        this.date = date;
        this.product_id = product_id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }


}