package dcm.party.repository;

import com.dcm.chat.domain.Chat;
import com.dcm.chat.domain.repository.ChatRepository;
import dcm.common.RepositoryTest;
import com.dcm.party.domain.Party;
import com.dcm.party.domain.repository.PartyRepository;
import com.dcm.party.dto.PartyRequest;
import com.dcm.hobby.domain.Hobby;
import com.dcm.hobby.domain.repository.HobbyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.dcm.global.enumurate.YN.Y;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class PartyRepositoryTest extends RepositoryTest {

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
        Hobby hobby = Hobby.of("운동/스포츠", Y);
        hobbyRepository.save(hobby);
        PartyRequest partyRequest = new PartyRequest("scnoh@test.com", "강서풋살", 100, "37.402105,-122.081974",
                "서울시 강서구", "풋살모임입니다.", 1L);

        Party party = Party.toEntity(partyRequest, hobby);
        Chat chat = new Chat(party);

        // when
        partyRepository.save(party);
        chatRepository.save(chat);

        // then
        Optional<Party> findGroup = partyRepository.findById(party.getPartyId());
        assertTrue("그룹 생성에 실패하였습니다.", findGroup.isPresent());

        Optional<Chat> findChat = chatRepository.findById(chat.getChatId());
        assertTrue("채팅방 생성에 실패하였습니다.", findChat.isPresent());
    }

}
