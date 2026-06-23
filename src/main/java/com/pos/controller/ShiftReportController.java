package com.pos.controller;

import com.pos.payload.dto.shiftReportDto;
import com.pos.service.ShiftReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shiftReports")
public class ShiftReportController {



    private ShiftReportService service;
    public ShiftReportController(ShiftReportService service) {
        this.service = service;
    }


    @PostMapping("/start")
    public ResponseEntity<shiftReportDto> startShift() throws Exception {
        return ResponseEntity.ok(service.startShift());
    }

    @PatchMapping("/end")
    public ResponseEntity<shiftReportDto> endShift() throws Exception {
        return ResponseEntity.ok(service.endShift(null, null));
    }

    @GetMapping("/current")
    public ResponseEntity<shiftReportDto> getCurrentShiftProgress() throws Exception {
        return  ResponseEntity.ok(
                service.getCurrentShiftReportProgress(null)
        );
    }

    @GetMapping("/cashier/{cashierId}/by-date")
    public ResponseEntity<shiftReportDto> getShiftReportByDate(
            @PathVariable Long cashierId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDateTime date) throws Exception {



        return ResponseEntity.ok(service.getShiftReportByCashierAndDate(cashierId,date));
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<shiftReportDto>> getShiftReportByCashier(
            @PathVariable Long cashierId
     ) throws Exception
    {

        return ResponseEntity.ok(service.getShiftReportByCashierId(cashierId));

    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<shiftReportDto>> getShiftReportByBranch(
            @PathVariable Long branchId
    ) throws Exception
    {

        return ResponseEntity.ok(service.getShiftReportsByBranchId(branchId));

    }


    @GetMapping("/{id}")
    public ResponseEntity<shiftReportDto> getShiftReportById(
            @PathVariable Long id
    ) throws Exception
    {

        return ResponseEntity.ok(service.getShiftReportId(id));

    }




}
