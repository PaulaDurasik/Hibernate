package pl.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task7 {

    public static void main(String[] args) throws IOException {
        try (
            SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()){
                TestDataGenerator.createTestData(factory);
                try( Session session = factory.openSession()) { // otwieramy seshe
                    Transaction transaction = session.getTransaction();
                    transaction.begin();
                    final Query query = session.createQuery("select o from Order o where o.status = : status, Order.class");
                    query.setParameter("status",Status.NEW);
                    List<Order> orderList =query.getResultList();
                    List<Integer> updateOrderIs= new ArrayList <>();
                    for(Order order: orderList){
                        order.setStatus(Status.CLOSED);
                        updateOrderIs.add(order.getOrderId());
                }
                    transaction.commit();
                    System.out.println("numer zaktualizowany " + updateOrderIs);

            }
        }

    }
}
