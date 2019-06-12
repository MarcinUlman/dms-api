package dev.ulman.dms;

import dev.ulman.dms.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class DemoHibernate {

    public static void main(String[] args) {
        Supplier s1 = new Supplier();
        s1.setName("Farapol");
        s1.setPhoneNumber(123456789);
        Address s1Address = new Address();
        s1Address.setCity("Krakow");
        s1Address.setPostCode(32300);
        s1Address.setState("Malopolskie");
        s1Address.setStreet("Kolowa 16");
        s1.setAddress(s1Address);
        s1.setProducts(new ArrayList<>());

        Supplier s2 = new Supplier();
        s2.setName("SMay");
        s1.setPhoneNumber(123456756);
        Address s2Address = new Address();
        s2Address.setCity("Krakow");
        s2Address.setPostCode(32300);
        s2Address.setState("Malopolskie");
        s2Address.setStreet("Brzezna 16");
        s2.setAddress(s2Address);
        s2.setProducts(new ArrayList<>());

        Product p1 = new Product();
        p1.setName("rura1");
        p1.setPrice(new BigDecimal(3.5));
        p1.setSupplier(s1);
        s1.getProducts().add(p1);

        Product p2 = new Product();
        p2.setName("rura2");
        p2.setPrice(new BigDecimal(8.0));
        p2.setSupplier(s1);
        s2.getProducts().add(p2);

        Employee emp1 = new Estimator();
        emp1.setName("Ela");
        emp1.setSurname("Kowal");
        emp1.setEmploymentDate(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("PLT", ZoneId.SHORT_IDS))));

        Employee emp2 = new Trader();
        emp2.setSurname("Zrobowski");
        emp2.setName("Jan");

        emp2.setEmploymentDate(Timestamp.valueOf("2003-5-7 00:00:00"));

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(s1);
        session.save(s2);
        session.save(p1);
        session.save(p2);
        session.save(emp1);
        session.save(emp2);
        session.getTransaction().commit();

        session.close();

    }
}
