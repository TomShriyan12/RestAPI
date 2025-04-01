package com.example.demo.Repository;

import com.example.demo.Entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverId(String receiverId);
    List<Message> findBySenderIdAndReceiverId(String senderId, String receiverId);

    Page<Message> findByReceiverId(String receiverId, Pageable pageable);
    Page<Message> findBySenderIdAndReceiverId(String senderId, String receiverId, Pageable pageable);
}
