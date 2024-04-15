package dcm.party.service;

import dcm.chat.domain.Chat;
import dcm.chat.domain.repository.ChatRepository;
import dcm.common.ServiceTest;
import dcm.hobby.domain.Hobby;
import dcm.hobby.domain.repository.HobbyRepository;
import dcm.party.domain.Party;
import dcm.party.domain.repository.PartyRepository;
import dcm.party.dto.PartyRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static dcm.global.enumurate.YN.N;
import static dcm.global.enumurate.YN.Y;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class PartyServiceTest extends ServiceTest {

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
        Optional<Hobby> hobby = Optional.ofNullable(Hobby.of(1L, "운동/스포츠", Y));
        Party party = new Party(1L, "scnoh@test.com", "강서풋살", 100, "37.402105,-122.081974",
                "서울시 강서구", "풋살모임입니다.", 0L, hobby.get());

        PartyRequest partyRequest = new PartyRequest("scnoh@test.com", "강서풋살", 100, "37.402105,-122.081974",
                "서울시 강서구", "풋살모임입니다.", 1L);
        Chat chat = new Chat(1L, N, party);

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