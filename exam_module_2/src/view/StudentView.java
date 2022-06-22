package view;

import model.Student;
import services.IStudentService;
import services.StudentService;
import utils.CsvUtils;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Scanner;

public class StudentView {

    private final IStudentService iStudentService = new StudentService();

    private static final Scanner sc = new Scanner(System.in);

    public void run() {
        menuManagerStudent();
        int choice;
        try {
            do {
                System.out.println("chọn chức năng: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        showStudent();
                        run();
                        break;
                    case 2:
                        addStudent();
                        run();
                        break;
                    case 3:
                        updateStudent();
                        run();
                        break;
                    case 4:
                        removeStudent();
                        run();
                        break;
                    case 5 :
                        showSort();
                        run();
                        break;
                    case 0:
                        System.out.println("Xin chào Tạm biệt!");
                        System.exit(0);
                    default:
                        System.out.println("nhập sai xin mời nhập lại");
                }
            } while (choice != -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        public void addStudent () {
            System.out.println("Nhập Id Sinh viên: ");
            long id = Long.parseLong(sc.nextLine());
            System.out.println("Họ Tên Sv: ");
            String name = sc.nextLine();
            while (name.isEmpty()) {
                System.out.println("Tên không được để trống! Hãy nhập lại .");
                System.out.println("Họ Tên Sv: ");
                name = sc.nextLine();
            }
            System.out.println("Nhập Tuổi Sv: ");
            int age = Integer.parseInt(sc.nextLine());
            while (age < 0) {
                System.out.println("Bạn nhập sai. Vui lòng nhập lại tuổi.");
                age = Integer.parseInt(sc.nextLine());
            }
            System.out.println("Nhập Giới tính Sv: ");
            String gender = sc.nextLine();
            while (gender.isEmpty()) {
                System.out.println("Giới tính không được để trống! Hãy nhập lại .");
                System.out.println("Giới tính Sv: ");
                gender = sc.nextLine();
            }
            System.out.println("Nhập Địa Chỉ Sv: ");
            String address = sc.nextLine();
            while (address.isEmpty()) {
                System.out.println("Địa chỉ không được để trống! Hãy nhập lại .");
                System.out.println("Địa chỉ Sv: ");
                address = sc.nextLine();
            }
            System.out.println("Nhập Điểm Trung Bình Sv: ");
            double avScore = Double.parseDouble(sc.nextLine());
            Student student = new Student(id, name, age, gender, address, avScore);
            iStudentService.add(student);
            System.out.println("Thêm Sinh vien Thành công!");

        }

        public void showStudent () {
            System.out.printf("%-16s %-15s %-15s%-15s%-15s %-15s\n", "ID", "Tên Sv", " Tuổi", "Giới Tính", "Địa Chỉ", "Điểm Trung Bình");
            System.out.println("--------------------------------------------------------------------------------------------------");
            for (Student student : iStudentService.findAll()) {
                System.out.printf("%-16s %-15s %-15s%-15s%-15s %-15s\n",
                        student.getId(),
                        student.getFullName(),
                        student.getAge(),
                        student.isGender(),
                        student.getAddress(),
                        student.getAverageScore()
                );
            }
            System.out.println("---------------------------------------------------------------------------------------------");
        }


        public void updateStudent () {
            List<Student> students = iStudentService.findAll();
            showStudent();
            System.out.println("Nhập ID Sv cần sửa: ");
            long id = Long.parseLong(sc.nextLine());
            while (!iStudentService.existtById(id)) {
                System.out.println("ID không tồn tại !  vui lòng nhập lại.");
                id = Long.parseLong(sc.nextLine());
            }
            for (Student student : iStudentService.findAll()) {
                if (student.getId() == id) {
                    System.out.println("Nhập Id mới: ");
                    id = Long.parseLong(sc.nextLine());
//                    while(iStudentService.existtById(id)){
//                        System.out.println("id dã tồn tại! nhập id khác.");
//                        id = Long.parseLong(sc.nextLine());
//                    }
                    student.setId(id);
                    iStudentService.update(student);
                    System.out.println("Tên SV cần thay đổi: ");
                    String name = sc.nextLine();
//                    while (iStudentService.existByName(name)){
//                        System.out.println("Tên đã tồn taij~ Nhập tên mới.");
//                        name = sc.nextLine();
//                    }
                    student.setFullName(name);
                    iStudentService.update(student);
                    System.out.println("Nhập tuổi mới: ");
                    int age = Integer.parseInt(sc.nextLine());
//                    while (age < 0) {
//                        System.out.println("Bạn nhập sai. Vui lòng nhập lại tuổi.");
//                        age = Integer.parseInt(sc.nextLine());
//                    }
                    student.setAge(age);
                    iStudentService.update(student);
                    System.out.println("Nhập Giới tính Sv: ");
                    String gender = sc.nextLine();
//                    while (gender.isEmpty()) {
//                        System.out.println("Giới tính không được để trống! Hãy nhập lại .");
//                        System.out.println("Giới tính Sv: ");
//                        gender = sc.nextLine();
//                    }
                    student.setGender(gender);
                    iStudentService.update(student);
                    System.out.println("Nhập Địa Chỉ Sv: ");
                    String address = sc.nextLine();
//                    while (address.isEmpty()) {
//                        System.out.println("Địa chỉ không được để trống! Hãy nhập lại .");
//                        System.out.println("Địa chỉ Sv: ");
//                        address = sc.nextLine();
//                    }
                    System.out.println("Nhập Điểm Trung Bình Sv: ");
                    double avScore = Double.parseDouble(sc.nextLine());
                    student.setAverageScore(avScore);
                    Student student1 = new Student(id, name, age, gender, address, avScore);
//                    iStudentService.update(student1);
                    students.add(student1);
                    CsvUtils.write("data/student.csv", students);
                    System.out.println("Cập nhật Sinh vien Thành công!");
                }
            }
        }
        public void showSort () {
            sortStudent(iStudentService.sortStudent());
        }

        public void sortStudent (List < Student > studentList) {
            System.out.printf("%-16s %-15s %-15s%-15s%-15s %-15s\n", "ID", "Tên Sv", " Tuổi", "Giới Tính", "Địa Chỉ", "Điểm Trung Bình");
            System.out.println("--------------------------------------------------------------------------------------------------");
            for (Student student : studentList) {
                System.out.printf("%-16s %-15s %-15s%-15s%-15s %-15s\n",
                        student.getId(),
                        student.getFullName(),
                        student.getAge(),
                        student.isGender(),
                        student.getAddress(),
                        student.getAverageScore()
                );
            }
            System.out.println("---------------------------------------------------------------------------------------------");
        }


        public void removeStudent () {
            showStudent();
            System.out.println("Nhập id muốn xóa: ");
            long id = Long.parseLong(sc.nextLine());
            while (!iStudentService.existtById(id)) {
                System.out.println("ID Not Exist! Please Retry !");
                id = Long.parseLong(sc.nextLine());
            }
            iStudentService.removeById(id);
            System.out.println("xóa ID: " + id + " thành công !");
        }

//        try {
//            System.out.println("Enter Id You Want Remove: ");
//            System.out.print("✈✈: ");
//             id = Long.parseLong(sc.nextLine());
//            while (!iStudentService.existtById(id)) {
//                System.out.println("ID Not Exist! Please Retry !");
//                id = Long.parseLong(sc.nextLine());
//            }
//            iStudentService.removeById();
//            System.out.println("Delete ID: " + id + " Successful !");
//        } catch (Exception e) {
//            System.out.println("ID Not Incorrect!");
//        }


        public void menuManagerStudent () {
            System.out.println("------------------------------------------------------");
            System.out.println("-----------   Programs Manager Student     -----------");
            System.out.println("------------------------------------------------------");
            System.out.println("-          1. Xem danh sách sinh viên                -");
            System.out.println("-          2. Thêm mới                               -");
            System.out.println("-          3. Cập nhật                               -");
            System.out.println("-          4. Xóa                                    -");
            System.out.println("-          5. Sắp Xếp                                -");
            System.out.println("-          6. Đọc từ file                            -");
            System.out.println("-          7. Ghi vào file                           -");
            System.out.println("-          0. Thoát                                  -");
            System.out.println("------------------------------------------------------");
        }

        public static void main (String[]args){
            StudentView studentView = new StudentView();
//        studentView.addStudent();
//            studentView.updateStudent();
//            studentView.showStudent();
//            studentView.showSort();
//        studentView.removeStudent();
            studentView.run();
        }
    }
