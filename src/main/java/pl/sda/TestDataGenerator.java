package pl.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDataGenerator {
    public static void createTestData(SessionFactory sessionFactory){
        Session session = sessionFactory.openSession();

        Order order = new Order();
        order.setCustomerName("Jan Kowalski");
        order.setStatus(Status.NEW);
        order.setCreationDate(new Date());
        order.setCustomerAddress(new Address("Piramowicza 92", "łódź", "97-444"));
        order.setDeliveryAddress(new Address("Kilińskiego1", "łódź", "97-441"));

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setOrder(order);
        orderDetail1.setProductName("Skarpetki");
        orderDetail1.setQuantity(new BigDecimal(10));
        orderDetail1.setProductPrice(new BigDecimal(20.0));

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setOrder(order);
        orderDetail2.setProductName("T-shirt");
        orderDetail2.setQuantity(new BigDecimal(1));
        orderDetail2.setProductPrice(new BigDecimal(100.0));

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail1);
        orderDetails.add(orderDetail2);

        order.setOrderDetails(orderDetails);

        Transaction tx = session.getTransaction();
        tx.begin();
        Integer orderId = (Integer) session.save(order);
        System.out.println(orderId);
        tx.commit();
        session.close();
    }
}
