package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.App;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Base64;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketViewer {
    private static final String TICKETS_APi = "https://zcctina.zendesk.com/api/v2/tickets";
    private static final int PAGE_SIZE = 5;
    public Ticket[] tickets;
    public Meta meta;
    public Links links;
    public int pageCount;

    public TicketViewer() {
        super();
    }

    public TicketViewer(Ticket[] tickets) {
        this.tickets = tickets;
    }


    public static TicketViewer getAllTickets() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredential = App.getEncodedCredential();

        // send GET request for all tickets
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Basic " + encodedCredential)
                .header("Accept", "application/json")
                .uri(URI.create(TICKETS_APi + ".json?page[size]=" + PAGE_SIZE))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // parse JSON data into Java object
        ObjectMapper mapper = new ObjectMapper();
        TicketViewer ticketViewer = mapper.readValue(response.body(), TicketViewer.class);
        ticketViewer.pageCount = 1;
        return ticketViewer;
    }

    public static TicketViewer getTicketById(int id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredential = App.getEncodedCredential();

        // send GET request for all tickets
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Basic " + encodedCredential)
                .header("Accept", "application/json")
                .uri(URI.create(TICKETS_APi + "/show_many.json?ids=" + id))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // parse JSON data into Java object
        ObjectMapper mapper = new ObjectMapper();
        TicketViewer ticketViewer = mapper.readValue(response.body(), TicketViewer.class);
        ticketViewer.pageCount = 1;
        return ticketViewer;
    }

    public TicketViewer getNextPage() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredential = App.getEncodedCredential();

        // send GET request for all tickets
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Basic " + encodedCredential)
                .header("Accept", "application/json")
                .uri(URI.create(TICKETS_APi + ".json?page[size]=" + PAGE_SIZE + "&page[after]=" + meta.after_cursor))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // parse JSON data into Java object
        ObjectMapper mapper = new ObjectMapper();
        TicketViewer ticketViewer = mapper.readValue(response.body(), TicketViewer.class);
        ticketViewer.pageCount = this.pageCount + 1;
        return ticketViewer;
    }

    public TicketViewer getPreviousPage() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredential = App.getEncodedCredential();

        // send GET request for all tickets
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Basic " + encodedCredential)
                .header("Accept", "application/json")
                .uri(URI.create(TICKETS_APi + ".json?page[size]=" + PAGE_SIZE + "&page[before]=" + meta.before_cursor))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // parse JSON data into Java object
        ObjectMapper mapper = new ObjectMapper();
        TicketViewer ticketViewer = mapper.readValue(response.body(), TicketViewer.class);
        ticketViewer.pageCount = this.pageCount - 1;
        return ticketViewer;
    }




    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        String finalString = "\n** YOUR TICKET(S) ** \n";
        for (Ticket ticket : tickets) {
            finalString = finalString + ticket.toString() + "\n";
        }
        finalString = finalString + "PAGE: " + pageCount + "\n";
        return finalString;
    }

    public String toStringIndividual() {
        String finalString = "\n** YOUR TICKET(S) ** \n";
        Ticket ticket = tickets[0];
        finalString = finalString + ticket.toStringDetails() + "\n";
        return finalString;
    }
}
