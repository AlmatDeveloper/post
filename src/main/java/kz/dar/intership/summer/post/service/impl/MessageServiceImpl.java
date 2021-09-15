package kz.dar.intership.summer.post.service.impl;

import kz.dar.intership.summer.post.entity.Message;
import kz.dar.intership.summer.post.entity.enumeration.Status;
import kz.dar.intership.summer.post.feign.UserFeign;
import kz.dar.intership.summer.post.model.MessageDTO;
import kz.dar.intership.summer.post.model.PostUserDTO;
import kz.dar.intership.summer.post.repository.MessageRepository;
import kz.dar.intership.summer.post.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    UserFeign userFeign;

    @Override
    public MessageDTO getById(Long id) {
        Message message = messageRepository.findById(id).get();
        PostUserDTO postUserDTO = userFeign.getById(message.getRecipientId()).getBody();

        return new MessageDTO(message.getId(),
                message.getRecipientId(),
                postUserDTO.getEmail(),
                postUserDTO.getLastName(),
                postUserDTO.getFirstName(),
                message.getTitle(),
                message.getText(),
                message.getStatus().toString());
    }

    @Override
    public List<MessageDTO> getAll() {

        HashMap<Long, PostUserDTO> dtos = (HashMap<Long, PostUserDTO>) (userFeign.getAll().stream().map(m -> new PostUserDTO(
                m.getId(),
                m.getLastName(),
                m.getFirstName(),
                m.getEmail())).collect(Collectors.toList())).stream()
                .collect(Collectors.toMap(PostUserDTO::getId, Function.identity()));

        return messageRepository.findAll().stream().map(m -> new MessageDTO(
                m.getId(),
                m.getRecipientId(),
                dtos.get(m.getRecipientId()).getEmail(),
                dtos.get(m.getRecipientId()).getLastName(),
                dtos.get(m.getRecipientId()).getFirstName(),
                m.getTitle(),
                m.getText(),
                m.getStatus().toString())).collect(Collectors.toList());
    }

    @Override
    public MessageDTO create(MessageDTO messageDTO) {
        Message message = new Message();
        PostUserDTO postUserDTO = new PostUserDTO();

        postUserDTO.setEmail(messageDTO.getRecipientEmail());
        postUserDTO.setLastName(messageDTO.getRecipientLastName());
        postUserDTO.setFirstName(messageDTO.getRecipientFirstName());

        postUserDTO = userFeign.create(postUserDTO).getBody();

        message.setTitle(messageDTO.getTitle());
        message.setRecipientId(postUserDTO.getId());
        message.setText(messageDTO.getText());
        message.setStatus(Status.SAVED);

        messageRepository.save(message);
        messageRepository.flush();

        messageDTO.setStatus(Status.SAVED.toString());
        messageDTO.setId(message.getId());
        messageDTO.setRecipientId(message.getRecipientId());

        return messageDTO;
    }

    @Override
    public MessageDTO update(MessageDTO messageDTO, Long messageId) {
        Message message = messageRepository.findById(messageId).get();
        PostUserDTO postUserDTO = userFeign.getById(message.getRecipientId()).getBody();

        postUserDTO.setFirstName(messageDTO.getRecipientFirstName());
        postUserDTO.setLastName(messageDTO.getRecipientLastName());
        postUserDTO.setEmail(messageDTO.getRecipientEmail());

        postUserDTO = userFeign.update(postUserDTO, postUserDTO.getId()).getBody();

        message.setStatus(Status.UPDATED);
        message.setText(messageDTO.getText());
        message.setTitle(messageDTO.getTitle());
        message.setRecipientId(postUserDTO.getId());

        messageRepository.save(message);
        messageRepository.flush();

        messageDTO.setStatus(Status.UPDATED.toString());

        return messageDTO;
    }

    @Override
    public String checkMessageStatus(Long messageId) {
        return messageRepository.findById(messageId).get().getStatus().toString();
    }

    @Override
    public MessageDTO send(Long messageId) {
        Message message = messageRepository.findById(messageId).get();
        PostUserDTO postUserDTO = userFeign.getById(message.getRecipientId()).getBody();

        message.setStatus(Status.DELIVERED);

        messageRepository.save(message);
        messageRepository.flush();

        return new MessageDTO(message.getId(),
                postUserDTO.getId(),
                postUserDTO.getEmail(),
                postUserDTO.getLastName(),
                postUserDTO.getFirstName(),
                message.getTitle(),
                message.getText(),
                message.getStatus().toString());
    }
}
