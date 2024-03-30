package com.dcm.hobby.controller;

import com.dcm.hobby.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HobbyController {

    private HobbyService service;

}
