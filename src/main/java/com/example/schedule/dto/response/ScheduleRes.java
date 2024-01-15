package com.example.schedule.dto.response;

import com.example.schedule.entity.Lecture;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.Semester;
import com.example.schedule.entity.WeekDay;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ScheduleRes {

    private List<WeekDay> weekday;
    private String memberId;
    private Long scheduleId;
    private Semester semester;
    private int year;


}
