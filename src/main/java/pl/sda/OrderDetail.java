package pl.sda;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue
    @Column(name="order_detail_id")
    private Integer orderDetailId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")// jeżelui jest kluczem obcym to piszemy joincolumn zamiast coulmn
    private Order order;
    @Column(name="product_name")
    private String productName;
    @Column(name="product_quality")
    private BigDecimal quantity;
    @Column(name="product_price")
    private BigDecimal productPrice;

    public OrderDetail() {
    }

    public OrderDetail(Integer orderDetailId, Order order, String productName, BigDecimal quantity, BigDecimal productPrice) {
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
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(orderDetailId, that.orderDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailId);
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + order.getOrderId() +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                '}';
    }
}
