package com.example.importfileexcel.repositories;

import com.example.importfileexcel.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("select student from Student student where student.fullName like %:fullName%")
    List<Student> findByFullName(@Param("fullName") String fullName);

    @Query("select student from Student student where student.studentCode like %:studentCode%")
    List<Student> findByStudentCode(@Param("studentCode") String studentCode);


}
