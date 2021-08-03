package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    private String url;
    private int id;
    private long requester_id;
    private long assignee_id;
    private String subject;
    private String created_at;
    private String description;
    private String[] tags;

    public Ticket() {
        super();
    }

    public Ticket(String url, int id, long requester_id, long assignee_id, String subject, String created_at, String description, String[] tags) {
        this.url = url;
        this.id = id;
        this.requester_id = requester_id;
        this.assignee_id = assignee_id;
        this.subject = subject;
        this.created_at = created_at;
        this.description = description;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public long getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(long requester_id) {
        this.requester_id = requester_id;
    }


    public long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(long assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String toString() {
        return "Ticket ID: " + id + "\n" +
                "   Created at: " + created_at + "\n" +
                "   Subject: " + subject + "\n";
    }

    public String toStringDetails() {
        return "Ticket ID: " + id + "\n" +
                "   Created at: " + created_at + "\n" +
                "   Requester ID: " + requester_id + "\n" +
                "   Assignee ID: " + assignee_id + "\n" +
                "   Subject: " + subject + "\n" +
                "   Tags: " + Arrays.toString(tags) + "\n" +
                "   Description: " + description + "\n";
    }
}
