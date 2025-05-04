package org.example;

import org.example.model.Movie;
import org.example.service.TicketService;

import java.util.List;
import java.util.Scanner;

public class Main {
    static TicketService ticketService = new TicketService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMain");
            System.out.println("1. Add User");
            System.out.println("2. Add Movie");
            System.out.println("3. Show movie list");
            System.out.println("4. Buy ticket");
            System.out.println("5. Login User");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choosing = scanner.nextInt();
            scanner.nextLine();

            switch (choosing) {
                case 1:
                    System.out.print("Enter your name: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your mobile: ");
                    String mobile = scanner.nextLine();
                    ticketService.createUser(username, mobile);
                    System.out.println("User added.");
                    break;

                case 2:
                    System.out.print("Enter movie name: ");
                    String movieName = scanner.nextLine();
                    System.out.print("Enter available tickets: ");
                    int availableTicket = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    ticketService.createMovie(movieName, availableTicket, price);
                    System.out.println("Movie added.");
                    break;

                case 3:
                    showMovieList();
                    break;

                case 4:
                    if (ticketService.getCurrentUser() == null) {
                        System.out.println("You must log in first (Option 5).");
                        break;
                    }

                    List<Movie> movieList = showMovieList();
                    System.out.print("Choose movie number: ");
                    int choice = scanner.nextInt();
                    Movie selectedMovie = ticketService.getMovieById(choice - 1);
                    if (selectedMovie == null) {
                        System.out.println("Movie not found.");
                        break;
                    }

                    System.out.print("Enter ticket quantity: ");
                    int quantity = scanner.nextInt();

                    if (quantity <= 0) {
                        System.out.println("Invalid quantity.");
                    }
                    boolean success = ticketService.buyTicket(selectedMovie, quantity);
                    if (success) {
                        double totalPrice = ticketService.calculateTotal(selectedMovie, quantity);
                        System.out.println("Purchase successful. Total: " + totalPrice);
                    } else {
                        System.out.println("Not enough tickets.");
                    }
                    break;

            case 5:
                System.out.print("Enter your name to login: ");
                String loginName = scanner.nextLine();
                if (ticketService.loginUser(loginName)) {
                    System.out.println("Logged in as: " + loginName);
                } else {
                    System.out.println("User not found.");
                }
                break;

            case 6:
                System.out.println("Goodbye!");
                running = false;
                break;

            default:
                System.out.println("Invalid option.");
        }
    }
}

public static List<Movie> showMovieList() {
    List<Movie> movieList = ticketService.getMovieList();
    if (movieList.isEmpty()) {
        System.out.println("No movies available.");
    } else {
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1) + ". " + movieList.get(i));
        }
    }
    return movieList;
}
}
