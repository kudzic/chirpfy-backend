package com.example.Twitter_Chat.chatMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ChatMessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage savedMsg=chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(savedMsg.getRecipientID(),"/queue/messages",ChatNotification.builder()
                .id(savedMsg.getChatId())
                .recipientId(savedMsg.getRecipientID())
                .senderId(savedMsg.getSenderId())
                .content(savedMsg.getContent()).build())
                ;
    }


    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable ("senderId") String senderId,
            @PathVariable ("recipientId") String recipientId
    ){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }
}
