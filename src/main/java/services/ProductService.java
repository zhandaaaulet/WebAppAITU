package services;

import domain.Product;
import repositories.entities.ProductRepository;
import repositories.interfaces.IProductRepository;

public class ProductService implements IProductService {
    private final IProductRepository userrepo = new ProductRepository();

    @Override
    public Product getProductByID(long id) {
        return userrepo.getProductById(id);
    }
}
