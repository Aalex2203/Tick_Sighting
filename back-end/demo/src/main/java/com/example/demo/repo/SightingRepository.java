package com.example.demo.repo;

import com.example.demo.model.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, String>,
        JpaSpecificationExecutor<Sighting> {
                @Query("SELECT s.location, COUNT(s) FROM Sighting s GROUP BY s.location ORDER BY COUNT(s) DESC")
List<Object[]> countByLocation();
}