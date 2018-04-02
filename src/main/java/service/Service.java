package service;

import configuration.HibernateConfig;
import models.Employee;
import models.Skills;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class Service {
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

    public static List<Object> getEmployeeAndSkills() {

        List<Object> list = null;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            System.out.println("getAllInterns");
            list = session.createQuery("from employee join skills").list();
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

    public static void br(){
        System.out.println("#----------------------------------------------------------------#");
    }

}
