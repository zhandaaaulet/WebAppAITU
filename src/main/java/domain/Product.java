package domain;

public class Product {
    private int product_id;
    public String name;
    public String type;
    public int price;
    public String company;


    public Product(){

    }
    public Product(int product_id, String name, String type, int price, String company) {
        this.product_id = product_id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.company = company;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Product(String name, String type, int price, String company) {
        this.name = name;
        this.type = type;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", company='" + company + '\'' +
                '}';
    }
}