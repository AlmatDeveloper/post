package kz.dar.intership.summer.post.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class MessageDTO {

    private Long id;

    private Long recipientId;

    @NotNull(message = "recipient can not be null")
    @Email
    private String recipientEmail;

    private String recipientLastName;

    private String recipientFirstName;

    @NotNull(message = "title can not be null")
    @NotNull
    private String title;

    private String text;

    private String status;

    public MessageDTO() {
    }

    public MessageDTO(Long id, Long recipientId, String recipientEmail, String recipientLastName, String recipientFirstName, String title, String text, String status) {
        this.id = id;
        this.recipientId = recipientId;
        this.recipientEmail = recipientEmail;
        this.recipientLastName = recipientLastName;
        this.recipientFirstName = recipientFirstName;
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

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientLastName() {
        return recipientLastName;
    }

    public void setRecipientLastName(String recipientLastName) {
        this.recipientLastName = recipientLastName;
    }

    public String getRecipientFirstName() {
        return recipientFirstName;
    }

    public void setRecipientFirstName(String recipientFirstName) {
        this.recipientFirstName = recipientFirstName;
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
