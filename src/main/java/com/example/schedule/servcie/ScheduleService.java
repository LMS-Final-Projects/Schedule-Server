package com.example.schedule.servcie;


import com.example.schedule.dto.request.ScheduleRequest;
import com.example.schedule.dto.response.LectureRes;
import com.example.schedule.dto.response.ScheduleRes;
import com.example.schedule.dto.response.WeekdayRes;
import com.example.schedule.entity.Lecture;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.WeekDay;
import com.example.schedule.global.exception.MethodException;
import com.example.schedule.global.exception.NotFoundException;
import com.example.schedule.repository.LecturerRepository;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.WeekdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;
    private final LecturerRepository lecturerRepository;
    private final WeekdayRepository weekdayRepository;

    //스케줄 정보 저장.
    @Transactional
    public void saveSchedule(ScheduleRequest request) {
        List<Lecture> lecturesById = lecturerRepository.findLecturesById(request.getMemberId());
        if(lecturesById.isEmpty()){
            throw new NotFoundException("과목 정보가 없습니다.");
        }
        System.out.println("과목 찾기 성공");
        List<WeekDay> byMemberId = weekdayRepository.findByMemberId(request.getMemberId());
        if(byMemberId.isEmpty()){
            throw new NotFoundException("요일 정보가 없습니다.");
        }
        System.out.println("요일 정보 찾기 성공");
        try {
            repository.save(
                    Schedule.builder()
                    .memberId(request.getMemberId())
                    .weekday(byMemberId)
                    .semester(request.getSemester())
                    .year(request.getYear())
                    .build()
            );

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            throw new MethodException("문제 발생");
            
        }
    }


    // 스케줄 정보 가져 오기
    @Transactional(readOnly = true)
    public List<ScheduleRes> getScheduleByMemberId(String memberId) {
        List<Schedule> scheduleByMemberId = repository.findScheduleByMemberId(memberId);
        List<ScheduleRes> scheduleList = new ArrayList<>();

        for (Schedule schedule : scheduleByMemberId) {
            ScheduleRes scheduleRes = ScheduleRes.builder()
                    .memberId(schedule.getMemberId())
                    .weekday(schedule.getWeekday())
                    .semester(schedule.getSemester())
                    .year(schedule.getYear())
                    .build();
            scheduleList.add(scheduleRes);
        }

        return scheduleList;
    }

    
    //Weekday정보 Weekdayid로 가져오기
    @Transactional
    public WeekdayRes getWeekday(Integer weekdayId) {
        WeekDay weekDay = weekdayRepository.findById(weekdayId).orElseThrow(() -> new NotFoundException("요일 정보가 없습니다."));
        WeekdayRes dto = new WeekdayRes(weekDay);
        return dto;
    }

    @Transactional
    public LectureRes getLecture(Integer weekdayId) {
        Lecture lecture = lecturerRepository.findById(weekdayId).orElseThrow(() -> new NotFoundException("과목 정보가 없습니다."));
        LectureRes dto = new LectureRes(lecture);
        return dto;
    }


}
