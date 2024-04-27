package dcm.chat.domain;

import dcm.global.domain.BaseEntity;
import dcm.global.enumurate.YN;
import dcm.party.domain.Party;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "CHAT")
public class Chat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long chatId;

    private YN freezeYn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partyId")
    private Party party;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ChatMessage> messages = new ArrayList<>();

    public Chat(Long chatId, YN freezeYn, Party party) {
        this.chatId = chatId;
        this.freezeYn = freezeYn;
        this.party = party;
    }

    public Chat(Party party) {
        this.party = party;
    }

}
