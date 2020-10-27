package repositories.interfaces;

import domain.Order;

import java.util.List;

public interface IOrderRepository extends IEntityRepository<Order> {
    Order getOrderById(int id);
    List<Order> getOrdersOfUserByName(String principal);
    void deleteOrder(int id, String systemUser);
    void addProduct(int id,String user);
}