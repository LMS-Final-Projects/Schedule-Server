package com.example.schedule.servcie;


import com.example.schedule.dto.request.ScheduleRequest;
import com.example.schedule.dto.response.ScheduleRes;
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
        WeekDay weekDay = weekdayRepository.findByMemberId(request.getMemberId()).orElseThrow(() -> new NotFoundException("유저 스케줄 정보가 없습니다."));
        try {
            repository.save(
                    Schedule.builder()
                    .memberId(request.getMemberId())
                    .lectures(lecturesById)
                    .weekday(weekDay)
                    .semester(request.getSemester())
                    .year(request.getYear())
                    .build()
            );
        } catch (Exception e) {
            throw new MethodException("");
        }
    }


    //스케줄 정보 가져 오기
    @Transactional
    public ScheduleRes getScheduleByMemberId(String memberId){
        Schedule schedule = repository.findScheduleByMemberId(memberId).orElseThrow(() -> new NotFoundException("스케줄이 없습니다."));
        ScheduleRes dto = new ScheduleRes(schedule);
        return dto;
    };


    //스케줄 정보 변경.
    public ScheduleRes updateSchedule(String memberId) {
        Schedule schedule = repository.findScheduleByMemberId(memberId).orElseThrow(() -> new NotFoundException("스케줄이 없습니다."));
        Schedule save = repository.save(schedule);
        ScheduleRes dto = new ScheduleRes(save);
        return dto;
    }


}
