package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Class that represent a ticket object in a ticket list that gets returned
 *
 * @author Tina Trinh
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    /** Ticket id */
    private int id;
    /** Ticket requester id */
    private long requester_id;
    /** Ticket assignee id */
    private long assignee_id;
    /** Ticket subject */
    private String subject;
    /** When ticket was created */
    private String created_at;
    /** Ticket description */
    private String description;
    /** Ticket tags */
    private String[] tags;

    /**
     * Constructor with no argument as default
     */
    public Ticket() {
        super();
    }

    /**
     * Constructor taking all information needed to a Ticket object
     * @param id ticket id
     * @param requester_id ticket requester id
     * @param assignee_id ticket assignee id
     * @param subject a String of ticket subject
     * @param created_at a String of when the ticket was created
     * @param description a String of ticket description
     * @param tags a String array of ticket tags
     */
    public Ticket(int id, long requester_id, long assignee_id, String subject, String created_at, String description, String[] tags) {
        this.id = id;
        this.requester_id = requester_id;
        this.assignee_id = assignee_id;
        this.subject = subject;
        this.created_at = created_at;
        this.description = description;
        this.tags = tags;
    }

    /**
     * @return an integer of the current ticket id instance
     */
    public int getId() {
        return id;
    }

    /**
     * @param id an integer to set the current ticket id instance
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return a String of the current ticket description instance
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description a String to set the current description instance in Ticket
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return a long of the current ticket requester id
     */
    public long getRequester_id() {
        return requester_id;
    }

    /**
     * @param requester_id a long to set the current ticket requester_id instance in Ticket
     */
    public void setRequester_id(long requester_id) {
        this.requester_id = requester_id;
    }

    /**
     * @return a long of the current ticket assignee id
     */
    public long getAssignee_id() {
        return assignee_id;
    }

    /**
     * @param assignee_id a long to set the current ticket assignee_id instance in Ticket
     */
    public void setAssignee_id(long assignee_id) {
        this.assignee_id = assignee_id;
    }

    /**
     * @return a String of the current ticket subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject a String to set the current ticket subject instance in Ticket
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return a String of the time the ticket was created
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at a String to set the current ticket created_at instance in Ticket
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * @return a String array of the ticket tags
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * @param tags a String array to set the current ticket tags instance in Ticket
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    /**
     * Method to display ticket information in short
     * @return a String that displays ticket information in short
     */
    public String toString() {
        return "Ticket ID: " + id + "\n" +
                "   Created at: " + created_at + "\n" +
                "   Subject: " + subject + "\n";
    }

    /**
     * Method to display ticket information with more details
     * @return a String that displays ticket information in details
     */
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
