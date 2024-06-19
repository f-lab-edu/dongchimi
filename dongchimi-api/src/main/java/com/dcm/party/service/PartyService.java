package com.dcm.party.service;

import com.dcm.chat.domain.Chat;
import com.dcm.chat.domain.repository.ChatRepository;
import com.dcm.hobby.domain.Hobby;
import com.dcm.hobby.domain.repository.HobbyRepository;
import com.dcm.hobby.exception.NotFoundHobbyException;
import com.dcm.party.domain.Party;
import com.dcm.party.domain.PartyJoinUser;
import com.dcm.party.domain.repository.PartyJoinUserRepository;
import com.dcm.party.domain.repository.PartyRepository;
import com.dcm.party.dto.PartyJoinRequest;
import com.dcm.party.dto.PartyLikeRequest;
import com.dcm.party.dto.PartyRequest;
import com.dcm.party.exception.ExistPartyRequestException;
import com.dcm.party.exception.NotFoundPartyException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartyService {

    private static final String LIKE_PARTY_SUBSCRIBE_PREFIX = "/sub/like/party-";

    private final PartyRepository partyRepository;
    private final PartyJoinUserRepository partyJoinUserRepository;
    private final HobbyRepository hobbyRepository;
    private final ChatRepository chatRepository;
    private final StringRedisTemplate redisTemplate;

    @Transactional
    public void createParty(PartyRequest request) {
        Hobby hobby = readHobby(request.hobbyId());
        Party party = Party.toEntity(request, hobby);

        partyRepository.save(party);
        crateChat(party);
    }

    @Transactional
    public void requestPartyJoin(PartyJoinRequest request) {
        Party party = validateParty(request.partyId());
        if (partyJoinUserRepository.existsByEmail(request.email())) {
            throw new ExistPartyRequestException(request.email());
        }

        PartyJoinUser partyJoinUser = PartyJoinUser.toPartyJoin(request, party);
        partyJoinUserRepository.save(partyJoinUser);
    }

    public Party validateParty(Long partyId) {
        return partyRepository.findById(partyId).orElseThrow(() -> new NotFoundPartyException(partyId));
    }

    public Hobby readHobby(Long hobbyId) {
        Optional<Hobby> hobby = hobbyRepository.findById(hobbyId);
        if (hobby.isPresent())
            return hobby.get();
        throw new NotFoundHobbyException(hobbyId);
    }

    public void executePartyLike(PartyLikeRequest request) {
        validateParty(request.partyId());
        redisTemplate.convertAndSend(
            LIKE_PARTY_SUBSCRIBE_PREFIX + request.partyId(),
            request.partyId()
        );
    }

    private void crateChat(Party party) {
        Chat chat = new Chat(party);
        chatRepository.save(chat);
    }

}
