package model;

import java.util.Objects;

public class Meta {
    public boolean has_more;
    public String after_cursor;
    public String before_cursor;

    public Meta() {
        super();
    }

    public Meta(boolean has_more, String after_cursor, String before_cursor) {
        this.has_more = has_more;
        this.after_cursor = after_cursor;
        this.before_cursor = before_cursor;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public String getAfter_cursor() {
        return after_cursor;
    }

    public void setAfter_cursor(String after_cursor) {
        this.after_cursor = after_cursor;
    }

    public String getBefore_cursor() {
        return before_cursor;
    }

    public void setBefore_cursor(String before_cursor) {
        this.before_cursor = before_cursor;
    }
}
