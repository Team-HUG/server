package com.hub.demo.global.util;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimeAgoFormatter {
    public String format(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(localDateTime, now);

        if (duration.getSeconds() < 60) {
            return duration.getSeconds() + "초 전";
        } else if (duration.toMinutes() < 60) {
            return duration.toMinutes() + "분 전";
        } else if (duration.toHours() < 24) {
            return duration.toHours() + "시간 전";
        } else if (duration.toDays() < 30) {
            return duration.toDays() + "일 전";
        } else if (duration.toDays() < 365) {
            long months = duration.toDays() / 30;
            return months + "개월 전";
        } else {
            long years = duration.toDays() / 365;
            return years + "년 전";
        }
    }

}
