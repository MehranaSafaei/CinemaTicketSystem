package org.example;

import org.example.model.Movie;
import org.example.service.TicketService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final TicketService ticketService = new TicketService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int option = getUserOption();
            running = handleMenuOption(option);
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Add User");
        System.out.println("2. Add Movie");
        System.out.println("3. Show Movie List");
        System.out.println("4. Buy Ticket");
        System.out.println("5. Login User");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");
    }

    private static int getUserOption() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static boolean handleMenuOption(int option) {
        scanner.nextLine();
        switch (option) {
            case 1 -> addUser();
            case 2 -> addMovie();
            case 3 -> showMovieList();
            case 4 -> buyTicket();
            case 5 -> loginUser();
            case 6 -> {
                return false;
            }
            default -> System.out.println("Invalid option.");
        }
        return true;
    }

    private static void addUser() {
        System.out.print("Enter your name: ");
        String username = scanner.nextLine();
        System.out.print("Enter your mobile: ");
        String mobile = scanner.nextLine();
        ticketService.createUser(username, mobile);
        System.out.println("User added.");
    }

    private static void addMovie() {
        System.out.print("Enter movie name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter available tickets: ");
        int availableTicket = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        ticketService.createMovie(movieName, availableTicket, price);
        System.out.println("Movie added.");
    }

    private static void showMovieList() {
        List<Movie> movieList = ticketService.getMovieList();
        if (movieList.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println((i + 1) + ". " + movieList.get(i));
            }
        }
    }

    private static void buyTicket() {
        if (ticketService.getCurrentUser() == null) {
            System.out.println("You must log in first (Option 5).");
            return;
        }

        List<Movie> movieList = ticketService.getMovieList();
        if (movieList.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

        System.out.println("\nList of movies:");
        for (Movie movie : movieList) {
            System.out.println(movie.getName() + " - Available tickets: " + movie.getAvailableTicket() + " - Price: $" + movie.getPrice());
        }

        System.out.println("\nEnter the name of the movie you want to select (or 'exit' to close the program):");
        String selectedMovieName = scanner.nextLine();

        if (selectedMovieName.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the program. Goodbye!");
            return;
        }

        Movie selectedMovie = ticketService.getMovieByName(selectedMovieName);
        if (selectedMovie == null) {
            System.out.println("The selected movie does not exist. Please choose a different movie.");
            return;
        }

        System.out.println("Enter ticket quantity:");
        int requestedTickets = scanner.nextInt();
        scanner.nextLine();

        if (requestedTickets <= 0) {
            System.out.println("Invalid ticket quantity.");
            return;
        }

        if (requestedTickets > selectedMovie.getAvailableTicket()) {
            System.out.println("The requested number of tickets exceeds the available quantity. Please enter a smaller number.");
        } else {
            selectedMovie.setAvailableTicket(selectedMovie.getAvailableTicket() - requestedTickets);
            double totalCost = requestedTickets * selectedMovie.getPrice();
            System.out.println("Purchase successful! Total cost: $" + totalCost);
            ticketService.getCurrentUser().addPurchase(selectedMovie.getName(), requestedTickets, totalCost);
        }
    }

    private static void loginUser() {
        System.out.print("Enter your name to login: ");
        String loginName = scanner.nextLine();
        if (ticketService.loginUser(loginName)) {
            System.out.println("Logged in as: " + loginName);
        } else {
            System.out.println("User not found.");
        }
    }
}