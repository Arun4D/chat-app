package in.co.ad.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.ad.chatapp.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {}
