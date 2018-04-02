DROP TABLE country;
DROP TABLE address;
DROP TABLE employee;

CREATE TABLE country
(
  id INT AUTO_INCREMENT,
  ISO_code INT,
  name VARCHAR(20),
  PRIMARY KEY (id)
);
CREATE UNIQUE INDEX country_iso_code_uindex ON country (ISO_code);

CREATE TABLE address
(
  id INT AUTO_INCREMENT,
  country_id INT,
  city VARCHAR(20),
  street VARCHAR(20),
  street_num INT,
  FOREIGN KEY (country_id) REFERENCES country(id),
  PRIMARY KEY (id)
);

CREATE TABLE skills
(
  id INT AUTO_INCREMENT,
  type ENUM('SOFT', 'TECHNICAL'),
  skill VARCHAR(20),
  PRIMARY KEY (id)
);


CREATE TABLE employee
(
  id INT AUTO_INCREMENT,
  userId VARCHAR(20) NOT NULL,
  first_name VARCHAR(20),
  last_name VARCHAR(20),
  address_id INT,
  role ENUM('ADMIN', 'USER'),
  FOREIGN KEY (address_id) REFERENCES address(id),
  PRIMARY KEY (id)
);
CREATE UNIQUE INDEX employee_userId_uindex ON employee (userId);

CREATE TABLE employee_skills
(
  employee_id INT,
  skills_id INT,
  FOREIGN KEY (employee_id) REFERENCES employee(id),
  FOREIGN KEY (skills_id) REFERENCES skills(id)
);



INSERT INTO skills(type, skill) VALUES ('SOFT', "Java");
INSERT INTO skills(type, skill) VALUES ('SOFT', "JavaFX");

INSERT INTO country(ISO_code, name) VALUES (2048, "MOLDOVA");
INSERT INTO country(ISO_code, name) VALUES (5284, "RUSSIA");

INSERT INTO address(country_id, city, street, street_num) VALUES (1, "CHISINAU", "EMINESCU", 23);
INSERT INTO address(country_id, city, street, street_num) VALUES (2, "MOSCOW", "POSELKOVO", 2);

INSERT INTO company (name) VALUES ("Endava");
INSERT INTO company (name) VALUES ("Google");

INSERT INTO employee(userId, first_name, last_name, address_id, company_id, role) VALUES ("dlapteacru", "Dumitru", "Lapteacru",1,1, "ADMIN");
INSERT INTO employee(userId, first_name, last_name, address_id, company_id, role) VALUES ("aursu", "Ana", "Ursu",2,2, "USER");

INSERT INTO skills_employee (employees_id, skills_id) VALUES (1,1);
INSERT INTO skills_employee (employees_id, skills_id) VALUES (1,2);
INSERT INTO skills_employee (employees_id, skills_id) VALUES (2,2);



select * from employee
  join skills_employee
    on employees_id = employee.id
  join skills
    on skills_id = skills.id
# --------------------

# ----------------------

