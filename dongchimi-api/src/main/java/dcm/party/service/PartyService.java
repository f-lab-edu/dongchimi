package dcm.party.service;

import dcm.chat.domain.Chat;
import dcm.chat.domain.repository.ChatRepository;
import dcm.party.domain.Party;
import dcm.party.domain.repository.PartyRepository;
import dcm.party.dto.PartyRequest;
import dcm.hobby.domain.Hobby;
import dcm.hobby.domain.repository.HobbyRepository;
import dcm.hobby.exception.NotFoundHobbyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartyService {

    private final PartyRepository partyRepository;
    private final HobbyRepository hobbyRepository;
    private final ChatRepository chatRepository;

    @Transactional
    public void writeParty(PartyRequest request) {
        Hobby hobby = readHobby(request.hobbyId());
        Party party = Party.toEntity(request, hobby);

        partyRepository.save(party);
        writeChat(party);
    }

    private void writeChat(Party party) {
        Chat chat = new Chat(party);
        chatRepository.save(chat);
    }

    private Hobby readHobby(Long hobbyId) {
        Optional<Hobby> hobby = hobbyRepository.findById(hobbyId);
        if (hobby.isPresent())
            return hobby.get();
        throw new NotFoundHobbyException(hobbyId);
    }

}
