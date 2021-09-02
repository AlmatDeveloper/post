package kz.dar.intership.summer.post.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class MessageDTO {

    private Long id;

    @NotNull(message = "recipient can not be null")
    @Email
    private String recipient;

    @NotNull(message = "title can not be null")
    @NotNull
    private String title;

    private String text;

    private String status;

    public MessageDTO() {
    }

    public MessageDTO(@NotNull Long id, @NotNull @Email String recipient, @NotNull String title, String text, String status) {
        this.id = id;
        this.recipient = recipient;
        this.title = title;
        this.text = text;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
