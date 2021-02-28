package ada.wm2.CRUD.repository;

import ada.wm2.CRUD.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CourseRepository  extends CrudRepository<Course, Integer> {
    Course findByCourseNumber(Integer courseNumber);

    @Query("Select s from Course s where s.courseName like %:name%")
    List<Course> findByCourseName(String name);

    @Query(value = "SELECT * FROM COURSES WHERE Upper(COURSE_TYPE) like Upper(:courseType)", nativeQuery = true)
    List<Course> getCourseTypes(String courseType);

}
