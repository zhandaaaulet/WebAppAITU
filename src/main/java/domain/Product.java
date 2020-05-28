package domain;

import repositories.interfaces.IProductRepository;

public class Product{
    private long id;
    private String name;
    private String type;
    private String price;

    public Product(long id, String name, String type, String price) {
        setId(id);
        setName(name);
        setType(type);
        setPrice(price);
    }

    public Product(String name, String type, String price) {
        setName(name);
        setType(type);
        setPrice(price);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
