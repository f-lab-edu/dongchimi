package com.dcm.hobbydetail.service;

import com.dcm.hobby.domain.Hobby;
import com.dcm.hobby.domain.repository.HobbyRepository;
import com.dcm.hobby.exception.NotFoundHobbyException;
import com.dcm.hobbydetail.domain.HobbyDetail;
import com.dcm.hobbydetail.domain.repository.HobbyDetailRepository;
import com.dcm.hobbydetail.dto.HobbyDetailRequest;
import com.dcm.hobbydetail.dto.HobbyDetailResponse;
import com.dcm.hobbydetail.dto.HobbyDetailUpdateRequest;
import com.dcm.hobbydetail.exception.NotFoundHobbyDetailException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HobbyDetailService {

    private final HobbyDetailRepository hobbyDetailRepository;
    private final HobbyRepository hobbyRepository;

    public List<HobbyDetailResponse> readHobbyDetailList() {
        List<HobbyDetail> hobbyDetailList = hobbyDetailRepository.findAll();
        return HobbyDetailResponse.of(hobbyDetailList);

    }

    @Transactional
    public void writeHobbyDetail(HobbyDetailRequest request) {
        Hobby hobby = readHobby(request.hobbyId());
        HobbyDetail hobbyDetail = HobbyDetail.of(request.hobbyDetailName(), request.useYn(), hobby);
        hobbyDetailRepository.save(hobbyDetail);
    }

    @Transactional
    public void updateHobbyDetail(HobbyDetailUpdateRequest request) {
        validate(request.hobbyDetailId());
        Hobby hobby = readHobby(request.hobbyId());
        HobbyDetail hobbyDetail = HobbyDetail.of(request.hobbyDetailId(), request.hobbyDetailName(), request.useYn(), hobby);
        hobbyDetailRepository.save(hobbyDetail);
    }

    @Transactional
    public void deleteHobbyDetail(Long hobbyDetailId) {
        validate(hobbyDetailId);
        hobbyDetailRepository.deleteById(hobbyDetailId);
    }

    private Hobby readHobby(Long hobbyId) {
        return hobbyRepository.findById(hobbyId).orElseThrow(() -> new NotFoundHobbyException(hobbyId));
    }

    private void validate(Long hobbyDetailId) {
        if (hobbyDetailRepository.existsById(hobbyDetailId))
            throw new NotFoundHobbyDetailException(hobbyDetailId);
    }

}

