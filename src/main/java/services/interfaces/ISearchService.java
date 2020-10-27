package services.interfaces;

import domain.Product;

import java.util.List;

public interface ISearchService {
    Product searchProductById(int id);
    List<Product> searchByTag(String request);


    List<Product> searchProductByType(String type);
}