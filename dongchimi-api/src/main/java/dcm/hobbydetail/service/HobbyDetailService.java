package dcm.hobbydetail.service;

import dcm.hobby.domain.Hobby;
import dcm.hobby.domain.repository.HobbyRepository;
import dcm.hobby.exception.NotFoundHobbyException;
import dcm.hobbydetail.domain.HobbyDetail;
import dcm.hobbydetail.domain.repository.HobbyDetailRepository;
import dcm.hobbydetail.dto.HobbyDetailRequest;
import dcm.hobbydetail.dto.HobbyDetailResponse;
import dcm.hobbydetail.dto.HobbyDetailUpdateRequest;
import dcm.hobbydetail.exception.NotFoundHobbyDetailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

