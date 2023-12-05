package com.example.schedule.repository;


import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository
        extends JpaRepository<Schedule,Long> {

    @Query("SELECT Lecture FROM Schedule s join WeekDay w on w.schedule.id = s.id where s.memberId = :memberId and s.year = :year and s.semester = :semester")
    Optional<Schedule> findLectureBySchedule(@Param("memberId")String memberId, @Param("year")int year, @Param("semester") Semester semester);

}
