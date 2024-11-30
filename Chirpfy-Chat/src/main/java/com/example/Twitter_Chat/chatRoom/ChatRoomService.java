package com.example.Twitter_Chat.chatRoom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createRoomIfNotExists
    ){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(ChatRoom::getChatId)
                .or(()->{
            if(createRoomIfNotExists){
                var chatId=createChat(senderId,recipientId);
                return Optional.of(chatId);
            }
            return Optional.empty();
        });

    }

    private String createChat(String senderId, String recipientId) {
        var chatId=String.format("%s_%s", senderId,recipientId);
        ChatRoom senderRecipient=ChatRoom.builder()
                .chatId(chatId)
                .recipientId(recipientId)
                .senderId(senderId)
                .build();

        ChatRoom recipientSender=ChatRoom.builder()
                .chatId(chatId)
                .recipientId(senderId)
                .senderId(recipientId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatId;
    }
}
