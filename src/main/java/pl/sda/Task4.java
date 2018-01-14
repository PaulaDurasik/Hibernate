package pl.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task4 {
    public static void main(String args[]){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Order order=new Order();
        order.setCustomerName("Jan Kowalski");
        order.setStatus(Status.NEW);
        order.setCreationDate(new Date());
        order.setCustomerAddress(new Address("łódź", "Piramowicza 12", "97-444"));
        order.setDeliveryAddress(new Address("łódź", "Kilińskiego 1", "97-444"));
        OrderDetail orderDetail= new OrderDetail();
        orderDetail.setProductName("Skarpetki");
        orderDetail.setOrder(order);
        orderDetail.setQuantity(new BigDecimal(10));
        orderDetail.setProductPrice(new BigDecimal(20));

        OrderDetail orderDetail2= new OrderDetail();
        orderDetail2.setProductName("T-shirt");
        orderDetail2.setOrder(order);
        orderDetail2.setQuantity(new BigDecimal(1));
        orderDetail2.setProductPrice(new BigDecimal(100));

        List<OrderDetail> orderDetails= new ArrayList <>();
        orderDetails.add(orderDetail);
        orderDetails.add(orderDetail2);
        order.setOrderDetails(orderDetails);
        Transaction tx=session.getTransaction();
        tx.begin();
        Integer orderId=(Integer) session.save(order);
        System.out.println(orderId);
        session.save(orderDetail);
        session.save(orderDetail2);
        tx.commit();

        session.close();
        factory.close();




    }


}


