package services;

import domain.Product;
import repositories.ProductRepository;
import repositories.interfaces.IProductRepository;
import services.interfaces.IProductService;

public class ProductService implements IProductService {
    private final IProductRepository productRepo = new ProductRepository();

    @Override
    public void addProduct(Product product) {
        productRepo.add(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepo.update(product);
    }

    @Override
    public void removeProduct(int id) {
        productRepo.deleteProductById(id);
    }

    @Override
    public Product getProductById(int id) {
        return productRepo.getProductByID(id);
    }
}