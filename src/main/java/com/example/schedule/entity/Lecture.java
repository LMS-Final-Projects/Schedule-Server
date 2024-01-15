package com.example.schedule.entity;

import com.example.schedule.global.kafka.KafkaAction;
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
public class Lecture {

    @Id
    private String memberId;
    @Column(nullable = false)
    private Integer lectureId;
    @Column(nullable = false)
    private String lectureName;
    @Column(nullable = false)
    private String professorName;
    @Column(nullable = false)
    private Integer score;
    @Column(nullable = false)
    private Integer startTime;
    @ElementCollection
    private List<Integer> classTimes; // 해당 교시
    private DayOfWeek dayOfWeek;
    private Integer maximumNumber;
    private Integer year;
    @Enumerated(EnumType.STRING)
    private Semester semester;


}
