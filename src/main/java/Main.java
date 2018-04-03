import dao.DAOImplementation;
import models.Company;
import models.enums.Type;

import static dao.DAOImplementation.*;

public class Main {
    public static void main(String[] args) {
        DAOImplementation daoImplementation = new DAOImplementation();
            daoImplementation.getEmployees();
        br();
            daoImplementation.getEmployeesByProjectCode(7878);
        br();
            daoImplementation.getEmployeesByProjectCode(2325);
        br();
           daoImplementation.getProjectsByCompanyID(1);
        br();
            daoImplementation.getEmployeesBySkillType(Type.TECHNICAL);
        br();
            Company company = daoImplementation.detachCompany(1);
            daoImplementation.loadAndShowCompany(company);
        br();
            daoImplementation.editUserByUserId("dlapteacru");
        br();
            daoImplementation.getProjectsByJavaSkill();
        br();
            daoImplementation.softDeleteEmployee("aursu");
        br();
            daoImplementation.softDeleteProject(7879);
    }

}
