package com.example.schedule.controller;

import com.example.schedule.dto.request.ScheduleRequest;
import com.example.schedule.dto.response.LectureRes;
import com.example.schedule.dto.response.ScheduleRes;
import com.example.schedule.dto.response.WeekdayRes;
import com.example.schedule.global.response.LmsResponse;
import com.example.schedule.servcie.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    //스케줄 정보 저장.
    @PostMapping
    public LmsResponse<Void> saveSchedule(@RequestBody ScheduleRequest request){
        scheduleService.saveSchedule(request);
        return new LmsResponse<>(HttpStatus.CREATED, null, "서비스 성공", "", LocalDateTime.now());
    }
    
    
    //유저 스케줄 정보 가져 오기
    @GetMapping("/{memberId}")
    public LmsResponse<List<ScheduleRes>> getMemberId(@PathVariable("memberId") String memberId){
        List<ScheduleRes> scheduleByMemberId = scheduleService.getScheduleByMemberId(memberId);
        return new LmsResponse<>(HttpStatus.OK, scheduleByMemberId, " 서비스 성공", "", LocalDateTime.now());
    }


    //weekday정보 가져오기
    @GetMapping("/weekday/{weekdayId}")
    public LmsResponse<WeekdayRes> getWeekDay(@PathVariable("weekdayId") Integer weekdayId){
        WeekdayRes weekday = scheduleService.getWeekday(weekdayId);
        return new LmsResponse<>(HttpStatus.OK, weekday , "서비스 성공", "", LocalDateTime.now());
    }

    //lectureId로 정보 가져오기
    @GetMapping("/lecture/{lectureId}")
    public LmsResponse<LectureRes> getLecture(@PathVariable("lectureId") Integer lectureId){
        LectureRes lectureRes = scheduleService.getLecture(lectureId);
        return new LmsResponse<>(HttpStatus.OK, lectureRes , "서비스 성공", "", LocalDateTime.now());
    }


}
