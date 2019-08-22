package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Messagepost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date posteddate;

    @NotNull
    private String sentby;

    @NotNull
    private String pic;

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

    public Date getPosteddate() {
        return posteddate;
    }

    public void setPosteddate(Date posteddate) {
        this.posteddate = posteddate;
    }

    public String getSentby() {
        return sentby;
    }

    public void setSentby(String sentby) {
        this.sentby = sentby;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
