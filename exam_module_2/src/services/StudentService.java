package services;

import model.Student;
import utils.CsvUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentService implements IStudentService{

    private static final String PATH = "data/student.csv";
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        List<String> record = CsvUtils.read(PATH);
        for (String records: record ){
                   students.add(Student.parse(records));
        }
        return students;
    }

    @Override
    public void add(Student newStudent) {
        List<Student> students = findAll();
          students.add(newStudent);
        CsvUtils.write(PATH,students);
    }

    @Override
    public void update(Student newStudent) {

        List<Student> students = findAll();
        for (Student student : students) {
            if (student.getId() == newStudent.getId()) {
                long id = newStudent.getId();
                if(id != 0){
                    student.setId(newStudent.getId());
                }
                String name = newStudent.getFullName();
                if (name != null && !name.isEmpty()) {
                    student.setFullName(newStudent.getFullName());
                }
                int age = newStudent.getAge();
                if (age != 0 ){
                    student.setAge(newStudent.getAge());
                }
                String gender = newStudent.isGender();
                if (gender != null && !gender.isEmpty()) {
                    student.setGender(newStudent.isGender());
                }
                String address = newStudent.getAddress();
                if (address != null && !address.isEmpty()) {
                    student.setAddress(newStudent.getAddress());
                }
                Double avS = newStudent.getAverageScore();
                if (avS != 0) {
                    student.setAverageScore(newStudent.getAverageScore());
                }
                CsvUtils.write(PATH, students);
                break;
            }
        }
    }

    @Override
    public boolean existtById(long id) {
        List<Student> students = findAll();
        for (Student manga : students) {
            if (manga.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public boolean existByName(String name) {
        List<Student> users = findAll();
        for (Student user : users) {
            if (user.getFullName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public boolean exist(int id) {
        return false;
    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public List<Student> sortStudent() {
        List<Student> sortName = new ArrayList<>(findAll());
        sortName.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                String a = o1.getFullName();
                String b = o2.getFullName();
                int c = b.compareTo(a);
                if (c > 0) {
                    return 1;
                } else if (c < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return sortName;
    }







}
