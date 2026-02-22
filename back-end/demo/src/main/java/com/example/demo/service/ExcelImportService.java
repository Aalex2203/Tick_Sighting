package com.example.demo.service;

import com.example.demo.model.Sighting;
import com.example.demo.repo.SightingRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;

@Service
public class ExcelImportService {

    private final SightingRepository repo;

    public ExcelImportService(SightingRepository repo) {
        this.repo = repo;
    }

    public void importExcel() throws Exception {
        ClassPathResource resource = new ClassPathResource("Tick Sightings.xlsx");

        try (InputStream is = resource.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            boolean headerSkipped = false;
            int inserted = 0;
            int skipped = 0;

            for (Row row : sheet) {
                //to skip header row
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                try {
                    String id = trimOrNull(getString(row.getCell(0)));
                    if (id == null) { skipped++; continue; }

                    LocalDateTime date = parseIsoDateTime(getString(row.getCell(1)));
                    String location = trimOrNull(getString(row.getCell(2)));
                    String species = trimOrNull(getString(row.getCell(3)));
                    String latinName = trimOrNull(getString(row.getCell(4)));

                    repo.save(new Sighting(id, date, location, species, latinName));
                    inserted++;

                } catch (Exception e) {
                    skipped++;
                }
            }

            System.out.println("Import finished. Inserted=" + inserted + " Skipped=" + skipped);
        }
    }

    private String trimOrNull(String s) {
        if (s == null) return null;
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

    private String getString(Cell cell) {
        if (cell == null) return null;

        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
        if (cell.getCellType() == CellType.NUMERIC) return String.valueOf(cell.getNumericCellValue());
        if (cell.getCellType() == CellType.BOOLEAN) return String.valueOf(cell.getBooleanCellValue());

        if (cell.getCellType() == CellType.FORMULA) {
            try {
                return cell.getStringCellValue();
            } catch (Exception ignored) {
                try {
                    return String.valueOf(cell.getNumericCellValue());
                } catch (Exception ignored2) {
                    return null;
                }
            }
        }

        return null;
    }

    private LocalDateTime parseIsoDateTime(String s) {
        s = trimOrNull(s);
        if (s == null) return null;

        return LocalDateTime.parse(s);
    }
}