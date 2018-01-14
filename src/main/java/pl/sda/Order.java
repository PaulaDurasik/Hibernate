package pl.sda;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Integer orderId;
    private String customerName;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "customer_street")),
            @AttributeOverride(name = "city", column = @Column(name = "customer_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "customer_postal_code"))})
    private Address customerAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "delivery_street")),
            @AttributeOverride(name = "city", column = @Column(name = "delivery_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "delivery_postal_code"))})


    private Address deliverAdress;
    @Enumerated(EnumType.STRING)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;


    public Order() {
    }

    public Order(Integer orderId, String customerName, Address customerAddress,
                 Address deliverAdress, String status, Date creationDate, List <OrderDetails> orderDetails) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.deliverAdress = deliverAdress;
        this.status = status;
        this.creationDate = creationDate;
        this.orderDetails = orderDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Address getDeliverAdress() {
        return deliverAdress;
    }

    public void setDeliverAdress(Address deliverAdress) {
        this.deliverAdress = deliverAdress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List <OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List <OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return orderId == order.orderId;
    }


    @Override
    public int hashCode() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress=" + customerAddress +
                ", deliverAdress=" + deliverAdress +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
