package kz.dar.intership.summer.post.service;

import kz.dar.intership.summer.post.model.MessageDTO;
import kz.dar.intership.summer.post.model.PostUserDTO;

import java.util.List;

public interface MessageService {
    MessageDTO getById(Long id);
    List<MessageDTO> getAll();
    MessageDTO create(MessageDTO messageDTO);
    MessageDTO update(MessageDTO messageDTO, Long messageId);
    String checkMessageStatus(Long messageId);
    MessageDTO send(Long messageId);
}
