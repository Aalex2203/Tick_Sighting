package com.example.demo.controller;

import com.example.demo.dto.RegionCountDto;
import com.example.demo.dto.TrendPointDto;
import com.example.demo.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/regions")
    public List<RegionCountDto> regions() {
        return service.regions();
    }

    @GetMapping("/trends")
    public List<TrendPointDto> trends(@RequestParam(defaultValue = "month") String period) {
        return service.trends(period);
    }


}