package repositories.entities;

import domain.Product;
import repositories.db.PostgresRepository;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IProductRepository;

import javax.ws.rs.BadRequestException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository implements IProductRepository {
    private final IDBRepository dbrepo = new PostgresRepository();

    @Override
    public Product queryOne(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Product product = new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("price"));

                return product;
            }
        } catch (SQLException e) {
            throw new BadRequestException();
        }
        return null;
    }

    @Override
    public Product getProductById(long id) {
        String sql = "SELECT * FROM products WHERE id=" + id + " LIMIT 1";
        return queryOne(sql);
    }


    @Override
    public Product getProductByType(String type) {
        return null;
    }

    @Override
    public void add(Product entity) {

    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void remove(Product entity) {

    }

    @Override
    public Iterable<Product> query(String sql) {
        return null;
    }
}
