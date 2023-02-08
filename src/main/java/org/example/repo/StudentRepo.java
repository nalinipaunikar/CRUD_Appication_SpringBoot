package org.example.repo;

import org.example.model.Student;
import org.example.model.Teacher;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo implements StudentRepoImpl {
    String url = "jdbc:mysql://localhost:3306/nalini";
    String user = "root";
    String pass = "";

    public void insertSingle(Student s1){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url ,user ,pass);
            Statement st = con.createStatement();
            st.executeUpdate("insert into student1 values ('"+s1.getId()+"','"+s1.getName()+"','"+s1.getEmail()+"')");
            st.executeUpdate("insert into teacher values ('"+s1.getT1().getId()+"','"+s1.getT1().getName()+"','"+s1.getT1().getEmail()+"')");
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public Student selectSingle(int sid, int tid) {
        Student sd = new Student();
        Teacher t1 = new Teacher();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url ,user ,pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student1 where id = '"+sid+"'");
            while (rs.next()) {
                sd.setId(rs.getInt(1));
                sd.setName(rs.getString(2));
                sd.setEmail(rs.getString(3));
            }
            ResultSet rs1 = st.executeQuery("select * from teacher where id = '"+tid+"'");
            while (rs.next()) {
                t1.setId(rs1.getInt(1));
                t1.setName(rs1.getString(2));
                t1.setEmail(rs1.getString(3));
            }
            sd.setT1(t1);
        }catch (Exception e){
            System.out.println(e);
        }
        return sd;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> studentList = new ArrayList<>();
        List<Teacher> teacherList = new ArrayList<>();
        List<Student> Resultlist = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url ,user ,pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student1");
            while (rs.next()){
            Student sa = new Student();
            sa.setId(rs.getInt(1));
            sa.setName(rs.getString(2));
            sa.setEmail(rs.getString(3));
            studentList.add(sa);
            }
            ResultSet rs1 = st.executeQuery("select * from teacher");
            while (rs1.next()) {
                Teacher t1 = new Teacher();
                t1.setId(rs1.getInt(1));
                t1.setName(rs1.getString(2));
                t1.setEmail(rs1.getString(3));
                teacherList.add(t1);
            }
            for (int i=0; i<studentList.size(); i++){
                if (teacherList.size()<i) {
                    Student sc = studentList.get(i);
                    sc.setT1(teacherList.get(teacherList.size()));
                    Resultlist.add(sc);
                }else {
                    Student sc = studentList.get(i);
                    sc.setT1(teacherList.get(i));
                    Resultlist.add(sc);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return Resultlist;
    }

    @Override
    public Student updateSingle(Student s1) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url ,user ,pass);
            Statement st = con.createStatement();
            st.executeUpdate("update from student1 set name = '"+s1.getName()+"', email = '"+s1.getEmail()+"' where id= '"+s1.getId()+"'");
            st.executeUpdate("update from teacher set name = '"+s1.getT1().getName()+"', email = '"+s1.getT1().getEmail()+"' where id = '"+s1.getT1().getId()+"'");

        }catch (Exception e){
            System.out.println(e);
        }
        return s1;
    }

    @Override
    public Boolean deleteSingle(int sid, int tid) {
        Boolean Result = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url ,user ,pass);
            Statement st = con.createStatement();
           int StudentResult = st.executeUpdate("delete from student1 where id = '"+sid+"'");
           int TeacherResult = st.executeUpdate("delete from student1 where id = '"+tid+"'");
           if (StudentResult>0 || TeacherResult>0){
               Result = true;
           }
        }catch (Exception e){
            System.out.println(e);
        }
        return Result;
    }

    @Override
    public Boolean deleteAll() {
        Boolean Result = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url ,user ,pass);
            Statement st = con.createStatement();
            int Studentresult = st.executeUpdate("delete from student1");
            int Teacherresult = st.executeUpdate("delete from teacher");
            if (Studentresult>0 || Teacherresult>0){
                Result = true;
            }


        }catch (Exception e){
            System.out.println(e);
        }
        return Result;
    }
}
