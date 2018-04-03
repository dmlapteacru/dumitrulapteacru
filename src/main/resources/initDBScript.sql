
INSERT INTO skills(type, skill) VALUES ('TECHNICAL', "Java");
INSERT INTO skills(type, skill) VALUES ('TECHNICAL', "Python");
INSERT INTO skills(type, skill) VALUES ('SOFT', "Leadership");
INSERT INTO skills(type, skill) VALUES ('SOFT', "Teamwork");

INSERT INTO country(ISO_code, name) VALUES (2048, "MOLDOVA");
INSERT INTO country(ISO_code, name) VALUES (5284, "RUSSIA");

INSERT INTO address(country_id, city, street, street_num) VALUES (1, "CHISINAU", "EMINESCU", 23);
INSERT INTO address(country_id, city, street, street_num) VALUES (1, "CHISINAU", "N. Dimo", 15);
INSERT INTO address(country_id, city, street, street_num) VALUES (2, "MOSCOW", "POSELKOVO", 2);

INSERT INTO company (name) VALUES ("Endava");
INSERT INTO company (name) VALUES ("Google");
INSERT INTO company (name) VALUES ("Amazon");

INSERT INTO employee(userId, first_name, last_name,  company_id, role, status) VALUES ("dlapteacru", "Dumitru", "Lapteacru",1, "ADMIN", "ACTIVE");
INSERT INTO employee(userId, first_name, last_name,  company_id, role, status) VALUES ("aursu", "Ana", "Ursu",2, "USER", "ACTIVE");
INSERT INTO employee(userId, first_name, last_name,  company_id, role, status) VALUES ("ppetkov", "Petr", "Petkov",1, "USER", "INACTIVE");

INSERT INTO skills_employee (employees_id, skills_id) VALUES (1,1);
INSERT INTO skills_employee (employees_id, skills_id) VALUES (2,1);
INSERT INTO skills_employee (employees_id, skills_id) VALUES (1,2);
INSERT INTO skills_employee (employees_id, skills_id) VALUES (2,2);
INSERT INTO skills_employee (employees_id, skills_id) VALUES (2,4);
INSERT INTO skills_employee (employees_id, skills_id) VALUES (3,2);
INSERT INTO skills_employee (employees_id, skills_id) VALUES (3,3);
INSERT INTO skills_employee (employees_id, skills_id) VALUES (3,4);

INSERT INTO project(description, name, project_code, company_id, status) VALUES ("Inside", "SQL TEST", 7878, 1, "ACTIVE");
INSERT INTO project(description, name, project_code, company_id, status) VALUES ("Inside", "Java Test", 7879, 1, "ACTIVE");
INSERT INTO project(description, name, project_code, company_id, status) VALUES ("OUTSide", "Flutter", 2325, 2, "INACTIVE");

INSERT INTO employee_projects(employee_id, project_id) VALUES (1,2);
INSERT INTO employee_projects(employee_id, project_id) VALUES (1,3);
INSERT INTO employee_projects(employee_id, project_id) VALUES (2,3);
INSERT INTO employee_projects(employee_id, project_id) VALUES (3,1);

INSERT INTO adress_employee(adress_id, employees_id) VALUES (1,1);
INSERT INTO adress_employee(adress_id, employees_id) VALUES (2,2);
INSERT INTO adress_employee(adress_id, employees_id) VALUES (3,3);


# select * from employee
#   join skills_employee
#     on employees_id = employee.id
#   join skills
#     on skills_id = skills.id
# # --------------------
# SELECT * FROM employee
# ----------------------
#
# SELECT b.* FROM employee a
# JOIN employee_projects c
# on c.employee_id=a.id
# JOIN project b
# on b.id=c.project_id
# JOIN skills_employee d
# on d.employees_id=a.id
#   JOIN skills f
#   on f.id=d.skills_id
# where f.skill="Java"
# GROUP BY project_id
# HAVING count(a.id) > 1
