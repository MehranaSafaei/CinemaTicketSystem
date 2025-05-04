package org.example.service;

import org.example.model.Movie;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    List<Movie> movieList = new ArrayList<>();
    List<User> userList = new ArrayList<>();
    User currentUser;

    public void createUser(String name, String mobile) {
        User user = new User(name, mobile);
        userList.add(user);
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<Movie> getMovieList(){
        return movieList;
    }

    public Movie createMovie(String name, int availableTicket, double price) {
        Movie movie = new Movie(name, availableTicket, price);
        movieList.add(movie);
        return movie;
    }

    public List<User> getUserList(){
        return userList;
    }

    public boolean buyTicket(Movie movie, int quantity){
        return  movie.purchaseTicket(quantity);
    }

    public double calculateTotal(Movie movie, int quantity){
        return  movie.calculatePrice(quantity);
    }

    public Movie getMovieById(int id){
        if (id >= 0 && id < movieList.size()){
            return movieList.get(id);
        }
        return null;
    }

    public boolean loginUser(String loginName) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(loginName)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public Movie getMovieByName(String selectedMovieName) {
        if (selectedMovieName != null || selectedMovieName.isEmpty()) {
            return null;
        }else {
            for (Movie movie : movieList) {
                if (movie.getName().equalsIgnoreCase(selectedMovieName)) {
                    return movie;
                }
            }
        }
        return null;
    }
}
