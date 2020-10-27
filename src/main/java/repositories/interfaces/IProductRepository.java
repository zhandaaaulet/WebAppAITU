package repositories.interfaces;

import domain.Product;

import java.util.List;

public interface IProductRepository extends IEntityRepository<Product> {
    Product getProductByID(int id);
    List<Product> getProductByType(String type);
    List<Product> getProductsByTag(String request);
    void deleteProductById(int product_id);
}