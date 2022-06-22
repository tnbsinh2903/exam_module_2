package services;

import model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> findAll();

    void  add(Student newStudent);
    void update(Student newStudent);
    boolean existtById( long id);
    boolean existByName(String name);
    boolean exist(int id);
    void removeById(long id);
    List<Student> sortStudent();

}
