package TablesPack;

import java.util.List;


public class Order {
    public int id_Order;
    public Buyer buyerName;
    public List<Product> Products;

    public Order(int id_Order){
        this.id_Order = id_Order;
    }
    public Order(List<Product> Products, int id_Order, Buyer buyerName){
        this.Products = Products;
        this.id_Order = id_Order;
        this.buyerName = buyerName;
    }
    public Order(List<Product> Products,Buyer buyerName){
        this.id_Order = 0;
        this.buyerName = buyerName;
        this.Products = Products;
    }
}
