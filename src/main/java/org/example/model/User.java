package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String name;
    private String mobile;
    private List<String> purchaseHistory; // List to store purchase details

    public User() {
        this.purchaseHistory = new ArrayList<>();
    }

    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
        this.purchaseHistory = new ArrayList<>();
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

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<String> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void addPurchase(String movieName, int requestedTickets, double totalCost) {
        String purchaseDetail = "Movie: " + movieName + ", Tickets: " + requestedTickets + ", Total Cost: $" + totalCost;
        this.purchaseHistory.add(purchaseDetail);
    }

    public void showPurchaseHistory() {
        if (purchaseHistory.isEmpty()) {
            System.out.println("No purchase history available.");
        } else {
            System.out.println("Purchase History:");
            for (String purchase : purchaseHistory) {
                System.out.println(purchase);
            }
        }
    }
}