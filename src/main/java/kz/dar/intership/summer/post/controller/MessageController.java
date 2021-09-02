package kz.dar.intership.summer.post.controller;

import kz.dar.intership.summer.post.model.MessageDTO;
import kz.dar.intership.summer.post.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
public class MessageController {
    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @GetMapping("/messages")
    public List<MessageDTO> getAllMessages() {
        return messageServiceImpl.getAllMessages();
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody MessageDTO messageDTO) {
        messageServiceImpl.createMessage(messageDTO);
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    @GetMapping("/check-status/{messageId}")
    public String checkMessageStatus(@PathVariable Long messageId) {
        return messageServiceImpl.checkMessageStatus(messageId);
    }

    @PutMapping("/send/{messageId}")
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable Long messageId) {
        return new ResponseEntity<>(messageServiceImpl.sendMessage(messageId), HttpStatus.OK);
    }

    @PutMapping("/update/{messageId}")
    public ResponseEntity<MessageDTO> updateMessage(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Long messageId) {
        return new ResponseEntity<>(messageServiceImpl.updateMessage(messageDTO, messageId), HttpStatus.OK);
    }
}
