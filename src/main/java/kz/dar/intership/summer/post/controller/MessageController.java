package kz.dar.intership.summer.post.controller;

import kz.dar.intership.summer.post.feign.UserFeign;
import kz.dar.intership.summer.post.model.MessageDTO;
import kz.dar.intership.summer.post.model.PostUserDTO;
import kz.dar.intership.summer.post.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class MessageController {

    @Autowired
    Environment environment;

//    @Autowired
//    private UserFeign userFeign;

    @Autowired
    private MessageService messageService;

//    @GetMapping("/check/openfeign/user")
//    public String checkPostFeignClient() {
//        return userFeign.healthCheck();
//    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "It's  Working port: " + environment.getProperty("local.server.port");
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageDTO> getById(@PathVariable Long messageId) {
        return new ResponseEntity<>(messageService.getById(messageId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<MessageDTO> getAll() {
        return messageService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDTO> create(@Valid @RequestBody MessageDTO messageDTO) {
        messageService.create(messageDTO);
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    @GetMapping("/check-status/{messageId}")
    public String checkMessageStatus(@PathVariable Long messageId) {
        return messageService.checkMessageStatus(messageId);
    }

    @PutMapping("/send/{messageId}")
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable Long messageId) {
        return new ResponseEntity<>(messageService.send(messageId), HttpStatus.OK);
    }

    @PutMapping("/update/{messageId}")
    public ResponseEntity<MessageDTO> update(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Long messageId) {
        return new ResponseEntity<>(messageService.update(messageDTO, messageId), HttpStatus.OK);
    }
}
