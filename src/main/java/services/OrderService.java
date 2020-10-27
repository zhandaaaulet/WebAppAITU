package services;

import domain.Order;

import repositories.entities.OrderedProductRepository;
import repositories.interfaces.IOrderRepository;
import services.interfaces.IOrderService;

import java.util.List;

public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository = new OrderedProductRepository();

    @Override
    public List<Order> getMyCartProducts(String systemUser) {
        return orderRepository.getOrdersOfUserByName(systemUser);

    }


    @Override
    public void addOrder(Order order) {
        orderRepository.add(order);
    }

    @Override
    public void addorder(int product_id, String systemUser) {
        orderRepository.addProduct(product_id,systemUser);
    }

    @Override
    public void removeOrder(int id, String systemUser) {
        orderRepository.deleteOrder(id, systemUser);
    }
}