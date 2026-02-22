package com.example.demo.service;

import com.example.demo.dto.RegionCountDto;
import com.example.demo.dto.TrendPointDto;
import com.example.demo.repo.SightingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final SightingRepository repo;

    public ReportService(SightingRepository repo) {
        this.repo = repo;
    }

    public List<RegionCountDto> regions() {
        return repo.countByLocation().stream()
                .map(r -> new RegionCountDto(
                        (String) r[0],
                        (Long) r[1]
                ))
                .toList();
    }

    public List<TrendPointDto> trends(String period) {
        if (period == null) period = "month";
        period = period.toLowerCase();

        List<LocalDateTime> dates = repo.findAll().stream()
                .map(s -> s.getDate())
                .filter(Objects::nonNull)
                .toList();

        Map<String, Long> grouped;

        if (period.equals("week")) {
            WeekFields wf = WeekFields.ISO;
            grouped = dates.stream().collect(Collectors.groupingBy(d -> {
                int week = d.get(wf.weekOfWeekBasedYear());
                int year = d.get(wf.weekBasedYear());
                return year + "-W" + String.format("%02d", week);
            }, Collectors.counting()));
        } else if (period.equals("month")) {
            grouped = dates.stream().collect(Collectors.groupingBy(
                    d -> YearMonth.from(d).toString(),
                    Collectors.counting()
            ));
        } else {
            throw new IllegalArgumentException("period must be 'month' or 'week'");
        }

        return grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new TrendPointDto(e.getKey(), e.getValue()))
                .toList();
    }
}