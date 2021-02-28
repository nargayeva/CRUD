package ada.wm2.CRUD.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @Column(name = "ST_ID")
    Integer studentID;
    @Column(name = "ST_AGE")
    Integer age;
    @Column(name = "ST_FIRSTNAME")
    String firstName;
    @Column(name = "ST_LASTNAME")
    String lastName;
    @Column(name = "ST_EMAIL")
    String email;
    @Column(name = "ST_GPA")
    Double cgpa;
    @Column(name = "ST_DEGREE")
    String degree;
    @Column(name = "ST_MAJOR")
    String major;
    @Column(name = "ST_PERFORMANCE")
    String performance;

    @ManyToMany
    @JoinTable(name = "ENROLLMENTS",
            joinColumns = @JoinColumn(name = "ST_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    List<Course> courses;

    public Integer getStudentID() { return studentID; }

    public void setStudentID(Integer studentID) { this.studentID = studentID; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() { return age; }

    public void setAge(Integer age) { this.age = age; }

    public String getDegree() { return degree; }

    public void setDegree(String degree) { this.degree = degree; }

    public String getPerformance() { return performance; }

    public void setPerformance(String performance) { this.performance = performance; }

    public Double getCgpa() { return cgpa; }

    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public String getMajor() { return major; }

    public void setMajor(String major) { this.major = major; }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
