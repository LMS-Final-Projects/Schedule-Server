package com.example.schedule.repository;


import com.example.schedule.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecture, String> {

    @Query("select l from Lecture as l where l.memberId = :id")
    List<Lecture> findLecturesById(@Param("id") String id);


}
