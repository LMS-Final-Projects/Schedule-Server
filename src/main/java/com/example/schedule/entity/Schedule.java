package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    private Long scheduleId;

    private String memberId;

    @OneToMany(mappedBy = "schedule")
    private List<WeekDay> weekday;

    private Semester semester;

    private int year;

}
