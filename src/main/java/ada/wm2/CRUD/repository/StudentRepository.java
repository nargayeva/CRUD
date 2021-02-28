package ada.wm2.CRUD.repository;

import ada.wm2.CRUD.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query("Select s from Student s where s.firstName like %:first%")
    List<Student> findByStudentFirstName(String first);

    @Query("Select s from Student s where s.lastName like %:last%")
    List<Student> findByStudentLastName(String last);

    @Query("Select s from Student s where s.email like %:email%")
    List<Student> findByStudentEmail(String email);

    @Query(value = "SELECT * FROM STUDENTS WHERE Upper(ST_PERFORMANCE) like Upper (:performance)", nativeQuery = true)
    List<Student> getExcellentPerformance(String performance);

    @Query(value = "SELECT * FROM STUDENTS WHERE Upper(ST_DEGREE) like Upper(:degree)", nativeQuery = true)
    List<Student> getDegrees(String degree);
}



