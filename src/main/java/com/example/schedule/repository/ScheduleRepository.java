package com.example.schedule.repository;


import com.example.schedule.entity.Lecture;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduleRepository
        extends JpaRepository<Schedule,Long> {

    @Query("SELECT s from Schedule as s where s.memberId = :memberId")
    List<Schedule> findScheduleByMemberId(@Param("memberId")String memberId);

    @Query("DELETE Schedule as s  where s.memberId = :memberId")
    void deleteByMemberId(@Param("memberId")String memberId);
}
