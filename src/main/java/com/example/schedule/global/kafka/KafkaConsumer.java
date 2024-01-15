package com.example.schedule.global.kafka;


import com.example.schedule.dto.request.ScheduleRequest;
import com.example.schedule.entity.Lecture;
import com.example.schedule.entity.Semester;
import com.example.schedule.entity.WeekDay;
import com.example.schedule.repository.LecturerRepository;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.WeekdayRepository;
import com.example.schedule.servcie.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final LecturerRepository lectureRepository;
    private final WeekdayRepository weekdayRepository;
    private final ScheduleService scheduleService;


    @KafkaListener(topics = "weekday", groupId = "weekday_1")
    public void weekdayListener(String kafkaMessage) {
        Map<Object, Object> map;
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {});
            String action = (String) map.get("kafkaAction");
            String dayOfWeek = (String) map.get("dayOfWeek");
            if (action.equals(KafkaAction.CREATE.name())) {
                List<Lecture> memberList = lectureRepository.findLecturesById((String) map.get("memberId"));
                WeekDay build = WeekDay.builder()
                        .id((Integer) map.get("id"))
                        .memberId((String) map.get("memberId"))
                        .dayOfWeek(dayOfWeek)
                        .lectureId((Integer) map.get("lectureId"))
                        .build();

                weekdayRepository.save(build);

                System.out.println(map);
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    @KafkaListener(topics = "lecture", groupId = "lecture_1")
    public void lectureListener(String kafkaMessage) {
        Map<Object, Object> map;
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {});
            String action = (String) map.get("kafkaAction");
            if (action.equals(KafkaAction.CREATE.name())) {
                Object dayOfWeek = map.get("dayOfWeek");
                DayOfWeek dayOfWeekValue = null;

                if (dayOfWeek instanceof String) {
                    String dayOfWeekString = (String) dayOfWeek;
                    dayOfWeekValue = DayOfWeek.valueOf(dayOfWeekString);
                }

                Object semester = map.get("semester");
                Semester semesterValue = null;
                if (semester instanceof String) {
                    String semesterString = (String) semester;
                    semesterValue = Semester.valueOf(semesterString);
                }

                Lecture build = Lecture.builder()
                        .memberId((String) map.get("memberId"))
                        .lectureId((Integer) map.get("lectureId"))
                        .lectureName((String) map.get("lectureName"))
                        .professorName((String) map.get("professorName"))
                        .score((Integer) map.get("score"))
                        .startTime((Integer)map.get("startTime"))
                        .classTimes((List<Integer>) map.get("classTimes"))
                        .maximumNumber((Integer)map.get("maximumNumber"))
                        .year(((Integer) map.get("year")))
                        .semester(semesterValue)
                        .dayOfWeek(dayOfWeekValue)
                        .build();
                lectureRepository.save(build);
                ScheduleRequest request = ScheduleRequest.builder()
                        .memberId(build.getMemberId())
                        .LectureId(build.getLectureId())
                        .semester(build.getSemester())
                        .year(build.getYear())
                        .build();

                scheduleService.saveSchedule(request);

                System.out.println(map);
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

}

