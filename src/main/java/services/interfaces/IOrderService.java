package services.interfaces;

import domain.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getMyCartProducts(String name);

    void addOrder(Order order);

    void removeOrder(int order_id, String name);

    void addorder(int id, String name);

    ;
}