package ada.wm2.CRUD.controller;

import ada.wm2.CRUD.entity.Course;
import ada.wm2.CRUD.entity.Student;
import ada.wm2.CRUD.repository.CourseRepository;
import ada.wm2.CRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/home")
    public String getStudentHome() {

        return "student/index";
    }

    @GetMapping("/list")
    public String getStudentList(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);

        return "student/list";
    }

    @GetMapping("/id")
    public String getById(Model model, @RequestParam Integer id) {
        Optional<Student> res = studentRepository.findById(id);
        if (res.isPresent()) {
            Student student = res.get();
            List<Student> stList = new ArrayList<Student>();
            stList.add(student);
            model.addAttribute("students", stList);
        }
        return "student/list";
    }

    @GetMapping("/new")
    public String showNewPage(Model model) {
        model.addAttribute("students", new Student());

        return "student/data";
    }

    @GetMapping("/update")
    public String showUpdatePage(Model model, @RequestParam Integer id) {
        Optional<Student> res = studentRepository.findById(id);
        if (res.isPresent()) {
            Student st = res.get();
            model.addAttribute("students", st);
        }
        return "student/data";
    }

    @PostMapping("/save")
    public String saveStudent(Student studentData) {
        studentRepository.save(studentData);

        return "student/index";
    }

    @GetMapping("/excellent")
    public String searchExcellent(Model model) {
        Iterable<Student> students = studentRepository.getExcellentPerformance("Excellent Performance");
        model.addAttribute("students", students);

        return "student/list";
    }

    @GetMapping("/master")
    public String searchMaster(Model model) {
        Iterable<Student> students = studentRepository.getDegrees("master");
        model.addAttribute("students", students);

        return "student/list";
    }

    @GetMapping("/bachelor")
    public String searchBachelor(Model model) {
        Iterable<Student> students = studentRepository.getDegrees("bachelor");
        model.addAttribute("students", students);

        return "student/list";
    }

    @GetMapping("/searchFirst")
    public String searchStudentsByFirst(Model model, @RequestParam String first) {
        Iterable<Student> searched = studentRepository.findByStudentFirstName(first);
        model.addAttribute("students", searched);

        return "student/list";
    }

    @GetMapping("/searchLast")
    public String searchStudentsByLastName(Model model, @RequestParam String last) {
        Iterable<Student> searched = studentRepository.findByStudentLastName(last);
        model.addAttribute("students", searched);

        return "student/list";
    }

    @GetMapping("/searchEmail")
    public String searchStudentsByEmail(Model model, @RequestParam String email) {
        Iterable<Student> searched = studentRepository.findByStudentEmail(email);
        model.addAttribute("students", searched);

        return "student/list";
    }

    @GetMapping("/delete")
    public String deleteStudent(Model model, @RequestParam Integer id) {

            Optional<Student> res = studentRepository.findById(id);
            if (res.isPresent()) {
                Student student = res.get();
                try {
                    studentRepository.delete(student);
                    model.addAttribute("message", "Student was successfully deleted!");
                    return "student/index";

                } catch (Exception ex) {
                    model.addAttribute("message", "Oops, something is wrong!");
                    return "student/index";
                }
            }
        model.addAttribute("students", studentRepository.findAll());
        return "student/index";
    }



    @GetMapping("/courses")
    public String getCourses(Model model, @RequestParam Integer id) {
        Optional<Student> res = studentRepository.findById(id);
        if (res.isPresent()) {
            Student st = res.get();

            model.addAttribute("fullname", st.getFirstName() + " " + st.getLastName());
            model.addAttribute("courses", st.getCourses());
            model.addAttribute("studentID", res.get().getStudentID());
        }
        return "enrollment/index";
    }

    @PostMapping("/dropCourseOnStudent")
    public String dropCourses(Model model, @RequestParam Integer courseNumber, @RequestParam Integer studentID) {

        Optional<Student> studRes = studentRepository.findById(studentID);
        if (studRes.isPresent()) {

            Optional<Course> courseRes = courseRepository.findById(courseNumber);
            if (courseRes.isPresent()) {
                Course course = courseRes.get();

                studRes.get().getCourses().remove(course);
                studentRepository.save(studRes.get());

                model.addAttribute("fullname", studRes.get().getFirstName() + " " + studRes.get().getLastName());
                model.addAttribute("courses", studRes.get().getCourses());
                model.addAttribute("studentID", studRes.get().getStudentID());
            }
        }
        return "enrollment/index";
    }

    @PostMapping("/addCourseOnStudent")
    public String addCourses(Model model, Course course, @RequestParam Integer studentID) {
        Optional<Student> studRes = studentRepository.findById(studentID);
        if (studRes.isPresent()) {

                    studRes.get().getCourses().add(course);
                    studentRepository.save(studRes.get());

                    model.addAttribute("fullname", studRes.get().getFirstName() + " " + studRes.get().getLastName());
                    model.addAttribute("courses", studRes.get().getCourses());
                    model.addAttribute("studentID", studRes.get().getStudentID());


            }

        return "enrollment/index";
    }
}
