package com.example.schedule.dto.response;

import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.Semester;
import com.example.schedule.entity.WeekDay;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class WeekdayRes {

    private Integer id;

    private String dayOfWeek;
    private Integer lectureId;


    public WeekdayRes(WeekDay weekDay) {
        this.id = weekDay.getId();
        this.dayOfWeek = weekDay.getDayOfWeek();
        this.lectureId = weekDay.getLectureId();
    }
}
