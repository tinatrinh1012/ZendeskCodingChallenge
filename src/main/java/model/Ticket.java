package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    private String url;
    private int id;
    //private int requester_id;
    //private int assignee_id;
    private String subject;
    private String created_at;
    private String description;

    public Ticket() {
        super();
    }

    public Ticket(int id, String subject, String createdAt) {
        this.id = id;
        //this.requester_id = requester_id;
        //this.assignee_id = assignee_id;
        this.subject = subject;
        this.created_at = createdAt;
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

    /*
        public int getRequester_id() {
            return requester_id;
        }

        public void setRequester_id(int requester_id) {
            this.requester_id = requester_id;
        }



        public int getAssignee_id() {
            return assignee_id;
        }

        public void setAssignee_id(int assignee_id) {
            this.assignee_id = assignee_id;
        }
         */
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

    public String toString() {
        return "Ticket ID: " + id + "\n" +
                "   Created at: " + created_at + "\n" +
                "   Subject: " + subject + "\n";
    }

    public String toStringDetails() {
        return "Ticket ID: " + id + "\n" +
                "   Created at: " + created_at + "\n" +
                "   Subject: " + subject + "\n" +
                "   Description: " + description + "\n";
    }
}
