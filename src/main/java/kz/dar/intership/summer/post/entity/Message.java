package kz.dar.intership.summer.post.entity;

import kz.dar.intership.summer.post.entity.enumeration.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Email
    private String recipient;

    @NotNull
    private String title;

    private String text;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Message() {
    }

    public Message(Long id, @NotNull @Email String recipient, @NotNull String title, String text, Status status) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
