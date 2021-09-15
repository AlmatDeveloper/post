package kz.dar.intership.summer.post.feign;

import kz.dar.intership.summer.post.model.MessageDTO;
import kz.dar.intership.summer.post.model.PostUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "post-user-core-api")
public interface UserFeign {

    @GetMapping("/post-user/health-check")
    String healthCheck();

    @GetMapping("/post-user/{postUserId}")
    ResponseEntity<PostUserDTO> getById(@PathVariable Long postUserId);

    @GetMapping("/post-user/all")
    List<PostUserDTO> getAll();

    @PostMapping("/post-user/create")
    ResponseEntity<PostUserDTO> create(@Valid @RequestBody PostUserDTO postUserDTO);

    @PutMapping("/post-user/update/{postUserId}")
    ResponseEntity<PostUserDTO> update(@Valid @RequestBody PostUserDTO postUserDTO, @PathVariable Long postUserId);
}
