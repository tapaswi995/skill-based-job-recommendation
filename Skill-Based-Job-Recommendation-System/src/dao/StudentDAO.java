package dao;
import model.Student;
import util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class StudentDAO {
    public int register(Student s, String password) throws SQLException {
        String sql = "INSERT INTO students(name,email,password,phone,qualification,graduation_year) VALUES(?,?,?,?,?,?)";
        try (Connection c=DatabaseConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            p.setString(1,s.name); p.setString(2,s.email); p.setString(3,password); p.setString(4,s.phone); p.setString(5,s.qualification); p.setString(6,s.graduationYear); p.executeUpdate();
            ResultSet keys=p.getGeneratedKeys(); keys.next(); return keys.getInt(1);
        }
    }
    public Student login(String email, String password) throws SQLException {
        try (Connection c=DatabaseConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT * FROM students WHERE email=? AND password=?")) {
            p.setString(1,email); p.setString(2,password); ResultSet r=p.executeQuery(); return r.next()?read(r):null;
        }
    }
    public Student get(int id) throws SQLException { try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM students WHERE student_id=?")){p.setInt(1,id);ResultSet r=p.executeQuery();return r.next()?read(r):null;} }
    public void update(Student s) throws SQLException { try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement("UPDATE students SET name=?,phone=?,qualification=?,graduation_year=? WHERE student_id=?")){p.setString(1,s.name);p.setString(2,s.phone);p.setString(3,s.qualification);p.setString(4,s.graduationYear);p.setInt(5,s.id);p.executeUpdate();} }
    public void saveSkills(int id, List<String> skills) throws SQLException { try(Connection c=DatabaseConnection.getConnection()){c.setAutoCommit(false);try(PreparedStatement d=c.prepareStatement("DELETE FROM student_skills WHERE student_id=?")){d.setInt(1,id);d.executeUpdate();}try(PreparedStatement i=c.prepareStatement("INSERT INTO student_skills(student_id,skill_id) SELECT ?,skill_id FROM skills WHERE skill_name=?")){for(String skill:skills){i.setInt(1,id);i.setString(2,skill);i.addBatch();}i.executeBatch();}c.commit();} }
    public List<String> skills(int id) throws SQLException { List<String> out=new ArrayList<>();String q="SELECT skill_name FROM skills s JOIN student_skills ss ON s.skill_id=ss.skill_id WHERE ss.student_id=?";try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setInt(1,id);ResultSet r=p.executeQuery();while(r.next())out.add(r.getString(1));}return out; }
    public List<Student> all() throws SQLException {List<Student> out=new ArrayList<>();try(Connection c=DatabaseConnection.getConnection();Statement p=c.createStatement();ResultSet r=p.executeQuery("SELECT * FROM students ORDER BY student_id DESC")){while(r.next())out.add(read(r));}return out;}
    private Student read(ResultSet r)throws SQLException{Student s=new Student();s.id=r.getInt("student_id");s.name=r.getString("name");s.email=r.getString("email");s.phone=r.getString("phone");s.qualification=r.getString("qualification");s.graduationYear=r.getString("graduation_year");return s;}
}
