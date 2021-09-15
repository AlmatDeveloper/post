package kz.dar.intership.summer.post.entity;

import kz.dar.intership.summer.post.entity.enumeration.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long recipientId;

    @NotNull
    private String title;

    private String text;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Message() {
    }

    public Message(Long id, Long recipientId, String title, String text, Status status) {
        this.id = id;
        this.recipientId = recipientId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
