package com.example.schedule.global.kafka;


import com.example.schedule.entity.Lecture;
import com.example.schedule.entity.WeekDay;
import com.example.schedule.repository.LecturerRepository;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.WeekdayRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final LecturerRepository lecturerRepository;
    private final WeekdayRepository weekdayRepository;

    @KafkaListener(topics = "lecture", groupId = "lecture_1")
    public void lectureListener(String kafkaMessage) {
        Map<Object, Object> map;
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {});
            String action = (String) map.get("kafkaAction");
            if (action.equals(KafkaAction.CREATE.name())) {
                Lecture build = Lecture.builder()
                        .memberId((String) map.get("memberId"))
                        .lectureId((Long) map.get("lectureId"))
                        .lectureName((String) map.get("lectureName"))
                        .professorName((String) map.get("professorName"))
                        .score((Integer) map.get("score"))
                        .startTime((Integer)map.get("startTime"))
                        .weekday((WeekDay) map.get("weekday"))
                        .build();
                lecturerRepository.save(build);
                weekdayRepository.save((WeekDay) map.get("weekday"));
                System.out.println(map);
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }



}

