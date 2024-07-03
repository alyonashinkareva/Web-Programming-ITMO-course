package ru.itmo.wp.form;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.*;

public class NoticeForm {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Lob
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
