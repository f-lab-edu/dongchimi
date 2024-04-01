package com.dcm.hobby_detail.controller;

import com.dcm.hobby_detail.dto.HobbyDetailRequest;
import com.dcm.hobby_detail.dto.HobbyDetailResponse;
import com.dcm.hobby_detail.dto.HobbyDetailUpdateRequest;
import com.dcm.hobby_detail.service.HobbyDetailService;
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
    public ResponseEntity<Void> updateHobbyDetail(@RequestBody HobbyDetailUpdateRequest request) {
        hobbyDetailService.updateHobbyDetail(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{hobbyDetailId}")
    public ResponseEntity<Void> deleteHobbyDetail(@PathVariable Long hobbyDetailId) {
        hobbyDetailService.deleteHobbyDetail(hobbyDetailId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
