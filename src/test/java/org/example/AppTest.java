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

/**
 * Class for unit testing of the Ticket Viewer program
 *
 * @author Tina Trinh
 */
public class AppTest 
{
    /**
     * Test to ensure the ticket array returned is less than or equal to 25, following the page size
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void getAllTicketsPageSizeTest() throws IOException, InterruptedException {
        TicketViewer ticketViewer = TicketViewer.getAllTickets();
        assertTrue("getAllTickets() should return a TicketViewer object with a ticket array of at most 25 tickets", ticketViewer.tickets.length <= 25);
    }

    /**
     * Test to ensure TicketViewer toString() method works with an empty ticket array
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void ticketViewerToStringTestWithEmptyTicketArray() throws IOException, InterruptedException {
        TicketViewer emptyTicketViewer = TicketViewer.getAllTickets();
        emptyTicketViewer.tickets = new Ticket[0];
        String expectedString = "\n** YOUR TICKET(S) ** \n";
        expectedString = expectedString + "PAGE: " + emptyTicketViewer.pageCount + "\n";

        assertEquals("toString() with an empty ticket array should display no ticket information", expectedString, emptyTicketViewer.toString());
    }

    /**
     * Test to ensure TicketViewer toString() method works with a ticket array containing one single ticket
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void ticketViewerToStringTestWithOneTicket() throws IOException, InterruptedException {
        TicketViewer ticketViewer = TicketViewer.getTicketById(111);
        String expectedString = "\n** YOUR TICKET(S) ** \n";
        expectedString = expectedString + "Ticket ID: 111" + "\n" +
                "   Created at: " + ticketViewer.tickets[0].getCreated_at() + "\n" +
                "   Subject: " + ticketViewer.tickets[0].getSubject() + "\n\n";
        expectedString = expectedString + "PAGE: " + ticketViewer.pageCount + "\n";

        assertEquals("toString() with one element ticket array should display information of a single ticket in short", expectedString, ticketViewer.toString());
    }

    /**
     * Test to ensure TicketViewer toString() method works with a ticket array containing three tickets
     * @throws IOException
     * @throws InterruptedException
     */
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

        assertEquals("toString() with three elements ticket array should display information of three tickets in short", expectedString, ticketViewer.toString());
    }

    /**
     * Test to ensure TicketViewer toStringIndividual() work with an empty ticket array
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void ticketViewerToStringIndividualTestWithEmptyTicketArray() throws IOException, InterruptedException {
        TicketViewer ticketViewer = TicketViewer.getTicketById(120);
        ticketViewer.tickets = new Ticket[0];
        String expectedString = "\n** YOUR TICKET(S) ** \n";
        assertEquals("toStringIndividual() with an empty ticket array should display no ticket information", expectedString, ticketViewer.toStringIndividual());
    }

    /**
     * Test to ensure TicketViewer toStringIndividual() work with a ticket array containing one single ticket
     * @throws IOException
     * @throws InterruptedException
     */
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

        assertEquals("toStringIndividual() with one element ticket array should display one single ticket information in details", expectedString, ticketViewer.toStringIndividual());
    }
}
