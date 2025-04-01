package com.example.demo.Controller;

import com.example.demo.Entity.Message;
import com.example.demo.Service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
@Tag(name = "Message API", description = "Endpoints for sending and retrieving messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Operation(summary = "Get messages for a user", description = "Retrieve all messages for a specific user")
    @GetMapping("/user/{userId}")
    public Optional<Message> getMessagesForUser(
            @Parameter(description = "ID of the user") @PathVariable Long userId) {
        return messageService.getMessagesForUser(userId);
    }

    @Operation(summary = "Get paginated messages for a user", description = "Retrieve paginated messages for a user")
    @GetMapping("/user/{userId}/paginated")
    public Page<Message> getMessagesForUserPaginated(
            @Parameter(description = "ID of the user") @PathVariable String userId,
            Pageable pageable) {
        return messageService.getMessagesForUserPaginated(userId, pageable);
    }

    @Operation(summary = "Get messages between two users", description = "Retrieve all messages exchanged between two users")
    @GetMapping("/{senderId}/{receiverId}")
    public List<Message> getMessagesBetweenUsers(
            @Parameter(description = "Sender ID") @PathVariable String senderId,
            @Parameter(description = "Receiver ID") @PathVariable String receiverId) {
        return messageService.getMessagesBetweenUsers(senderId, receiverId);
    }

    @Operation(summary = "Get paginated messages between two users", description = "Retrieve paginated messages between two users")
    @GetMapping("/{senderId}/{receiverId}/paginated")
    public Page<Message> getMessagesBetweenUsersPaginated(
            @Parameter(description = "Sender ID") @PathVariable String senderId,
            @Parameter(description = "Receiver ID") @PathVariable String receiverId,
            Pageable pageable) {
        return messageService.getMessagesBetweenUsersPaginated(senderId, receiverId, pageable);
    }

    @Operation(summary = "Send a new message", description = "Send a message from one user to another")
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Map<String, Object> request) {
        try {
            if (!request.containsKey("senderId") || !request.containsKey("receiverId") || !request.containsKey("content")) {
                return ResponseEntity.badRequest().body("Missing required fields: senderId, receiverId, or content");
            }

            String senderId = request.get("senderId").toString();
            String receiverId = request.get("receiverId").toString();
            String content = request.get("content").toString();

            Message message = messageService.sendMessage(senderId, receiverId, content);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while sending the message: " + e.getMessage());
        }
    }

    @Operation(summary = "Update a message", description = "Update the content of an existing message")
    @PutMapping("/{messageId}")
    public ResponseEntity<Message> updateMessage(
            @Parameter(description = "ID of the message to update") @PathVariable Long messageId,
            @RequestBody Map<String, Object> request) {
        String newContent = request.get("content").toString();
        Message updatedMessage = messageService.updateMessage(messageId, newContent);
        return ResponseEntity.ok(updatedMessage);
    }
}
