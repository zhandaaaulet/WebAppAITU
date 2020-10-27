package repositories.entities;



import domain.Order;
import repositories.db.PostgresRepository;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IOrderRepository;

import javax.ws.rs.BadRequestException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OrderedProductRepository implements IOrderRepository {
    private final IDBRepository dbrepo = new PostgresRepository();

    @Override
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public List<Order> getOrdersOfUserByName(String principal) {
        String sql = "SELECT * FROM orders WHERE username='" + principal + "'";
        return query(sql);
    }

    @Override
    public void add(Order entity) {
        try {
            String sql = "INSERT INTO orders(ordered_date, product_id, username) VALUES (?, ?, ?)";

            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setDate(1, (Date) entity.getDate());
            stmt.setInt(2, entity.getProduct_id());
            stmt.setString(3, entity.getUsername());

            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void remove(Order entity) {
        String sql = "DELETE FROM orders WHERE username=? AND order_id=?";
        try {
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, entity.getUsername());
            stmt.setInt(2, entity.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement: " + e.getMessage());
        }

    }

    @Override
    public void deleteOrder(int product_id, String username) {
        String sql = "DELETE FROM orders WHERE username=? AND product_id=?";
        try {
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, product_id);
            stmt.execute();

        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement: " + e.getMessage());
        }

    }

    @Override
    public void addProduct(int id,String user) {
        try {
            String sql = "INSERT INTO orders(ordered_date, product_id, username) VALUES (?, ?, ?)";

            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setDate(1, Date.valueOf("2020-06-10"));
            stmt.setInt(2, id);
            stmt.setString(3,user);

            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }

    @Override
    public List<Order> query(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Order> orders = new LinkedList<>();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getDate("ordered_date"),
                        rs.getInt("product_id"));

                orders.add(order);
            }
            return orders;
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getSQLState());
        }
    }

    @Override
    public Order queryOne(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Order order = new Order(rs.getInt("order_id"),
                        rs.getDate("ordered_date"),
                        rs.getInt("product_id"));

                return order;
            }
        } catch (SQLException e) {
            throw new BadRequestException();
        }
        return null;
    }


}