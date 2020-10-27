package domain;

import java.util.Date;

public class OrderDTO {
    private int product_id;
    private Date date;


    public OrderDTO(Order order){
        setProduct_id(order.getProduct_id());
        setDate(order.getDate());
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}