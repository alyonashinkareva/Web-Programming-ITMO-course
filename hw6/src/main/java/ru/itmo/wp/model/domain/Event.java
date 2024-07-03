package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.*;

public class Event implements Serializable {
    private long id;
    private long userId;
    private EventType type;
    private Date creationTime;

    public enum EventType{
        ENTER, LOGOUT
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
