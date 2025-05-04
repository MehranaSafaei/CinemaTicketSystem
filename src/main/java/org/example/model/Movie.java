package org.example.model;

public class Movie {

    private Long id;
    private String name;
    private int availableTicket;
    private double price;

    public Movie() {
    }

    public Movie(String name, int availableTicket, double price) {
        this.name = name;
        this.availableTicket = availableTicket;
        this.price = price;
    }

    public boolean purchaseTicket(int quantity) {
        if (quantity > availableTicket) {
            availableTicket -= quantity;
            return true;
        }
        return false;
    }

    public double calculatePrice(int quantity) {
        return quantity * price;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableTicket() {
        return availableTicket;
    }
    public void setAvailableTicket(int availableTicket) {
        this.availableTicket = availableTicket;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", availableTicket='" + availableTicket + '\'' +
                ", price=" + price +
                '}';
    }
}
