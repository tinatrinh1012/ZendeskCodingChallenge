package model;

import java.util.Objects;

/**
 * Class that represents a Links object containing links to previous and next pages in a Ticket object
 *
 * @author Tina Trinh
 */
public class Links {

    /** link to previous page */
    private String prev;
    /** link to next page */
    private String next;

    /**
     * Constructor with no argument as default
     */
    public Links() {
        super();
    }

    /**
     * Constructor taking all information needed to construct a Links object
     * @param prev a String for the link to previous page
     * @param next a String for the link to next page
     */
    public Links(String prev, String next) {
        this.prev = prev;
        this.next = next;
    }

    /**
     * @return a String of the link to the previous page
     */
    public String getPrev() {
        return prev;
    }

    /**
     * @param prev a String to set the link for the current prev instance in Links object
     */
    public void setPrev(String prev) {
        this.prev = prev;
    }

    /**
     * @return a String of the link to the next page
     */
    public String getNext() {
        return next;
    }

    /**
     * @param next a String to set the link for the current next instance in Links object
     */
    public void setNext(String next) {
        this.next = next;
    }
}
