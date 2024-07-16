package in.co.ad.chatapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.co.ad.chatapp.model.Message;
import in.co.ad.chatapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessageRepository messageRepository;


    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
	message = messageRepository.save(message);
        return message;
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }
}   
