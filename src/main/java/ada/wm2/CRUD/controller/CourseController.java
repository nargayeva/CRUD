package ada.wm2.CRUD.controller;

import ada.wm2.CRUD.entity.Course;
import ada.wm2.CRUD.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/home")
    public String getCourseHome() {
        return "course/index";
    }

    @GetMapping("/list")
    public String getCourseList(Model model) {
        Iterable<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "course/list";
    }

//    @GetMapping("/number")
//    public String getById(Model model, @RequestParam Integer number) {
//        Optional<Course> res = courseRepository.findById(number);
//        if (res.isPresent()) {
//            Course course = res.get();
//            List<Course> courseList = new ArrayList<Course>();
//            courseList.add(course);
//            model.addAttribute("courses", courseList);
//        }
//        return "course/list";
//    }

    @GetMapping("/new")
    public String getNewCourse(Model model) {
        model.addAttribute("courses", new Course());
        return "course/data";
    }

    @PostMapping("save")
    public String saveCourse(Course newCourse) {
        courseRepository.save(newCourse);
        return "course/index";
    }

    @GetMapping("/update")
    public String updateCourse(Model model, @RequestParam Integer number) {
        Optional<Course> res = courseRepository.findById(number);
        if (res.isPresent()) {
            Course course = res.get();
            model.addAttribute("courses", course);
        }
        return "course/data";
    }

    @GetMapping("/searchNumber")
    public String searchCourseNumber(Model model, @RequestParam("courseNumber") Integer courseNumber) {
        Course searched = courseRepository.findByCourseNumber(courseNumber);
        model.addAttribute("courses", searched);
        return "course/list";
    }

    @GetMapping("/searchName")
    public String searchCourseName(Model model, @RequestParam String name) {
        Iterable<Course> searched = courseRepository.findByCourseName(name);
        model.addAttribute("courses", searched);
        return "course/list";
    }


    @GetMapping("/freeElective")
    public String getFreeElective(Model model) {
        Iterable<Course> searched = courseRepository.getCourseTypes("free elective");
        model.addAttribute("courses", searched);

        return "course/list";
    }

    @GetMapping("/major")
    public String getMajor(Model model) {
        Iterable<Course> searched = courseRepository.getCourseTypes("major");
        model.addAttribute("courses", searched);

        return "course/list";
    }

    @GetMapping("/genEd")
    public String getGenEd(Model model) {
        Iterable<Course> searched = courseRepository.getCourseTypes("general education");
        model.addAttribute("courses", searched);

        return "course/list";
    }

    @GetMapping("/delete")
    public String deleteCourse(Model model, @RequestParam Integer number) {
        Optional<Course> res = courseRepository.findById(number);

        if (res.isPresent()) {
            Course course = res.get();
            try {
                courseRepository.delete(course);
                model.addAttribute("message", "Course was successfully deleted!");
                return "course/index";
            } catch (Exception ex) {
                model.addAttribute("message", "Oops, something is wrong, check whether this course has been taken by any student or not.");
                return "course/index";
            }
        }
        model.addAttribute("courses", courseRepository.findAll());
        return "course/index";
    }
}
