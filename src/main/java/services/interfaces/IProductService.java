package services.interfaces;

import domain.Product;

public interface IProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    void removeProduct(int id);
    Product getProductById(int id);

}