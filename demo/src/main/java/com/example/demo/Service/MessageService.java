package com.example.demo.Service;

import com.example.demo.Entity.Message;
import com.example.demo.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Optional<Message> getMessagesForUser(Long userId) {
        return messageRepository.findById(userId);
    }

    public Page<Message> getMessagesForUserPaginated(String userId, Pageable pageable) {
        return messageRepository.findByReceiverId(userId, pageable);
    }

    public List<Message> getMessagesBetweenUsers(String senderId, String receiverId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    public Page<Message> getMessagesBetweenUsersPaginated(String senderId, String receiverId, Pageable pageable) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId, pageable);
    }

    public Message sendMessage(String senderId, String receiverId, String content) {
        Message message = new Message(senderId, receiverId, content, LocalDateTime.now(), false);
        return messageRepository.save(message);
    }

    public Message updateMessage(Long messageId, String newContent) {
        Message message = messageRepository.findById(messageId)
                                           .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setContent(newContent);
        return messageRepository.save(message);
    }
}
