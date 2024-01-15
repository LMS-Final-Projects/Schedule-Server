package com.example.schedule.dto.response;

import com.example.schedule.entity.Lecture;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.Semester;
import com.example.schedule.entity.WeekDay;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class LectureRes {

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
    @Column
    private DayOfWeek dayOfWeek;
    @Column
    private Integer maximumNumber;

    public LectureRes(Lecture lecture) {
        this.memberId = lecture.getMemberId();
        this.lectureId = lecture.getLectureId();
        this.lectureName = lecture.getLectureName();
        this.professorName = lecture.getProfessorName();
        this.score = lecture.getScore();
        this.startTime = lecture.getStartTime();
        this.classTimes = lecture.getClassTimes();
        this.dayOfWeek = lecture.getDayOfWeek();
        this.maximumNumber = lecture.getMaximumNumber();
    }
}
