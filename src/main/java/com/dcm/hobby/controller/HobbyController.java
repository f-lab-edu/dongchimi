package com.dcm.hobby.controller;

import com.dcm.hobby.dto.HobbyRequest;
import com.dcm.hobby.dto.HobbyResponse;
import com.dcm.hobby.dto.HobbyUpdateRequest;
import com.dcm.hobby.service.HobbyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby")
public class HobbyController {

    private final HobbyService hobbyService;

    @GetMapping
    public ResponseEntity<List<HobbyResponse>> readHobbyList() {
        List<HobbyResponse> hobbyList = hobbyService.readHobbyList();
        return ResponseEntity.ok(hobbyList);
    }

    @PostMapping
    public ResponseEntity<Void> writeHobby(@RequestBody @Valid HobbyRequest request) {
        hobbyService.writeHobby(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateHobby(@RequestBody HobbyUpdateRequest request) {
        hobbyService.updateHobby(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{hobbyId}")
    public ResponseEntity<Void> deleteHobby(@PathVariable Long hobbyId) {
        hobbyService.deleteHobby(hobbyId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
