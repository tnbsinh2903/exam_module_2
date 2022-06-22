package model;

public class Student {

    private long id;
    private String fullName;
    private int age;
    private String gender;
    private String address;
    private double averageScore;


    public Student() {
    }

    public Student(long id, String fullName, int age, String gender, String address, double averageScore) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.averageScore = averageScore;
    }


    public static Student parse(String student) {
        Student students = new Student();
        String[] files = student.split(",");
        students.id = Long.parseLong(files[0]);
        students.fullName = files[1];
        students.age = Integer.parseInt(files[2]);
        students.gender = files[3];
        students.address = files[4];
        students.averageScore = Double.parseDouble(files[5]);
        return new Student(students.id, students.fullName, students.age, students.gender, students.address, students.averageScore);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String isGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",
                id,
                fullName,
                age,
                gender,
                address,
                averageScore);
    }
}
