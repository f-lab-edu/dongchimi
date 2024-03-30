package com.dcm.hobby.service;

import com.dcm.hobby.domain.repository.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyService {

    private HobbyRepository repository;
}
