package dcm.chat.domain;

import dcm.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CHAT_MESSAGE")
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(nullable = false)
    private Long partyId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 200)
    private String message;

    @ManyToOne
    @JoinColumn(name = "chatId")
    private Chat chat;

}
