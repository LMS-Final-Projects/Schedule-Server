package com.example.schedule.dto.request;


import com.example.schedule.entity.Lecture;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.Semester;
import com.example.schedule.entity.WeekDay;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequest {
    private String memberId;
    private Semester semester;
    private Integer year;

    public Schedule toEntity(){
        return Schedule.builder()
                .memberId(memberId)
                .build();
    }
}
