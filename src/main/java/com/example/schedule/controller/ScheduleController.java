package com.example.schedule.controller;

import com.example.schedule.dto.request.ScheduleRequest;
import com.example.schedule.dto.response.ScheduleRes;
import com.example.schedule.global.response.LmsResponse;
import com.example.schedule.servcie.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

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
    public LmsResponse<ScheduleRes> getMajorId(@PathVariable("memberId") String memberId){
        ScheduleRes scheduleByMemberId = scheduleService.getScheduleByMemberId(memberId);
        return new LmsResponse<>(HttpStatus.OK, scheduleByMemberId, " 서비스 성공", "", LocalDateTime.now());
    }

    //스케줄 정보 변경
    @PostMapping("/info")
    public LmsResponse<ScheduleRes> updateProfessor(@RequestParam String memberId){
        ScheduleRes scheduleRes = scheduleService.updateSchedule(memberId);
        return new LmsResponse<>(HttpStatus.OK, scheduleRes, "서비스 성공", "", LocalDateTime.now());
    }


}
