package com.example.schedule.dto.response;

import com.example.schedule.entity.Lecture;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.Semester;
import com.example.schedule.entity.WeekDay;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ScheduleRes {

    private UUID id;
    private WeekDay weekday;
    private List<Lecture> lectures;
    private String memberId;
    private Semester semester;
    private int year;

    public ScheduleRes(Schedule schedule) {
        this.id = schedule.getId();
        this.weekday = schedule.getWeekday();
        this.lectures = schedule.getLectures();
        this.memberId = schedule.getMemberId();
        this.semester = schedule.getSemester();
        this.year = schedule.getYear();
    }
}
