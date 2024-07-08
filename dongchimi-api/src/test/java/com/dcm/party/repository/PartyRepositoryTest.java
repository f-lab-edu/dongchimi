package com.dcm.party.repository;

import com.dcm.chat.domain.Chat;
import com.dcm.chat.domain.repository.ChatRepository;
import com.dcm.fixtures.PartyFixtures;
import com.dcm.global.annotation.RepositoryTest;
import com.dcm.hobby.domain.Hobby;
import com.dcm.hobby.domain.repository.HobbyRepository;
import com.dcm.party.domain.Party;
import com.dcm.party.domain.repository.PartyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RepositoryTest
public class PartyRepositoryTest {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @DisplayName("성공적으로 파티와 채팅방을 생성한다.")
    @Test
    void successPartyAndChat() {
        // given
        Hobby hobby = PartyFixtures.CREATE_HOBBY();
        Party party = PartyFixtures.CREATE_PARTY();
        Chat chat = PartyFixtures.CREATE_CHAT();

        // when
        hobbyRepository.save(hobby);
        partyRepository.save(party);
        chatRepository.save(chat);

        // then
        Optional<Party> findGroup = partyRepository.findById(party.getPartyId());
        assertTrue("그룹 생성에 성공하였습니다.", findGroup.isPresent());

        Optional<Chat> findChat = chatRepository.findById(chat.getChatId());
        assertTrue("채팅방 생성에 성공하였습니다.", findChat.isPresent());
    }

}
