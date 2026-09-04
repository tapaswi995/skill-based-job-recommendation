package dao;
import util.DatabaseConnection; import java.sql.*; import java.util.*;
public class ApplicationDAO {
 public boolean apply(int student,int job)throws SQLException{try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO applications(student_id,job_id,application_date,status) VALUES(?,?,CURDATE(),'Applied')")){p.setInt(1,student);p.setInt(2,job);p.executeUpdate();return true;}catch(SQLIntegrityConstraintViolationException e){return false;}}
 public List<Map<String,String>> byStudent(int id)throws SQLException{return list("WHERE a.student_id=?",id);}
 public List<Map<String,String>> all()throws SQLException{return list("",0);}
 private List<Map<String,String>> list(String where,int id)throws SQLException{List<Map<String,String>> out=new ArrayList<>();String q="SELECT a.application_id,a.application_date,a.status,s.name,j.job_title,j.company_name FROM applications a JOIN students s ON a.student_id=s.student_id JOIN jobs j ON a.job_id=j.job_id "+where+" ORDER BY a.application_date DESC";try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){if(!where.isEmpty())p.setInt(1,id);ResultSet r=p.executeQuery();while(r.next()){Map<String,String>m=new LinkedHashMap<>();m.put("id",r.getString(1));m.put("date",r.getString(2));m.put("status",r.getString(3));m.put("student",r.getString(4));m.put("title",r.getString(5));m.put("company",r.getString(6));out.add(m);}}return out;}
 public void status(int id,String value)throws SQLException{try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement("UPDATE applications SET status=? WHERE application_id=?")){p.setString(1,value);p.setInt(2,id);p.executeUpdate();}}
}
