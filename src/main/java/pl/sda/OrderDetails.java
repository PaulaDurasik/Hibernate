package pl.sda;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class OrderDetails {
    @Id
   private Integer orderDetailId;
    @ManyToOne(fetch= FetchType.LAZY)
   private Order order;
   private String productName;
   private BigDecimal quantity;
   private BigDecimal productPrice;

    public OrderDetails() {
    }


    public OrderDetails(Integer orderDetailId, Order order, String productName, BigDecimal quantity, BigDecimal productPrice) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetails that = (OrderDetails) o;

        return orderDetailId.equals(that.orderDetailId);
    }

    @Override
    public int hashCode() {
        return orderDetailId.hashCode();
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailId=" + orderDetailId +
                ", order=" + order +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                '}';
    }
}
