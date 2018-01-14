package pl.sda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.xml.XmlMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.List;

public class Task5 {
    public static void main(String args[]) throws IOException {
        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            TestDataGenerator.createTestData(factory);
            try(Session session = factory.openSession()) {
                //Order order1 = session.get(Order.class, 1);
                //System.out.println(order1);
                final Query<Order> query = session.createQuery("select o from Order o where customerAddress.city = :city", Order.class);
                query.setParameter("city", "Lodz");
                List<Order> orderList = query.getResultList();
                for(Order order: orderList){
                    System.out.println(order);
                }

                //Zapis do XML
                ObjectMapper xmlMapper = new ObjectMapper();
                xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                xmlMapper.setDateFormat(df);
                for(Order order: orderList){
                    String xml = xmlMapper.writeValueAsString(order);
                    Files.write(Paths.get("order_" + order.getOrderId() + ".xml"), xml.getBytes(), StandardOpenOption.CREATE);
                }

                //Zapis do JSON
                ObjectMapper jsonMapper = new ObjectMapper();
                jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
                jsonMapper.setDateFormat(df);
                for(Order order: orderList){
                    String json = jsonMapper.writeValueAsString(order);
                    Files.write(Paths.get("order_" + order.getOrderId() + ".json"), json.getBytes(), StandardOpenOption.CREATE);
                }
            }
        }
    }

}
