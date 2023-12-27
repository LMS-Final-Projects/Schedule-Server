package com.example.schedule.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeekDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberId;

    @ElementCollection
    @CollectionTable(name = "times", joinColumns = @JoinColumn(name = "weekday_id"))
    @Column(name = "time")
    private List<Integer> times;

    @ElementCollection
    @CollectionTable(name = "lecture_info", joinColumns = @JoinColumn(name = "weekday_id"))
    @Column(name = "lecture_info")
    private List<String> lecturesByDay;

}

