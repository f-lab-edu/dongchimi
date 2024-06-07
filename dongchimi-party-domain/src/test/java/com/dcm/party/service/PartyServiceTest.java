package com.dcm.party.service;

import com.dcm.chat.domain.Chat;
import com.dcm.chat.domain.repository.ChatRepository;
import com.dcm.fixtures.PartyFixtures;
import com.dcm.global.annotation.ServiceTest;
import com.dcm.hobby.domain.Hobby;
import com.dcm.hobby.domain.repository.HobbyRepository;
import com.dcm.party.domain.Party;
import com.dcm.party.domain.repository.PartyRepository;
import com.dcm.party.dto.PartyRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ServiceTest
class PartyServiceTest {

    @Mock
    private HobbyRepository hobbyRepository;

    @Mock
    private PartyRepository partyRepository;

    @Mock
    private ChatRepository chatRepository;

    @InjectMocks
    private PartyService partyService;


    @DisplayName("성공적으로 파티와 채팅방을 생성한다.")
    @Test
    void successPartyAndChat() {
        // given
        Optional<Hobby> hobby = Optional.ofNullable(PartyFixtures.CREATE_HOBBY());
        PartyRequest partyRequest = PartyFixtures.CREATE_PARTY_REQUEST();
        Party party = PartyFixtures.CREATE_PARTY();
        Chat chat = PartyFixtures.CREATE_CHAT();

        // when
        doReturn(hobby).when(hobbyRepository).findById(any(Long.class));
        doReturn(party).when(partyRepository).save(any(Party.class));
        doReturn(chat).when(chatRepository).save(any(Chat.class));
        partyService.writeParty(partyRequest);

        // then
        verify(hobbyRepository).findById(1L);
        verify(partyRepository).save(any(Party.class));
        verify(chatRepository).save(any(Chat.class));
    }


}