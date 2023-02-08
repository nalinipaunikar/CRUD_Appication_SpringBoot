package org.example.controller.service;

import org.example.model.Student;
import org.example.model.Teacher;
import org.example.repo.StudentRepoImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements StudentServiceImpl {
    private StudentRepoImpl r1;
    @Override
    public void insertSingle(Student s1){
        r1.insertSingle(s1);
    }

    @Override
    public void insertMultiple(List<Student> studentList) {
        for (int i=0; i< studentList.size(); i++) {
           r1.insertSingle(studentList.get(i));
        }
    }

    @Override
    public Student selectSingle(int sid, int tid) {
        return r1.selectSingle(sid ,tid);
    }

    @Override
    public List<Student> selectMultiple(List<Integer> sids, List<Integer> tids) {
        List<Student> students = new ArrayList<>();
        for(int i=0; i< sids.size(); i++){
            if (tids.size()<i){
               students.add(r1.selectSingle(sids.get(i),tids.get(tids.size())));
            }else {
                students.add(r1.selectSingle(sids.get(i), tids.get(i)));
            }
        }
        return students;
    }

    @Override
    public List<Student> selectAll() {
        return r1.selectAll();

    }

    @Override
    public Student updateSingle(Student s1) {
        return r1.updateSingle(s1);
    }

    @Override
    public List<Student> updateMultiple(List<Student> studentList) {
        for (int i=0; i< studentList.size(); i++){
            r1.updateSingle(studentList.get(i));
        }
        return studentList ;
    }

    @Override
    public Boolean deleteSingle(int sid, int tid) {
        return r1.deleteSingle(sid , tid);
    }

    @Override
    public Boolean deleteMultiple(List<Integer> sids, List<Integer> tids) {
        List<Boolean> booleanList = new ArrayList<>();
        Boolean result = false;
        for (int i=0; i<sids.size(); i++){
            booleanList.add(r1.deleteSingle(sids.get(i), tids.get(tids.size())));
        }
        for (int i=0; i<booleanList.size(); i++) {
            if (booleanList.get(i).equals(true)){
                result = true;
        }

        }
        return result;
    }

    @Override
    public Boolean deleteAll() {
        return r1.deleteAll();
    }
}
