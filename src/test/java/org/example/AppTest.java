package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Ticket;
import model.TicketViewer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;


public class AppTest 
{
    @Test
    public void getAllTicketsPageSizeTest() throws IOException, InterruptedException {
        TicketViewer ticketViewer = TicketViewer.getAllTickets();
        assertTrue("getAllTickets() should return a TicketViewer object with a ticket array of at most 25 tickets", ticketViewer.tickets.length <= 25);
    }

    @Test
    public void ticketViewerToStringTestWithEmptyTicketList() throws IOException, InterruptedException {
        TicketViewer emptyTicketViewer = TicketViewer.getAllTickets();
        emptyTicketViewer.tickets = new Ticket[0];
        String expectedString = "\n** YOUR TICKET(S) ** \n";
        expectedString = expectedString + "PAGE: " + emptyTicketViewer.pageCount + "\n";

        assertEquals("Empty ticket list should display like normal with no ticket information", expectedString, emptyTicketViewer.toString());
    }

    @Test
    public void ticketViewerToStringTestWithOneTicket() throws IOException, InterruptedException {
        TicketViewer ticketViewer = TicketViewer.getTicketById(111);
        String expectedString = "\n** YOUR TICKET(S) ** \n";
        expectedString = expectedString + "Ticket ID: 111" + "\n" +
                "   Created at: " + ticketViewer.tickets[0].getCreated_at() + "\n" +
                "   Subject: " + ticketViewer.tickets[0].getSubject() + "\n\n";
        expectedString = expectedString + "PAGE: " + ticketViewer.pageCount + "\n";

        assertEquals("One element ticket list should display one ticket information in compact style", expectedString, ticketViewer.toString());
    }

    @Test
    public void ticketViewerToStringTestWithThreeTickets() throws IOException, InterruptedException {
        Ticket[] testTicketArray = new Ticket[3];

        TicketViewer ticketViewer = TicketViewer.getTicketById(112);
        testTicketArray[0] = ticketViewer.tickets[0];

        ticketViewer = TicketViewer.getTicketById(113);
        testTicketArray[1] = ticketViewer.tickets[0];

        ticketViewer = TicketViewer.getTicketById(115);
        testTicketArray[2] = ticketViewer.tickets[0];

        ticketViewer.tickets = testTicketArray;

        String expectedString = "\n** YOUR TICKET(S) ** \n";

        expectedString = expectedString + "Ticket ID: 112" + "\n" +
                "   Created at: " + ticketViewer.tickets[0].getCreated_at() + "\n" +
                "   Subject: " + ticketViewer.tickets[0].getSubject() + "\n" + "\n";

        expectedString = expectedString + "Ticket ID: 113" + "\n" +
                "   Created at: " + ticketViewer.tickets[1].getCreated_at() + "\n" +
                "   Subject: " + ticketViewer.tickets[1].getSubject() + "\n" + "\n";

        expectedString = expectedString + "Ticket ID: 115" + "\n" +
                "   Created at: " + ticketViewer.tickets[2].getCreated_at() + "\n" +
                "   Subject: " + ticketViewer.tickets[2].getSubject() + "\n" + "\n";

        expectedString = expectedString + "PAGE: " + ticketViewer.pageCount + "\n";

        assertEquals("Three elements ticket list should display 3 tickets information in compact style", expectedString, ticketViewer.toString());
    }

    @Test
    public void ticketViewerToStringIndividualTestWithEmptyTicketList() throws IOException, InterruptedException {
        TicketViewer ticketViewer = TicketViewer.getTicketById(120);
        ticketViewer.tickets = new Ticket[0];
        String expectedString = "\n** YOUR TICKET(S) ** \n";
        assertEquals("Displaying individual ticket with empty ticket list should display nothing", expectedString, ticketViewer.toStringIndividual());
    }

    @Test
    public void ticketViewerToStringIndividualTestWithOneTicket() throws IOException, InterruptedException {
        TicketViewer ticketViewer = TicketViewer.getTicketById(120);
        String expectedString = "\n** YOUR TICKET(S) ** \n";
        expectedString = expectedString + "Ticket ID: 120" + "\n" +
                "   Created at: " + ticketViewer.tickets[0].getCreated_at() + "\n" +
                "   Requester ID: " + ticketViewer.tickets[0].getRequester_id() + "\n" +
                "   Assignee ID: " + ticketViewer.tickets[0].getAssignee_id() + "\n" +
                "   Subject: " + ticketViewer.tickets[0].getSubject() + "\n" +
                "   Tags: " + Arrays.toString(ticketViewer.tickets[0].getTags()) + "\n" +
                "   Description: " + ticketViewer.tickets[0].getDescription() + "\n\n";
        //expectedString = expectedString + "PAGE: " + ticketViewer.pageCount + "\n";

        assertEquals("Displaying individual ticket with one ticket should display the ticket information in details", expectedString, ticketViewer.toStringIndividual());
    }
}
