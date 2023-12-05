package com.example.schedule.servcie;


import com.example.global.exception.MethodException;

import com.example.global.exception.NotFoundException;
import com.example.schedule.dto.request.ScheduleRequest;
import com.example.schedule.dto.response.ScheduleRes;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;

    //스케줄 정보 저장.
    @Transactional
    public void saveSchedule(ScheduleRequest request) {

        try {
            repository.save(request.toEntity());
        } catch (Exception e) {
            throw new MethodException("");
        }
    }


    //스케줄 정보 가져 오기
    @Transactional
    public ScheduleRes getScheduleByMemberId(UUID memberId){
        Schedule schedule = repository.findScheduleByMemberId(memberId).orElseThrow(() -> new NotFoundException("스케줄이 없습니다."));
        ScheduleRes dto = new ScheduleRes(schedule);
        return dto;
    };


    //스케줄 정보 변경.
    public ScheduleRes updateSchedule(UUID memberId) {
        Schedule schedule = repository.findScheduleByMemberId(memberId).orElseThrow(() -> new NotFoundException("스케줄이 없습니다."));
        Schedule save = repository.save(schedule);
        ScheduleRes dto = new ScheduleRes(save);
        return dto;
    }

    //스케줄 삭제
    public void deleteSchedule(UUID memberId) {
        Schedule schedule = repository.findScheduleByMemberId(memberId).orElseThrow(() -> new NotFoundException("스케줄이 없습니다."));
        repository.deleteByMemberId(schedule.getMemberId());
    }

}
