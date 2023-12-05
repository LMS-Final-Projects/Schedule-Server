package com.example.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String memberName;
    private Role role;
    private UUID memberId;
    private UUID scheduleId;
}