package com.dcm.party.controller;

import com.dcm.party.dto.PartyRequest;
import com.dcm.party.service.PartyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/party")
public class PartyController {

    private final PartyService partyService;

    @PostMapping
    public ResponseEntity<Void> createParty(@RequestBody @Valid PartyRequest request) {
        partyService.createParty(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
