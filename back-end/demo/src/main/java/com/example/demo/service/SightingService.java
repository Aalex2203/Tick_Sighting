package com.example.demo.service;

import com.example.demo.dto.SightingDto;
import com.example.demo.model.Sighting;
import com.example.demo.repo.SightingRepository;
import com.example.demo.repo.SightingSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SightingService {

    private final SightingRepository repo;

    public SightingService(SightingRepository repo) {
        this.repo = repo;
    }

    public Page<SightingDto> search(LocalDateTime start, LocalDateTime end, String location, int page, int size) {

        if (start != null && end != null && start.isAfter(end)) {
            throw new IllegalArgumentException("start must be <= end");
        }

        Specification<Sighting> spec = Specification
                .where(SightingSpecifications.dateFrom(start))
                .and(SightingSpecifications.dateTo(end))
                .and(SightingSpecifications.locationContains(location));

        PageRequest pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "date")
        );

        return repo.findAll(spec, pageable).map(this::toDto);
    }

    private SightingDto toDto(Sighting s) {
        return new SightingDto(
                s.getId(),
                s.getDate(),
                s.getLocation(),
                s.getSpecies(),
                s.getLatinName()
        );
    }
}