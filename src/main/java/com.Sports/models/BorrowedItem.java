package com.Sports.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BorrowedItem {
    private int borrowedItemId;
    private int itemId;
    private LocalDate borrowedDate;
    private int quantity;
    private String borrowerId;

    public BorrowedItem(int itemId, LocalDate date,int borrowedItemId,int quantity,String borrowerId) {
        this.itemId = itemId;
        this.borrowedDate = date;
        this.borrowedItemId = borrowedItemId;
        this.quantity = quantity;
        this.borrowerId = borrowerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getBorrowedItemId() {
        return borrowedItemId;
    }

    public void setBorrowedItemId(int borrowedItemId) {
        this.borrowedItemId = borrowedItemId;
    }

    public LocalDate getDate() {
        return borrowedDate;
    }

    public void setDate(LocalDate date) {
        this.borrowedDate = date;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }
}
