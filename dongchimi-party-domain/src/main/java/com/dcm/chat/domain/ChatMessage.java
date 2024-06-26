package com.dcm.chat.domain;

import com.dcm.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CHAT_MESSAGE")
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 200)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatId")
    private Chat chat;

    public ChatMessage(String email, String message, Chat chat) {
        this.email = email;
        this.message = message;
        this.chat = chat;
    }

}
