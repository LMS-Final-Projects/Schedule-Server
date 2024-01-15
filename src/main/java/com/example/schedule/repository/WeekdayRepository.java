package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WeekdayRepository extends JpaRepository<WeekDay, Integer> {


    @Query("SELECT w from WeekDay as w where w.memberId = :memberId")
    List<WeekDay> findByMemberId(@Param("memberId")String memberId);
}
