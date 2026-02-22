package com.example.demo.repo;

import com.example.demo.model.Sighting;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class SightingSpecifications {

    public static Specification<Sighting> dateFrom(LocalDateTime start) {
        return (root, query, cb) -> start == null ? cb.conjunction()
                : cb.greaterThanOrEqualTo(root.get("date"), start);
    }

    public static Specification<Sighting> dateTo(LocalDateTime end) {
        return (root, query, cb) -> end == null ? cb.conjunction()
                : cb.lessThanOrEqualTo(root.get("date"), end);
    }

    public static Specification<Sighting> locationContains(String location) {
        return (root, query, cb) -> (location == null || location.isBlank()) ? cb.conjunction()
                : cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%");
    }
}