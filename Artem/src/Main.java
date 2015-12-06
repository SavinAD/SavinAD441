import BusinessLayer.BusinessLogClass;
import TablesPack.Buyer;
import TablesPack.Order;
import TablesPack.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static BusinessLogClass bLogic = new BusinessLogClass();


    public static void main(String[] args) throws Exception {
       // showMainMenu();
        System.out.println("Добро Пожаловать,вы впервые работаете с данной базой?");
        System.out.println("Введите число '1' если ДА, или '0' если НЕТ");
        Scanner scaner=new Scanner(System.in);
        int param=0;
        String line;
        String name;
        int result;
         line=scaner.toString();

        while ((line = scaner.next()) != null) {
            param=Integer.parseInt(line);
           if(param==1){
               System.out.println("Cоздайте пользователя:");
               name = CreatBuyer();
               result = bLogic.createBuyer(name);
               if (result > 0) {
                   System.out.println("Added(name = " + name + ")");
                   if(param==1){
                       System.out.println("Ваш клиент создан!");
                       System.out.println("Теперь вы можете работать с полным списком");
                       showMainMenu();

                   }
               } else {
                   System.out.println("Error");
               }
              // showMainMenu();
           }
        }

        if(param==0){
            System.out.println("С чем вы хотите работать?:");
            showMainMenu();
        }


        SetMenu();

    }

    public static void showMainMenu() {
        System.out.println("Press key to:");
        System.out.println("1: Create Buyer");
        System.out.println("2: Create Order");
        System.out.println("3: Create Product");
        System.out.println("4: Edit Buyer");
        System.out.println("5: Edit Order");
        System.out.println("6: Edit Product");
        System.out.println("7: Delete Buyer");
        System.out.println("8: Delete Order");
        System.out.println("9: Delete Product");
        System.out.println("10: Select Buyer");
        System.out.println("11: Select Products");
        System.out.println("12: Select Orders");
        System.out.println("13: Read ProductOrder");
        System.out.println("14: Read BuyerOrder");
        SetMenu();
    }

    public static void showOrderProductList(List<Order> ordersList) {
        for (Order order : ordersList) {
            System.out.println(order.buyerName.buyerName + " : " + order.Products.get(0).nameProd);
        }
    }

    public static String CreatBuyer() {
        System.out.println("Create Buyer: ");
        System.out.println("Enter Buyer name: ");
        Scanner scaner = new Scanner(System.in);
        String name = scaner.nextLine();
        return name;
    }

    public static String CreateProduct() {
        System.out.println("Create Product");
        System.out.println("Enter Product name: ");
        Scanner scaner = new Scanner(System.in);
        String name = scaner.nextLine();
        return name;
    }

    public static Order CreateOrder() {
        System.out.println("Create Order");
        System.out.println("Enter Buyer id: ");
        Scanner scaner = new Scanner(System.in);
        int id_Buyer = Integer.valueOf(scaner.nextLine());
        System.out.println("Enter Product id:");
        String line = scaner.nextLine();
        String[] productsId = line.split(",");
        List<Product> products = new ArrayList<Product>();
        for (String product : productsId) {
            products.add(new Product(Integer.valueOf(product), ""));
        }
        Order order = new Order(products, new Buyer(id_Buyer));
        return order;
    }

    public static void ShowBuyer(List<Buyer> buyers) {
        System.out.println("Show Buyers:");
        for (Buyer buyer : buyers) {
            System.out.println("id: " + buyer.id_Buyer + " Name: " + buyer.buyerName);
        }
    }

    public static void ShowProducts(List<Product> products) {
        System.out.println("Show Products");
        for (Product product : products) {
            System.out.println("id: " + product.id_Prod + " Name: " + product.nameProd);
        }
    }

    public static void ShowOrders(List<Order> orders) {
        System.out.println("Show Orders");
        for (Order order : orders) {
            System.out.println("id: " + order.id_Order + ", Buyer name: " + order.buyerName.buyerName);
        }
    }

    public static Buyer showEditBuyer() {
        System.out.println("Edit Buyer");
        System.out.println("Enter Buyer id: ");
        Scanner scaner = new Scanner(System.in);
        int id = Integer.parseInt(scaner.nextLine());
        System.out.println("Enter Buyer name: ");
        String name = scaner.nextLine();
        return new Buyer(id, name);
    }

    public static Product showEditProduct() {
        System.out.println("Edit produc");
        System.out.println("Enter product id: ");
        Scanner scaner = new Scanner(System.in);
        int id = Integer.parseInt(scaner.nextLine());
        System.out.println("Enter product: ");
        String name = scaner.nextLine();
        return new Product(id, name);
    }

    public static int showRemoveBuyer() {
        System.out.println("Remove Buyer");
        System.out.println("Enter Buyer id: ");
        Scanner scaner = new Scanner(System.in);
        int id = Integer.parseInt(scaner.nextLine());
        return id;
    }
    public static void SetMenu(){
        String line;
        String name;
        int result;
        Scanner scaner = new Scanner(System.in);

        while ((line = scaner.next()) != null) {
            int a=Integer.parseInt(line);
            switch (a) {
                case 1:
                    name = CreatBuyer();
                    result = bLogic.createBuyer(name);
                    if (result > 0) {
                        System.out.println("Added(name = " + name + ")");
                    } else {
                        System.out.println("Error");
                    }
                    showMainMenu();
                    break;
                case 2:
                    Order order = CreateOrder();
                    result = bLogic.createOrder(order);
                    if (result > 0) {
                        System.out.println("Order Added");
                    } else {
                        System.out.println("Error");
                    }
                    showMainMenu();
                    break;
                case 3:
                    name = CreateProduct();
                    result = bLogic.createProduct(name);
                    if (result > 0) {
                        System.out.println("Product Added(name = " + name + ")");
                    } else {
                        System.out.println("Error");
                    }
                    showMainMenu();
                    break;
                case 4:
                    Buyer buyer = showEditBuyer();
                    bLogic.editBuyer(buyer);
                    break;
                case 5:
                    showMenuOrder();
                    showMainMenu();
                    break;
                case 6:
                    Product product = showEditProduct();
                    bLogic.editProduct(product);
                    showMainMenu();
                    break;
                case 7:
                    int id_Buyer = showRemoveBuyer();
                    bLogic.removeBuyer(id_Buyer);
                    showMainMenu();
                    break;
                case 8:
                    int id_Ord = showRemoveOrder();
                    bLogic.removeOrder(id_Ord);
                    showMainMenu();
                    break;
                case 9:
                    int id_Prod = showRemoveProduct();
                    bLogic.removeProd(id_Prod);
                    showMainMenu();
                    break;
                case 10:
                    ShowBuyer(bLogic.getBuyer());
                    showMainMenu();
                    break;
                case 11:
                    ShowProducts(bLogic.getProduct());
                    showMainMenu();
                    break;
                case 12:
                    ShowOrders(bLogic.getOrder());
                    showMainMenu();
                    break;
                case 13:
                    System.out.println("----");
                    List<Order> ordersList = bLogic.getOrderedProd();
                    showOrderProductList(ordersList);
                    showMainMenu();
                    break;
                case 14:
                    System.out.println("----");
                    ShowOrders(bLogic.getOrder());
                    showMainMenu();
                    break;


            }
        }
    }
    public static int showRemoveProduct() {
        System.out.println("Remove product");
        System.out.println("Enter product id: ");
        Scanner scaner = new Scanner(System.in);
        int id = Integer.parseInt(scaner.nextLine());
        return id;
    }

    public static int showRemoveOrder() {
        System.out.println("Remove order");
        System.out.println("Enter order id : ");
        Scanner scaner = new Scanner(System.in);
        int id = Integer.parseInt(scaner.nextLine());
        return id;
    }

    public static void showMenuOrder() {

        System.out.println("Orders:");
        System.out.println("15: Add product to order");
        System.out.println("16: Remove product from order");
        System.out.println("17: Change buyer");

        Scanner scaner = new Scanner(System.in);
        int item = scaner.nextInt();
        Order order = null;
        switch (item) {
            case 15:
                order = addProductToOrder();
                bLogic.addProductToOrder(order);
                break;
            case 16:
                order = removeProductFromOrder();
                bLogic.removeProductFromOrder(order);
                break;
            case 17:
                order = changeBuyer();
                bLogic.changeBuyer(order);
                break;
            default:
                showMenuOrder();
        }
    }

    public static Order addProductToOrder() {
        Scanner scanerr = new Scanner(System.in);
        System.out.println("Enter order id: ");
        int id_Order = Integer.parseInt(scanerr.next());
        System.out.println("Enter Products id:");
        String line = scanerr.next();
        String[] id_Prod = line.split(",");
        List<Product> products = new ArrayList<Product>();
        for (String product : id_Prod) {
            products.add(new Product(Integer.valueOf(product), ""));
        }
        return new Order(products, id_Order, null);
    }

    public static Order removeProductFromOrder() {
        Scanner scanerr = new Scanner(System.in);
        System.out.println("Enter order id: ");
        int id_Order = Integer.parseInt(scanerr.next());
        System.out.println("Enter Product id)");
        String line = scanerr.next();
        String[] id_Prod = line.split(",");
        List<Product> products = new ArrayList<Product>();
        for (String product : id_Prod) {
            products.add(new Product(Integer.valueOf(product), ""));
        }
        return new Order(products, id_Order, null);
    }

    public static Order changeBuyer() {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Enter order id: ");
        int id_Order = Integer.parseInt(scaner.next());
        System.out.println("Enter Buyer id: ");
        int id_Buyer = Integer.parseInt(scaner.next());
        return new Order(null, id_Order, new Buyer(id_Buyer, null));
    }
}


