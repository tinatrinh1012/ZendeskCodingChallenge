package model;

import java.util.Objects;

/**
 * Class that represents a Meta object containing information about cursor pagination in a TicketViewer object that get returned
 *
 * @author Tina Trinh
 */
public class Meta {

    /** whether there is a next page */
    public boolean has_more;
    /** cursor for the next page */
    public String after_cursor;
    /** cursor for the previous page */
    public String before_cursor;

    /**
     * Constructor with no argument as default
     */
    public Meta() {
        super();
    }

    /**
     * Constructor taking all information needed to construct a Meta object
     * @param has_more whether there is a next page
     * @param after_cursor cursor for requesting next page
     * @param before_cursor cursor for requesting previous page
     */
    public Meta(boolean has_more, String after_cursor, String before_cursor) {
        this.has_more = has_more;
        this.after_cursor = after_cursor;
        this.before_cursor = before_cursor;
    }

    /**
     * @return a boolean true if there is a next page and false otherwise
     */
    public boolean isHas_more() {
        return has_more;
    }

    /**
     * @param has_more a boolean to set the current has_more instance in Meta
     */
    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    /**
     * @return a String of the cursor for requesting the next page
     */
    public String getAfter_cursor() {
        return after_cursor;
    }

    /**
     * @param after_cursor a String to set the current after_cursor instance in Meta
     */
    public void setAfter_cursor(String after_cursor) {
        this.after_cursor = after_cursor;
    }

    /**
     * @return a String of the cursor for requesting the previous page
     */
    public String getBefore_cursor() {
        return before_cursor;
    }

    /**
     * @param before_cursor a String to set the current before_cursor instance in Meta
     */
    public void setBefore_cursor(String before_cursor) {
        this.before_cursor = before_cursor;
    }
}
