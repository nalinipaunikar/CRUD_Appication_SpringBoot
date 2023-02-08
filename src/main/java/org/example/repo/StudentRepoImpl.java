package org.example.repo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.example.model.Student;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentRepoImpl {
    void insertSingle(Student s1);
    Student selectSingle(int sid,int tid);
    List<Student> selectAll();
    Student updateSingle(Student s1);
    Boolean deleteSingle (int sid , int tid);
    Boolean deleteAll();

}
