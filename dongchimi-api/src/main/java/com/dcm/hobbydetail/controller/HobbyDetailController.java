package com.dcm.hobbydetail.controller;

import com.dcm.hobbydetail.dto.HobbyDetailRequest;
import com.dcm.hobbydetail.dto.HobbyDetailResponse;
import com.dcm.hobbydetail.dto.HobbyDetailUpdateRequest;
import com.dcm.hobbydetail.service.HobbyDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hobby-detail")
public class HobbyDetailController {

    private final HobbyDetailService hobbyDetailService;

    @GetMapping
    public ResponseEntity<List<HobbyDetailResponse>> readHobbyDetailList() {
        List<HobbyDetailResponse> hobbyDetailList = hobbyDetailService.readHobbyDetailList();
        return ResponseEntity.ok(hobbyDetailList);
    }

    @PostMapping
    public ResponseEntity<Void> writeHobbyDetail(@RequestBody @Valid HobbyDetailRequest request) {
        hobbyDetailService.writeHobbyDetail(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public void updateHobbyDetail(@RequestBody HobbyDetailUpdateRequest request) {
        hobbyDetailService.updateHobbyDetail(request);
    }

    @DeleteMapping("/{hobbyDetailId}")
    public void deleteHobbyDetail(@PathVariable Long hobbyDetailId) {
        hobbyDetailService.deleteHobbyDetail(hobbyDetailId);
    }


}
