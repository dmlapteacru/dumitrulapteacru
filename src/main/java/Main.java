import models.Employee;
import models.Skills;
import models.enums.Type;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import configuration.HibernateConfig;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        getAllInterns().stream().forEach(System.out::println);
    }
    public static List<Employee> getAllInterns() {

        List<Employee> list = null;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            System.out.println("getAllInterns");
            list = session.createQuery("from employee").list();
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
