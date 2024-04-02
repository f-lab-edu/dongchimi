package com.dcm.hobby_detail.service;

import com.dcm.hobby_detail.domain.repository.HobbyDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyDetailService {

    private final HobbyDetailRepository repository;

}
