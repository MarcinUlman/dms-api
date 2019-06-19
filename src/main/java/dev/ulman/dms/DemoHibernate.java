package dev.ulman.dms;

import dev.ulman.dms.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;

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


        Session session = HibernateUtil.getSessionFactory().openSession();
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

class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            try {

                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties

                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "org.postgresql.Driver");

                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/dms");

                settings.put(Environment.USER, "postgres");

                settings.put(Environment.PASS, "postgres");

                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(dev.ulman.dms.model.Contract.class);
                configuration.addAnnotatedClass(dev.ulman.dms.model.Customer.class);
                configuration.addAnnotatedClass(dev.ulman.dms.model.Department.class);
                configuration.addAnnotatedClass(dev.ulman.dms.model.Employee.class);
                configuration.addAnnotatedClass(dev.ulman.dms.model.Estimator.class);
                configuration.addAnnotatedClass(dev.ulman.dms.model.Offer.class);
                configuration.addAnnotatedClass(dev.ulman.dms.model.OfferDetails.class);
                configuration.addAnnotatedClass(dev.ulman.dms.model.Product.class);
                configuration.addAnnotatedClass(dev.ulman.dms.model.Supplier.class);
                configuration.addAnnotatedClass(dev.ulman.dms.model.Trader.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()

                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return sessionFactory;

    }

}

