package com.pos.controller;

import com.pos.exception.UserException;
import com.pos.payload.dto.RefundDto;
import com.pos.payload.response.ApiResponse;
import com.pos.repository.StoreRepository;
import com.pos.service.RefundService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {
    private RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping
    public ResponseEntity<RefundDto> createRefund(@RequestBody RefundDto refundDto) throws UserException {

        RefundDto refund =refundService.createRefund(refundDto);

        return ResponseEntity.ok(refund);


    }
    @GetMapping
    public ResponseEntity<List<RefundDto>> getAllRefunds() throws Exception {

        return ResponseEntity.ok(refundService.getAllRefund());
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<RefundDto>> getRefundByCashierID(@PathVariable Long cashierId) throws Exception {

        return ResponseEntity.ok(refundService.getRefundCashier(cashierId));
    }


    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<RefundDto>> getRefundByBranchId(@PathVariable Long branchId) throws Exception {

        return ResponseEntity.ok(refundService.getRefundByBranch(branchId));

    }



    @GetMapping("/shift/{shiftId}")
    public ResponseEntity<List<RefundDto>> getRefundByShiftId(@PathVariable Long shiftId) throws Exception {

        return ResponseEntity.ok(refundService.getRefundByShiftReport(shiftId));

    }


    @GetMapping("/cashier/{cashierId}/range")
    public ResponseEntity<List<RefundDto>> getRefundByCashierAndDateRange(@PathVariable Long cashierId,
                                                                          @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                          @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) throws Exception {

        return ResponseEntity.ok(refundService.getAllRefundByCashierAndDateRange(cashierId,startDate,endDate));

    }

    @GetMapping("/{id}")
    public ResponseEntity<RefundDto> getRefundById(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(refundService.getRefundById(id));

    }


    @DeleteMapping("/{refundId}")
    public ResponseEntity<ApiResponse> deleteRefund(@PathVariable Long refundId) throws Exception {

        refundService.deleteRefund(refundId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Refund deleted successfully");

        return ResponseEntity.ok(apiResponse);



    }


}
