package kz.dar.intership.summer.post.service;

import kz.dar.intership.summer.post.model.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getAllMessages();
    MessageDTO createMessage(MessageDTO messageDTO);
    MessageDTO updateMessage(MessageDTO messageDTO, Long messageId);
    String checkMessageStatus(Long messageId);
    MessageDTO sendMessage(Long messageId);
}
