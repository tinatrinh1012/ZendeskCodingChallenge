package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import model.Ticket;
import model.TicketViewer;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


public class App
{
    public static void main( String[] args ) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String userChoice;
        System.out.println("\n \n");
        System.out.println("*** WELCOME TO TICKET VIEWER APP! ***");

        while (true) {
            System.out.println();
            System.out.println("** MAIN MENU **");
            System.out.println("Enter an option number to continue:");
            System.out.println("1. View all tickets");
            System.out.println("2. View a ticket by id");
            System.out.println("3. Quit");
            System.out.print("Your choice: ");
            userChoice = scanner.nextLine(); // could trim input if want to
            System.out.println();

            if (userChoice.equals("1")) {
                try {
                    TicketViewer ticketViewer = TicketViewer.getAllTickets();
                    System.out.println(ticketViewer);

                    while (true) {
                        System.out.println();
                        System.out.println("Enter an option number to continue");
                        System.out.println("1. Return to main menu");
                        System.out.println("2. Previous page");
                        System.out.println("3. Next page");
                        System.out.print("Your choice: ");
                        userChoice = scanner.nextLine();
                        System.out.println();

                        if (userChoice.equals("1")) {
                            break;
                        } else if (userChoice.equals("2")) {
                            ticketViewer = ticketViewer.getPreviousPage();
                            System.out.println(ticketViewer);

                        } else if (userChoice.equals("3")) {
                            ticketViewer = ticketViewer.getNextPage();
                            System.out.println(ticketViewer);
                        } else {
                            System.out.println("** ATTENTION: Please enter valid option number! **");
                        }
                    }
                } catch (ConnectException e) {
                    System.out.println("** ATTENTION: Zendesk API is not available. Check your connection. **");
                }

            } else if (userChoice.equals("2")) {
                TicketViewer ticketViewer;
                try {
                    System.out.print("Enter ticket id: ");
                    userChoice = scanner.nextLine();
                    ticketViewer = TicketViewer.getTicketById(Integer.parseInt(userChoice));
                    System.out.println(ticketViewer.toStringIndividual());
                } catch (NumberFormatException e) {
                    System.out.println("** ATTENTION: Invalid id. Id needs to be a positive integer (so space allowed). Returning to main menu. **");
                } catch (IOException e) {
                    System.out.println("** ATTENTION: Didn't find a ticket with the provided id. Returning to main menu.");
                }
            } else if (userChoice.equals("3")) {
                System.out.println("*** THANKS FOR USING TICKETS VIEWER! ***");
                break;
            } else {
                System.out.println("** ATTENTION: Please enter a valid option number! **");
            }
        }
    }

    public static String getEncodedCredential() {
        String apiTokenCredentials = "tina.trinh@stthomas.edu/token:V9mFmN2WQnT7IEK4QcbTuEBClmgJ1XUXOTqFTKZq";
        String encodedCredential = new String(Base64.getEncoder().encode(apiTokenCredentials.getBytes()));
        return encodedCredential;
    }
}
