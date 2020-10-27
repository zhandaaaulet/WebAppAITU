package repositories;

import domain.Product;
import repositories.db.PostgresRepository;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IProductRepository;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final IDBRepository dbrepo = new PostgresRepository();

    @Override
    public Product getProductByID(int id) {
        String sql = "SELECT * FROM products WHERE product_id=" + id;
        return queryOne(sql);
    }

    @Override
    public List<Product> getProductByType(String type) {
        String sql = "SELECT * FROM products WHERE type='" + type +"'";
        return query(sql);
    }

    @Override
    public List<Product> getProductsByTag(String request) {
        String sql = "SELECT * FROM products WHERE (type ILIKE '"+request+"%') OR (name ILIKE '"+request+"%') OR (company ILIKE '"+request+"%')";
        return query(sql);
    }

    @Override
    public void deleteProductById(int product_id) {
        String sql="DELETE FROM products WHERE product_id=?";
        try {
            PreparedStatement stmt= dbrepo.getConnection().prepareStatement(sql);
            stmt.setInt(1,product_id);
            stmt.execute();

        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement: " +"The Product does not exist");
        }

    }


    @Override
    public void add(Product entity) {
        String insert="INSERT INTO products(name, type, price, company) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement stmt=dbrepo.getConnection().prepareStatement(insert);
            stmt.setString(1,entity.getName());
            stmt.setString(1,entity.getType());
            stmt.setInt(1,entity.getPrice());
            stmt.setString(1,entity.getCompany());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void remove(Product entity) {

    }

    @Override
    public List<Product> query(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product found = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getString("company"));

                products.add(found);
            }
            return products;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product queryOne(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getString("company"));

                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}