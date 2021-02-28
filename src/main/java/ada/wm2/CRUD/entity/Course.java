package ada.wm2.CRUD.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @Column(name = "COURSE_ID")
    Integer courseNumber;
    @Column(name = "COURSE_NAME")
    String courseName;
    @Column(name = "COURSE_CREDIT")
    Integer courseCredit;
    @Column(name = "COURSE_GRADE")
    Double passingGrade;
    @Column(name = "COURSE_TYPE")
    String courseType;

    @ManyToMany
    List<Student> students;

    public Integer getCourseNumber() { return courseNumber; }

    public void setCourseNumber(Integer courseNumber) { this.courseNumber = courseNumber; }

    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Integer getCourseCredit() { return courseCredit; }

    public void setCourseCredit(Integer courseCredit) { this.courseCredit = courseCredit; }

    public Double getPassingGrade() { return passingGrade; }

    public void setPassingGrade(Double passingGrade) { this.passingGrade = passingGrade; }

    public String getCourseType() { return courseType; }

    public void setCourseType(String courseType) { this.courseType = courseType; }
}
