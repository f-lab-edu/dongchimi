package com.dcm.fixtures;

import com.dcm.chat.domain.Chat;
import com.dcm.chat.domain.ChatMessage;
import com.dcm.global.enumurate.YN;
import com.dcm.hobby.domain.Hobby;
import com.dcm.party.domain.Party;
import com.dcm.party.dto.PartyRequest;

import static com.dcm.global.enumurate.YN.Y;

public class PartyFixtures {

    public static final YN USE_YN = Y;

    // TODO Hobby
    public static final Long HOBBY_ID = 1L;
    public static final String HOBBY_NAME = "운동/스포츠";

    // TODO Party
    public static final Long PARTY_ID = 1L;
    public static final String MANAGER_ID = "test@tset.com";
    public static final String PARTY_NAME = "강서풋살";
    public static final Integer CAPACITY = 100;
    public static final String MEET_ADDRESS = "37.402105,-122.081974";
    public static final String MEET_ADDRESS_NAME = "서울시 강서구";
    public static final String DESCRIPTION = "풋살모임입니다";
    public static final Long VOTE = 0L;


    // TODO Chat
    public static final Long CHAT_ID = 1L;

    // TODO Chat Message
    public static final Long CHAT_MESSAGE_ID = 1L;
    public static final String EMAIL = "test@test.com";
    public static final String MESSAGE = "Hi";

    public static Hobby CREATE_HOBBY() {
        return Hobby.of(HOBBY_ID, HOBBY_NAME, USE_YN);
    }

    public static Party CREATE_PARTY() {
        return new Party(PARTY_ID, MANAGER_ID, PARTY_NAME, CAPACITY, MEET_ADDRESS,
                MEET_ADDRESS_NAME, DESCRIPTION, VOTE, CREATE_HOBBY());
    }

    public static PartyRequest CREATE_PARTY_REQUEST() {
        return new PartyRequest(MANAGER_ID, PARTY_NAME, CAPACITY, MEET_ADDRESS,
                MEET_ADDRESS_NAME, DESCRIPTION, HOBBY_ID);
    }

    public static Chat CREATE_CHAT() {
        return new Chat(CHAT_ID, USE_YN, CREATE_PARTY());
    }

    public static ChatMessage CREATE_CHAT_MESSAGE() {
        return new ChatMessage(EMAIL, MESSAGE, CREATE_CHAT());
    }

}
