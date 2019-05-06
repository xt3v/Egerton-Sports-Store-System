package com.Sports.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BorrowedItem {
    private int borrowedItemId;
    private int itemId;
    private LocalDate borrowedDate;

    public BorrowedItem(int itemId, LocalDate date,int borrowedItemId) {
        this.itemId = itemId;
        this.borrowedDate = date;
        this.borrowedItemId = borrowedItemId;
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
}
