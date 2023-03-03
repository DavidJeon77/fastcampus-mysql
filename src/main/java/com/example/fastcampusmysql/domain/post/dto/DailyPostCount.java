package com.example.fastcampusmysql.domain.post.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record DailyPostCount(
        Long memberId,
//        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date,
        Long postCount
) {
}
