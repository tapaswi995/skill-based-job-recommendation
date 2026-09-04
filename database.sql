CREATE DATABASE IF NOT EXISTS job_recommendation_system;
USE job_recommendation_system;

CREATE TABLE students (student_id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, email VARCHAR(120) NOT NULL UNIQUE, password VARCHAR(100) NOT NULL, phone VARCHAR(20), qualification VARCHAR(100), graduation_year VARCHAR(10));
CREATE TABLE skills (skill_id INT AUTO_INCREMENT PRIMARY KEY, skill_name VARCHAR(80) NOT NULL UNIQUE);
CREATE TABLE student_skills (student_id INT, skill_id INT, PRIMARY KEY(student_id,skill_id), FOREIGN KEY(student_id) REFERENCES students(student_id) ON DELETE CASCADE, FOREIGN KEY(skill_id) REFERENCES skills(skill_id));
CREATE TABLE jobs (job_id INT AUTO_INCREMENT PRIMARY KEY, company_name VARCHAR(120) NOT NULL, job_title VARCHAR(120) NOT NULL, location VARCHAR(100) NOT NULL, job_description TEXT NOT NULL, eligibility VARCHAR(200) NOT NULL, posted_date DATE NOT NULL);
CREATE TABLE job_skills (job_id INT, skill_id INT, PRIMARY KEY(job_id,skill_id), FOREIGN KEY(job_id) REFERENCES jobs(job_id) ON DELETE CASCADE, FOREIGN KEY(skill_id) REFERENCES skills(skill_id));
CREATE TABLE applications (application_id INT AUTO_INCREMENT PRIMARY KEY, student_id INT NOT NULL, job_id INT NOT NULL, application_date DATE NOT NULL, status ENUM('Applied','Under Review','Shortlisted','Rejected') NOT NULL DEFAULT 'Applied', UNIQUE(student_id,job_id), FOREIGN KEY(student_id) REFERENCES students(student_id) ON DELETE CASCADE, FOREIGN KEY(job_id) REFERENCES jobs(job_id) ON DELETE CASCADE);
CREATE TABLE admins (admin_id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(100) NOT NULL);

INSERT INTO skills(skill_name) VALUES ('Java'),('HTML'),('CSS'),('JavaScript'),('SQL'),('Python'),('C'),('C++'),('Data Structures'),('Git'),('Communication');
INSERT INTO students(name,email,password,phone,qualification,graduation_year) VALUES ('Demo Student','student@example.com','student123','9876543210','B.Tech Computer Science','2026');
INSERT INTO student_skills VALUES (1,1),(1,2),(1,3),(1,5),(1,9),(1,10);
INSERT INTO admins(username,password) VALUES ('admin','admin123');
INSERT INTO jobs(company_name,job_title,location,job_description,eligibility,posted_date) VALUES
('TechNova','Java Developer','Bengaluru','Build and maintain Java applications.','B.Tech/BCA, 2024-2026','2026-08-20'),
('PixelWorks','Frontend Developer','Hyderabad','Create responsive web interfaces.','Any graduate, 2024-2026','2026-08-21'),
('CloudSoft','Software Engineer','Pune','Work with a team on product features.','B.Tech/B.E., 2024-2026','2026-08-22'),
('WebCraft','Web Developer','Chennai','Develop modern company websites.','Any graduate','2026-08-23'),
('DataSphere','SQL Developer','Mumbai','Write SQL queries and database reports.','B.Tech/BCA, 2024-2026','2026-08-24'),
('CodeLabs','Junior Java Developer','Bengaluru','Learn and contribute to Java services.','Freshers welcome','2026-08-25'),
('BrightApps','Full Stack Developer','Hyderabad','Build frontend and backend modules.','B.Tech/BCA, 2024-2026','2026-08-26'),
('NextGen','Graduate Software Engineer','Pune','Graduate engineering programme.','2026 graduates','2026-08-27'),
('InsightAI','Python Developer','Remote','Develop Python data utilities.','B.Tech/BCA, 2024-2026','2026-08-28'),
('SystemsPro','C++ Programmer','Noida','Create high performance desktop components.','B.Tech/B.E., 2024-2026','2026-08-29');
INSERT INTO job_skills VALUES
(1,1),(1,5),(1,9),(1,10),(2,2),(2,3),(2,4),(2,10),(3,1),(3,5),(3,9),(3,10),(4,2),(4,3),(4,4),(4,10),(5,5),(5,1),(5,10),(6,1),(6,5),(6,9),(7,1),(7,2),(7,3),(7,4),(7,5),(8,1),(8,9),(8,10),(9,6),(9,5),(9,10),(10,8),(10,9),(10,10);
