package BusinessLayer;

import DataLayer.DataClass.*;
import TablesPack.*;
import java.util.List;

public class BusinessLogClass {

    private DataLayer.DataClass daL;

    public BusinessLogClass(){

        daL = new DataLayer.DataClass();

        List<Table> tables = daL.getInfoTablesAll();
        CreateProductTable();
        CreateBuyerTable();
        CreateTableOrders();
        CreateOrderedProd();
    }
    private void CreateBuyerTable() {
       daL.createBuyerTable();
    }
    private void CreateProductTable() {
        daL.createProductTable();
    }
    private void CreateTableOrders() {
       daL.createOrderTalbe();
    }
    public void CreateOrderedProd() {
       daL.createOrderedProd();
    }

    public int createBuyer(String buyerName) {
        try {
            daL.createBuyer(buyerName);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int createProduct(String nameProd) {
        try {
            daL.createProduct(nameProd);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int createOrder(Order order) {
        try {
            daL.createOrder(order);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public List<Buyer> getBuyer() {
        return daL.getBuyer();
    }

    public List<Product> getProduct() {
        return  daL.getProduct();
    }

    public List<Order> getOrder() {
        return  daL.getOrder();
    }

    public List<Order> getOrderedProd() {
        return daL.getOrderedProd();
    }


    public void editBuyer(Buyer buyer) {
        daL.editBuyer(buyer);
    }

    public void editProduct(Product product) {
        daL.editProduct(product);
    }

    public void addProductToOrder(Order order) {
        daL.addProductToOrder(order);
    }



    public void changeBuyer(Order order) {
        daL.changeBuyer(order);
    }

    public void removeBuyer(int id_Buyer)
    {
        daL.removeBuyer(id_Buyer);
    }

    public void removeProd(int id_Prod)
    {
        daL.removeProd(id_Prod);
    }

    public void removeOrder(int id_Order) {
        daL.removeOrder(id_Order);
    }

    public void removeProductFromOrder(Order order) {
        daL.removeProductFromOrder(order);
    }


}
