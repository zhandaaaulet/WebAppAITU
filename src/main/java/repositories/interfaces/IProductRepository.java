package repositories.interfaces;

import domain.Product;

public interface IProductRepository extends IEntityRepository<Product> {
Product getProductById(long id);
Product getProductByType(String type);
}
