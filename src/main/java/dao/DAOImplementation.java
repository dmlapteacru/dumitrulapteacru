package dao;

import configuration.HibernateConfig;
import models.Company;
import models.Employee;
import models.Project;
import models.enums.Role;
import models.enums.Status;
import models.enums.Type;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Scanner;


public class DAOImplementation implements DAO{

    @Override
    public void getEmployees() {

        List<Employee> list;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            System.out.println("GET ALL EMPLOYEES");
            list = session.createQuery("select distinct a from employee as a join a.skills").list();

            if (!list.isEmpty()){
                for(int i =0; i < list.size(); i++){
                    System.out.println(list.get(i));
                }
            }

            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void getEmployeesByProjectCode(int projectCode) {

        List<Employee> list;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            System.out.println("GET ALL EMPLOYEES BY PROJECT CODE - " + projectCode);
            list = session.createQuery("select a from employee a join a.projects b where b.project_code=:projectCode")
                    .setParameter("projectCode", projectCode)
                    .list();

            if (!list.isEmpty()){
                for(int i =0; i < list.size(); i++){
                    System.out.println(list.get(i));
                }
            }

            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void getProjectsByCompanyID(int companyID) {

        List<Employee> list;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            System.out.println("GET PROJECTS BY COMPANY ID - " + companyID);
            list = session.createQuery("select a from project a where a.company.id=:companyID")
                    .setParameter("companyID", companyID)
                    .list();

            if (!list.isEmpty()){
                for(int i =0; i < list.size(); i++){
                    System.out.println(list.get(i));
                }
            }

            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void getEmployeesBySkillType(Type type) {

        List<Employee> list;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            System.out.println("GET ALL EMPLOYEES BY SKILL TYPE - " + type);
            list = session.createQuery("select distinct a from employee a join a.skills b where b.type=:skillType")
                    .setParameter("skillType", type)
                    .list();

            if (!list.isEmpty()){
                for(int i =0; i < list.size(); i++){
                    System.out.println(list.get(i));
                }
            }

        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Company detachCompany(int id) {

        Company company = new Company();
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            System.out.println("DETACH COMPANY - " + id);
             company = (Company)session
                     .createQuery("select a from company a join a.projects b join a.employees c where a.id=:id")
                    .setParameter("id", id).getSingleResult();
            System.out.println(company);
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return company;
    }

    @Override
    public void loadAndShowCompany(Company company) {

        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            System.out.println("LOAD COMPANY ID - " + company.getId());
            session.load(Company.class, company.getId());
            System.out.println("Active employees:");
            company.getEmployees().stream()
                    .filter(e -> e.getStatus() == Status.ACTIVE)
                    .forEach(System.out::println);
            System.out.println("Active projects:");
            company.getProjects().stream()
                    .filter(p -> p.getStatus() == Status.ACTIVE)
                    .forEach(System.out::println);
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void editUserByUserId(String userID) {

        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            System.out.println("EDIT USER - " + userID);
            Employee employee = (Employee) session.createQuery("select a from employee a where a.userId=:userID")
                    .setParameter("userID", userID).getSingleResult();
            System.out.println("Change : 1 - fname, 2 - lname, 3 - role, 4 - status? \n Input: ");
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextByte()){
                case 1: employee.setFirst_name(scanner.nextLine());
                case 2: employee.setLast_name(scanner.nextLine());
                case 3: {
                    if(employee.getRole()== Role.ADMIN)
                        employee.setRole(Role.USER);
                                else employee.setRole(Role.ADMIN);
                }
                case 4: {
                    if (employee.getStatus() == Status.ACTIVE)
                        employee.setStatus(Status.INACTIVE);
                                else employee.setStatus(Status.ACTIVE);
                }
                default:session.saveOrUpdate(employee);
            }
            session.saveOrUpdate(employee);
            session.getTransaction().commit();
            System.out.println("changes commited");
            scanner.close();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void getProjectsByJavaSkill() {

        List<Project> list;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            System.out.println("GET ALL PROJECTS BY JAVA SKILL");
            list = session.createQuery("select b from employee a join a.projects b join a.skills c " +
                    "where c.skill=:ss group by b having count(a.id) > 1")
                    .setParameter("ss", "Java")
                    .list();

            if (!list.isEmpty()){
                for(int i =0; i < list.size(); i++){
                    System.out.println(list.get(i));
                }
            }

        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void softDeleteEmployee(String userID) {

        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            System.out.println("CHANGE EMPLOYEE STATUS - " + userID);
            Employee employee = (Employee) session.createQuery("from employee a where a.userId=:userID")
                    .setParameter("userID", userID)
                    .getSingleResult();
            if (employee.getStatus() == Status.ACTIVE)
                    employee.setStatus(Status.INACTIVE);
            else employee.setStatus(Status.ACTIVE);
            session.saveOrUpdate(employee);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void softDeleteProject(int projectCode) {

        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            System.out.println("CHANGE PROJECT STATUS - " + projectCode);
            Project project = (Project) session.createQuery("from project a where a.project_code=:projectCode")
                    .setParameter("projectCode", projectCode)
                    .getSingleResult();
            if (project.getStatus() == Status.ACTIVE)
                project.setStatus(Status.INACTIVE);
            else project.setStatus(Status.ACTIVE);

            session.saveOrUpdate(project);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static void br(){
        System.out.println("#----------------------------------------------------------------#");
    }

}
