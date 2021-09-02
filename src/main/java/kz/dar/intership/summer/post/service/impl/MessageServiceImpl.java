package kz.dar.intership.summer.post.service.impl;

import kz.dar.intership.summer.post.entity.Message;
import kz.dar.intership.summer.post.entity.enumeration.Status;
import kz.dar.intership.summer.post.model.MessageDTO;
import kz.dar.intership.summer.post.repository.MessageRepository;
import kz.dar.intership.summer.post.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll().stream().map(m -> new MessageDTO(
                m.getId(),
                m.getRecipient(),
                m.getTitle(),
                m.getText(),
                m.getStatus().toString())).collect(Collectors.toList());
    }

    @Override
    public MessageDTO createMessage(MessageDTO messageDTO) {
        Message message = new Message();

        message.setTitle(messageDTO.getTitle());
        message.setRecipient(messageDTO.getRecipient());
        message.setText(messageDTO.getText());
        message.setStatus(Status.SAVED);

        messageRepository.save(message);
        messageRepository.flush();

        messageDTO.setStatus(Status.SAVED.toString());
        messageDTO.setId(message.getId());

        return messageDTO;
    }

    @Override
    public MessageDTO updateMessage(MessageDTO messageDTO, Long messageId) {
        Message message = messageRepository.findById(messageId).get();

        message.setStatus(Status.UPDATED);
        message.setText(messageDTO.getText());
        message.setTitle(messageDTO.getTitle());
        message.setRecipient(messageDTO.getRecipient());

        messageRepository.save(message);
        messageRepository.flush();

        messageDTO.setId(messageId);
        messageDTO.setStatus(Status.UPDATED.toString());

        return messageDTO;
    }

    @Override
    public String checkMessageStatus(Long messageId) {
        return messageRepository.findById(messageId).get().getStatus().toString();
    }

    @Override
    public MessageDTO sendMessage(Long messageId) {
        Message message = messageRepository.findById(messageId).get();

        message.setStatus(Status.DELIVERED);

        messageRepository.save(message);
        messageRepository.flush();

        return new MessageDTO(message.getId(), message.getRecipient(), message.getTitle(), message.getText(), message.getStatus().toString());
    }
}
