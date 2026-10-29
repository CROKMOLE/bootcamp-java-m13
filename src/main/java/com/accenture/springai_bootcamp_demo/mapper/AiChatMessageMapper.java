package com.accenture.springai_bootcamp_demo.mapper;

import com.accenture.springai_bootcamp_demo.entity.ChatMessage;
import com.accenture.springai_bootcamp_demo.entity.Role;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AiChatMessageMapper {

    public List<Message> toChaMessage(List<ChatMessage> history) {
        return history.stream()
                .map(this::toSpringMessage)
                .toList();
    }

    private Message toSpringMessage(ChatMessage m) {
        return switch (m.getRole()) {
            case Role.SYSTEM -> new SystemMessage(m.getContent());
            case Role.ASSISTANT -> new AssistantMessage(m.getContent());
            default          -> new UserMessage(m.getContent());
        };
    }
}
