package model;

public class OrderDetailModel extends AbstractModel<OrderDetailModel>{
    
    private int orderId;
    private int productId;
    private int quantity;
    private String customerName;
    private String productName;
    private int price;
    private int total;
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }  

    public int getTotal() {
        return this.price * this.quantity;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
