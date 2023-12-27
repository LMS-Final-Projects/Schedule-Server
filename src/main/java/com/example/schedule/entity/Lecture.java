package com.example.schedule.entity;

import com.example.schedule.global.kafka.KafkaAction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {

    @Id
    private Long lectureId;
    private String memberId;
    private String lectureName;
    private String professorName;
    private Integer score;
    private Integer startTime;
    @OneToOne
    private WeekDay weekday;
    private KafkaAction kafkaAction;
}
