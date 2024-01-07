package com.example.schedule.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeekDay {

    @Id
    private Integer id;

    private String memberId;

    private String dayOfWeek;
    private Integer lectureId;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }
}

