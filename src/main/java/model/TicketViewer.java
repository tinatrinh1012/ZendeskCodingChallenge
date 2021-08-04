package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.App;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

/**
 * Class to make API request and represent a page of tickets object that get returned
 *
 * @author Tina Trinh
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketViewer {

    /** Zendesk API for requesting tickets for this account */
    private static final String TICKETS_APi = "https://zcctina.zendesk.com/api/v2/tickets";
    /** Tickets page size */
    private static final int PAGE_SIZE = 25;
    /** Array of tickets per page that get returned */
    public Ticket[] tickets;
    /** Meta information for cursor pagination */
    public Meta meta;
    /** Links to next or previous page */
    public Links links;
    /** Page number */
    public int pageCount;

    /**
     * Constructor with no argument as default
     */
    public TicketViewer() {
        super();
    }

    /**
     * Constructor taking all information needed to construct a TicketViewer object
     * @param tickets Ticket array
     * @param meta Meta object with cursor pagination information
     * @param links Links object with links to next or previous page
     * @param pageCount an integer of the page number
     */
    public TicketViewer(Ticket[] tickets, Meta meta, Links links, int pageCount) {
        this.tickets = tickets;
        this.meta = meta;
        this.links = links;
        this.pageCount = pageCount;
    }

    /**
     * Static method to request all tickets
     * @return a TicketViewer object that represent a page of at most 25 tickets
     * @throws IOException
     * @throws InterruptedException
     */
    public static TicketViewer getAllTickets() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredential = App.getEncodedCredential();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Basic " + encodedCredential)
                .header("Accept", "application/json")
                .uri(URI.create(TICKETS_APi + ".json?page[size]=" + PAGE_SIZE))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw new ConnectException();
        }

        ObjectMapper mapper = new ObjectMapper();
        TicketViewer ticketViewer = mapper.readValue(response.body(), TicketViewer.class);
        ticketViewer.pageCount = 1;
        return ticketViewer;
    }

    /**
     * Static method to request information about a specific ticket using ticket ID
     * @param id ticket id
     * @return a TicketViewer object that represent a page with one single ticket
     * @throws IOException
     * @throws InterruptedException
     */
    public static TicketViewer getTicketById(int id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredential = App.getEncodedCredential();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Basic " + encodedCredential)
                .header("Accept", "application/json")
                .uri(URI.create(TICKETS_APi + "/show_many.json?ids=" + id))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw new ConnectException();
        }

        ObjectMapper mapper = new ObjectMapper();
        TicketViewer ticketViewer = mapper.readValue(response.body(), TicketViewer.class);
        ticketViewer.pageCount = 1;

        // non-exist id returns an empty ticket array
        if (ticketViewer.tickets.length == 0) {
            throw new IOException();
        }

        return ticketViewer;

    }

    /**
     * Method to get the next page of the current page or the last page if the current page is the last
     * @return a TicketViewer object that represent the next page of the current TicketViewer object
     * @throws IOException
     * @throws InterruptedException
     */
    public TicketViewer getNextPage() throws IOException, InterruptedException {
        if (this.meta.has_more) {
            HttpClient client = HttpClient.newHttpClient();
            String encodedCredential = App.getEncodedCredential();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Authorization", "Basic " + encodedCredential)
                    .header("Accept", "application/json")
                    .uri(URI.create(TICKETS_APi + ".json?page[size]=" + PAGE_SIZE + "&page[after]=" + meta.after_cursor))
                    .build();

            HttpResponse<String> response;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (ConnectException e) {
                throw new ConnectException();
            }

            ObjectMapper mapper = new ObjectMapper();
            TicketViewer ticketViewer = mapper.readValue(response.body(), TicketViewer.class);

            // sometimes, meta.has_more returns true, but next page ticket array is empty
            // and after_cursor and before_cursor are null (API document)
            // this condition checks for this special case
            if (ticketViewer.tickets.length == 0) {
                return this;
            } else {
                ticketViewer.pageCount = this.pageCount + 1;
                return ticketViewer;
            }
        }
        return this;
    }

    /**
     * Method to get the previous page of the current page or the first page if the current page is the first
     * @return a TicketViewer object that represent the previous page of the current TicketViewer object
     * @throws IOException
     * @throws InterruptedException
     */
    public TicketViewer getPreviousPage() throws IOException, InterruptedException {
        if (this.pageCount > 1) {
            HttpClient client = HttpClient.newHttpClient();
            String encodedCredential = App.getEncodedCredential();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Authorization", "Basic " + encodedCredential)
                    .header("Accept", "application/json")
                    .uri(URI.create(TICKETS_APi + ".json?page[size]=" + PAGE_SIZE + "&page[before]=" + this.meta.before_cursor))
                    .build();

            HttpResponse<String> response;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (ConnectException e) {
                throw new ConnectException();
            }

            ObjectMapper mapper = new ObjectMapper();
            TicketViewer ticketViewer = mapper.readValue(response.body(), TicketViewer.class);
            ticketViewer.pageCount = this.pageCount - 1;
            return ticketViewer;
        }
        return this;
    }

    /**
     * @return the current Meta instance in TicketViewer
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * @param meta a Meta object to set the current Meta instance in TicketViewer
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * @return the current Links instance in TicketViewer
     */
    public Links getLinks() {
        return links;
    }

    /**
     * @param links a Links object to set the current Links instance in TicketViewer
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * @return the current array of Ticket instance in TicketViewer
     */
    public Ticket[] getTickets() {
        return tickets;
    }

    /**
     *
     * @param tickets an array of Ticket to set the current Ticket array instance in TicketViewer
     */
    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    /**
     * Method to display tickets list in short
     * @return a String contains tickets information
     */
    @Override
    public String toString() {
        String finalString = "\n** YOUR TICKET(S) ** \n";
        if (tickets != null && tickets.length > 0) {
            for (Ticket ticket : tickets) {
                if (ticket != null) {
                    finalString = finalString + ticket.toString() + "\n";
                }
            }
        }
        finalString = finalString + "PAGE: " + pageCount + "\n";
        return finalString;
    }

    /**
     * Method to display an individual ticket with more details
     * @return a String contains a single ticket information with more details
     */
    public String toStringIndividual() {
        String finalString = "\n** YOUR TICKET(S) ** \n";
        if (tickets != null && tickets.length > 0) {
            Ticket ticket = tickets[0];
            finalString = finalString + ticket.toStringDetails() + "\n";
        }
        return finalString;
    }
}
