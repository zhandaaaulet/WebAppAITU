package services;

import domain.Product;
import repositories.ProductRepository;
import repositories.interfaces.IProductRepository;
import services.interfaces.ISearchService;

import java.util.List;

public class SearchService implements ISearchService {
    IProductRepository products = new ProductRepository();


    @Override
    public Product searchProductById(int id) {
        return products.getProductByID(id);
    }

    @Override
    public List<Product> searchByTag(String request) {
        return products.getProductsByTag(request);
    }

    @Override
    public List<Product> searchProductByType(String type) {
        return products.getProductByType(type);
    }


}