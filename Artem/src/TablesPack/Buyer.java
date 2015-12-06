package TablesPack;


public class Buyer {
    public int id_Buyer;
    public String buyerName;

    public Buyer(int id_Buyer, String buyerName) {
        this.id_Buyer = id_Buyer;
        this.buyerName = buyerName;
    }

    public Buyer(String buyerName) {
        this.id_Buyer = id_Buyer;
        this.buyerName = buyerName;
    }

    public Buyer(int id_Buyer) {
        this.id_Buyer = id_Buyer;
        this.buyerName = "";
    }
}
