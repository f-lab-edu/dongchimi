package dcm.hobby.service;

import dcm.hobby.domain.Hobby;
import dcm.hobby.domain.repository.HobbyRepository;
import dcm.hobby.dto.HobbyRequest;
import dcm.hobby.dto.HobbyResponse;
import dcm.hobby.dto.HobbyUpdateRequest;
import dcm.hobby.exception.NotFoundHobbyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    public List<HobbyResponse> readHobbyList() {
        List<Hobby> hobbyList = hobbyRepository.findAll();
        return HobbyResponse.of(hobbyList);
    }

    @Transactional
    public void writeHobby(HobbyRequest request) {
        Hobby hobby = Hobby.of(request.hobbyName(), request.useYn());
        hobbyRepository.save(hobby);
    }

    @Transactional
    public void updateHobby(HobbyUpdateRequest request) {
        validate(request.hobbyId());
        Hobby hobby = Hobby.of(request.hobbyId(), request.hobbyName(), request.useYn());
        hobbyRepository.save(hobby);
    }

    @Transactional
    public void deleteHobby(Long hobbyId) {
        validate(hobbyId);
        hobbyRepository.deleteById(hobbyId);
    }

    public void validate(Long hobbyId) {
        if (!hobbyRepository.existsById(hobbyId))
            throw new NotFoundHobbyException(hobbyId);
    }
}
