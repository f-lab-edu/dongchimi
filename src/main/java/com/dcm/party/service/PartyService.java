package com.dcm.party.service;

import com.dcm.chat.domain.Chat;
import com.dcm.chat.domain.repository.ChatRepository;
import com.dcm.party.domain.Party;
import com.dcm.party.domain.repository.PartyRepository;
import com.dcm.party.dto.PartyRequest;
import com.dcm.hobby.domain.Hobby;
import com.dcm.hobby.domain.repository.HobbyRepository;
import com.dcm.hobby.exception.NotFoundHobbyException;
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
