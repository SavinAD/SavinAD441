package DataLayer;

import TablesPack.Buyer;
import TablesPack.Order;
import TablesPack.Product;
import TablesPack.Table;

//import javax.swing.plaf.nimbus.State;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DataClass {
    private String conStrings;
    private boolean first;

    public DataClass() {
        conStrings = "jbc:sqdlite:test.db";
        first = true;
    }

    public List<Table> getInfoTablesAll() {
        List<Table> tables = new ArrayList<Table>();
        Connection con = null;
        ResultSet resSet = null;

        try {
            con = DriverManager.getConnection(conStrings);
            DatabaseMetaData meta = con.getMetaData();
            resSet = meta.getTables(null, null, null, new String[]{"TABLE"});
            while (resSet.next()) {
                tables.add(new Table(resSet.getString("TABLE_NAME")));
            }
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
            }
            catch (Exception exception) {
            }
        }
        return tables;
    }

    public boolean createProductTable() {
        Connection con = null;
        ResultSet resSet = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.createStatement();
            String query = "CREATE TABLE Product " +
                    "(id_Prod INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " nameProd nvarchar(100) NOT NULL)";
            stmt.execute(query);
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
            } catch (Exception exception) {
            }
            return false;
        }
        return true;
    }

    public boolean createBuyerTable() {
        Connection con = null;
        ResultSet resSet = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.createStatement();
            String query = "CREATE TABLE Buyer " +
                    "(id_Buyer INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " buyerName nvarchar(100) NOT NULL)";
            stmt.execute(query);
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
            } catch (Exception exception) {
            }
            return false;
        }
        return true;
    }

    public boolean createOrderTalbe() {
        Connection con = null;
        ResultSet resSet = null;
        Statement stmt = null;
        PreparedStatement state = null;
        try {
            con = DriverManager.getConnection(conStrings);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String query = "CREATE TABLE Order " +
                    "(id_Order INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "buyerName int NOT NULL," +
                    "foreign key (buyerName) references Buyer(id_Buyer) ON DELETE CASCADE)";
            stmt.execute(query);
            con.commit();

        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
                state.close();
                stmt.close();
            } catch (Exception exception) {
            }
            return false;
        }
        return true;
    }

    public boolean createOrderedProd() {
        Connection con = null;
        ResultSet resSet  = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.createStatement();

            String query = "CREATE TABLE OrderedProd " +
                    "(IDRow INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "id_Order INT  NOT NULL," +
                    "id_Prod INT NOT NULL," +
                    "FOREIGN KEY (id_Order) REFERENCES Order(id_Order) ON DELETE CASCADE," +
                    "FOREIGN KEY (id_Prod) REFERENCES Product(id_Prod) ON DELETE CASCADE)";
            stmt.execute(query);
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
            } catch (Exception exception) {
            }
            return false;
        }
        return true;
    }

    public int createBuyer(String buyerName) {
        Connection con = null;
        ResultSet resSet  = null;
        PreparedStatement stmt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.prepareStatement("INSERT INTO Buyer(buyerName) VALUES(?)");
            stmt.setString(1, buyerName);
            stmt.executeUpdate();
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
            } catch (Exception exception) {
            }
            return 0;
        }
        return 1;
    }

    public int createProduct(String nameProd) {
        Connection con = null;
        ResultSet resSet = null;
        PreparedStatement stmt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.prepareStatement("INSERT INTO Product(nameProd) VALUES(?)");
            stmt.setString(1, nameProd);
            stmt.executeUpdate();
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
            } catch (Exception exception) {
            }
            return 0;
        }
        return 1;
    }

    public int createOrder(Order order) {
        Connection con = null;
        ResultSet resSet = null;
        PreparedStatement stmt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);
            String query = "INSERT INTO Order(buyerName) VALUES(?)";
            con.setAutoCommit(false);
            stmt = con.prepareStatement(query);
            stmt.setInt(1, order.buyerName.id_Buyer);
            stmt.executeUpdate();
            Statement st = con.createStatement();
            resSet = st.executeQuery("select last_insert_rowid()");
            int idOrder = -1;
            if(resSet.next()) {
                idOrder = resSet.getInt(1);
            }
            stmt = con.prepareStatement("INSERT INTO OrderedProd(id_Order,id_Prod) VALUES (?,?)");
            for (Product product : order.Products) {
                stmt.setInt(1, idOrder);
                stmt.setInt(2, product.id_Prod);
                stmt.executeUpdate();
            }
            con.commit();
            resSet.close();
            con.close();
            stmt.close();
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
                stmt.close();
            } catch (Exception exception) {
            }
            return 0;
        }
        return 1;
    }

    public List<Buyer> getBuyer() {
        List<Buyer> buyers = new ArrayList<Buyer>();
        Connection con = null;
        ResultSet resSet = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.createStatement();
            String query = "SELECT * FROM Buyer";
            resSet = stmt.executeQuery(query);
            while (resSet.next()) {
                buyers.add(new Buyer(resSet.getInt("id_Buyer"),resSet.getString("buyerName")));
            }
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
                stmt.close();
            }
            catch (Exception exception) {
            }
        }
        return buyers;
    }

    public List<Product> getProduct() {
        List<Product> products = new ArrayList<Product>();
        Connection con = null;
        ResultSet resSet= null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.createStatement();
            String query = "SELECT * FROM Product";
            resSet = stmt.executeQuery(query);
            while (resSet.next()) {
                products.add(new Product(resSet
                        .getInt("id_Prod"),resSet
                        .getString("nameProd")));
            }
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
                stmt.close();
            }
            catch (Exception exception) {
            }
        }
        return products;
    }

    public List<Order> getOrder() {
        List<Order> orders = new ArrayList<Order>();
        Connection con = null;
        ResultSet resSet = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.createStatement();
            String query = "SELECT Order.id_Order, Buyer.buyerName AS buyerName FROM Order LEFT JOIN Buyer ON Buyer.id_Buyer = Order.buyerName ";
            resSet = stmt.executeQuery(query);
            while (resSet.next()) {
                int id_Order = resSet.getInt("id_Order");
                String buyerName = resSet.getString("buyerName");
                orders.add(new Order(null,id_Order, new Buyer(0,buyerName)));
            }
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
                stmt.close();
            }
            catch (Exception exception) {
            }
        }
        return orders;
    }

    public List<Order> getOrderedProd() {
        List<Order> orders = new ArrayList<Order>();
        Connection con = null;
        ResultSet resSet = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.createStatement();
            String query = "SELECT Buyer.buyerName AS Buyer, Product.nameProd AS Product FROM OrderedProd LEFT JOIN Order ON Order.id_Order = OrderedProd.id_Order " +
                    "LEFT JOIN Product ON Product.id_Prod = OrderedProd.id_Prod " +
                    "LEFT JOIN Buyer ON Buyer.id_Buyer = Order.Buyer  ";
            resSet = stmt.executeQuery(query);
            while (resSet.next()) {
                String nameProd = resSet.getString("nameProd");
                List<Product> products = new ArrayList<Product>();
                products.add(new Product(1, nameProd));
                Order order = new Order(products,new Buyer(resSet.getString("buyerName")));
                orders.add(order);
            }
            resSet.close();
            con.close();
            stmt.close();
        } catch (Exception e) {
            try {
                resSet.close();
                con.close();
                stmt.close();
            }
            catch (Exception exception) {
            }
        }
        return orders;
    }

    public void editBuyer(Buyer buyerName) {
        Connection con = null;
        PreparedStatement stmt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.prepareStatement("UPDATE Buyer SET buyerName = ? WHERE id_Buyer = ?");
            stmt.setString(1, buyerName.buyerName);
            stmt.setInt(2, buyerName.id_Buyer);
            stmt.executeUpdate();
        } catch (Exception e) {
            try {
                con.close();
                stmt.close();
            } catch (Exception exception) {
            }
        }
    }

    public void changeBuyer(Order order) {
        Connection con = null;
        PreparedStatement stmt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);

            stmt = con.prepareStatement("UPDATE Order SET buyerName = ? WHERE id_Order = ?");
            stmt.setInt(1, order.buyerName.id_Buyer);
            stmt.setInt(2, order.id_Order);
            stmt.executeUpdate();
            con.close();
            stmt.close();
        } catch (Exception e) {
            try {
                con.close();
                stmt.close();
            } catch (Exception exception) {
            }
        }
    }

    public void addProductToOrder(Order order) {
        Connection con = null;
        PreparedStatement stmt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.prepareStatement("INSERT INTO OrderedProd(id_Order,id_Prod) VALUES (?,?)");

            for (Product product : order.Products) {
                stmt.setInt(1, order.id_Order);
                stmt.setInt(2, product.id_Prod);
                stmt.executeUpdate();
            }
            con.close();
            stmt.close();
        } catch (Exception e) {
            try {
                con.close();
                stmt.close();
            } catch (Exception exception) {
            }
        }
    }

    public void removeProductFromOrder(Order order) {
        Connection con = null;
        PreparedStatement stmt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);

            stmt = con.prepareStatement("DELETE FROM OrderedProd WHERE id_Order = ? AND id_Prod = ?");

            for (Product product : order.Products) {
                stmt.setInt(1, order.id_Order);
                stmt.setInt(2, product.id_Prod);
                stmt.executeUpdate();
            }
            con.close();
            stmt.close();
        } catch (Exception e) {
            try {
                con.close();
                stmt.close();
            } catch (Exception exception) {
            }
        }
    }

    public void editProduct(Product product) {
        Connection con = null;
        PreparedStatement stmt = null;
        int id = -1;

        try {
            con = DriverManager.getConnection(conStrings);
            stmt = con.prepareStatement("UPDATE Product SET nameProd = ? WHERE id_Prod = ?");
            stmt.setString(1, product.nameProd);
            stmt.setInt(2, product.id_Prod);
            stmt.executeUpdate();
        } catch (Exception e) {
            try {
                con.close();
                stmt.close();
            } catch (Exception exception) {
            }
        }
    }

    public void removeBuyer(int idBuyer) {
        Connection con = null;
        PreparedStatement stmt = null;
        Statement opt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);
            opt = con.createStatement();
            opt.execute("PRAGMA foreign_keys = ON;");
            stmt = con.prepareStatement("DELETE FROM Buyer WHERE id_Buyer = ?");
            stmt.setInt(1, idBuyer);
            stmt.executeUpdate();
        } catch (Exception e) {
            try {
                con.close();
                opt.close();
                stmt.close();
            } catch (Exception exception) {
            }
        }
    }

    public void removeProd(int idProduct) {
        Connection con = null;
        PreparedStatement stmt = null;
        Statement opt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);
            opt = con.createStatement();
            opt.execute("PRAGMA foreign_keys = ON;");
            stmt = con.prepareStatement("DELETE FROM Product WHERE id_Prod = ?");
            stmt.setInt(1, idProduct);
            stmt.executeUpdate();
        } catch (Exception e) {
            try {
                opt.close();
                con.close();
                stmt.close();
            } catch (Exception exception) {
            }
        }
    }

    public void removeOrder(int idOrder) {
        Connection con = null;
        PreparedStatement stmt = null;
        Statement opt = null;
        int id = -1;
        try {
            con = DriverManager.getConnection(conStrings);
            opt = con.createStatement();
            opt.execute("PRAGMA foreign_keys = ON;");
            stmt = con.prepareStatement("DELETE FROM Order WHERE id_Order = ?");
            stmt.setInt(1, idOrder);
            stmt.executeUpdate();
        } catch (Exception e) {
            try {
                con.close();
                stmt.close();
                opt.close();
            } catch (Exception exception) {
            }
        }
    }


}
