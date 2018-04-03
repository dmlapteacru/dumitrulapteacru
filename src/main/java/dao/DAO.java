package dao;

import models.Company;
import models.enums.Type;


public interface DAO {
    void getEmployees();
    void getEmployeesByProjectCode(int projectCode);
    void getProjectsByCompanyID(int companyID);
    void getEmployeesBySkillType(Type type);
    Company detachCompany(int companyID);
    void loadAndShowCompany(Company company);
    void editUserByUserId(String userID);
    void getProjectsByJavaSkill();
    void softDeleteEmployee(String userID);
    void softDeleteProject(int projectCode);
}
