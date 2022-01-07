package com.example.springdemo.core.repository;


import com.example.springdemo.core.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findByFirstNameOrLastName(String firstName, String lastName);

    List<Student> findByFirstNameIn(List<String> firstName);

    List<Student> findByFirstNameContains(String firstName);

    List<Student> findByFirstNameStartsWith(String firstName);

    @Query("FROM Student WHERE firstName = :firstName AND lastName = :lastName")
    Student getByLastNameAndFirstName(@Param("firstName") String primeiroNome, String lastName);

    @Modifying
    @Transactional
    @Query("Update Student set firstName = :firstName where id = :id")
    Integer updateFirstName(Long id, String firstName);

    List<Student> findByAddressCity (String city);

    //mesma coisa que o findByAddressCity mas dessa vez usando JPQL
    @Query("From Student where address.city = :city")
    List<Student> getByAddressCity (String city);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student WHERE firstName = :firstName")
    Integer deleteByFirstName(String firstName);
}
