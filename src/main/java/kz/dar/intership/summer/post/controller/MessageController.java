package kz.dar.intership.summer.post.controller;

import kz.dar.intership.summer.post.model.MessageDTO;
import kz.dar.intership.summer.post.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
public class MessageController {

    @Autowired
    Environment environment;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "It's  Working port: " + environment.getProperty("local.server.port");
    }

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody MessageDTO messageDTO) {
        messageService.createMessage(messageDTO);
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    @GetMapping("/check-status/{messageId}")
    public String checkMessageStatus(@PathVariable Long messageId) {
        return messageService.checkMessageStatus(messageId);
    }

    @PutMapping("/send/{messageId}")
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable Long messageId) {
        return new ResponseEntity<>(messageService.sendMessage(messageId), HttpStatus.OK);
    }

    @PutMapping("/update/{messageId}")
    public ResponseEntity<MessageDTO> updateMessage(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Long messageId) {
        return new ResponseEntity<>(messageService.updateMessage(messageDTO, messageId), HttpStatus.OK);
    }
}
