package dcm.party.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dcm.common.ControllerTest;
import dcm.party.dto.PartyRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PartyController.class)
class PartyControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("성공적으로 파티를 생성한다.")
    @Test
    void successWriteParty() throws Exception {
        // given
        PartyRequest partyRequest = new PartyRequest("scnoh@test.com", "강서풋살", 100, "37.402105,-122.081974",
                "서울시 강서구", "풋살모임입니다.", 1L);

        // when
        mockMvc.perform(post("/api/party")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(partyRequest)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("파티 생성 시 정원 초과 및 1 이하 값 일 때 Bad Request 반환.")
    @Test
    void failWritePartyToInvalidCapacity() throws Exception {
        // given
        PartyRequest partyRequest = new PartyRequest("scnoh@test.com", "강서풋살", 301, "37.402105,-122.081974",
                "서울시 강서구", "풋살모임입니다.", 1L);

        // when
        mockMvc.perform(post("/api/party")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(partyRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("파티 생성 시 필수 값 누락 시 Bad Request 반환.")
    @Test
    void failWritePartyToMissingValues() throws Exception {
        // given
        PartyRequest partyRequest = new PartyRequest(null, "", 301, "37.402105,-122.081974",
                "서울시 강서구", "풋살모임입니다.", null);

        // when
        mockMvc.perform(post("/api/party")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(partyRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}